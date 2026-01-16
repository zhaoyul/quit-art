(ns quit-art.demos.demo-07-flow-fields
  "Demo 07: Advanced Flow Fields - Complex flow field visualization with color gradients"
  (:require [quil.core :as q]))

(defn setup []
  "Initialize with multiple particle layers"
  (q/smooth)
  (q/background 250)
  (q/frame-rate 60)
  {:time 0
   :particles (vec (repeatedly 800
                                (fn []
                                  {:x (q/random (q/width))
                                   :y (q/random (q/height))
                                   :px (q/random (q/width))
                                   :py (q/random (q/height))
                                   :speed (+ 0.3 (q/random 0.7))
                                   :color-offset (q/random 100)})))})

(defn update-state [state]
  "Update particle positions based on layered flow fields"
  (-> state
      (update :time #(+ % 0.01))
      (update :particles
              (fn [particles]
                (mapv (fn [p]
                        (let [scale 0.005
                              time-scale 0.02
                              t (:time state)
                              ;; Multiple noise layers for complex movement
                              noise1 (q/noise (* (:x p) scale) 
                                             (* (:y p) scale) 
                                             (* t time-scale))
                              noise2 (q/noise (* (:x p) scale 1.7) 
                                             (* (:y p) scale 1.7) 
                                             (+ (* t time-scale) 100))
                              angle (+ (* noise1 q/TWO-PI 2)
                                      (* noise2 q/PI 0.5))
                              speed (:speed p)
                              new-x (+ (:x p) (* (q/cos angle) speed))
                              new-y (+ (:y p) (* (q/sin angle) speed))
                              ;; Wrap around edges
                              wrapped-x (cond
                                         (< new-x 0) (q/width)
                                         (> new-x (q/width)) 0
                                         :else new-x)
                              wrapped-y (cond
                                         (< new-y 0) (q/height)
                                         (> new-y (q/height)) 0
                                         :else new-y)]
                          {:x wrapped-x
                           :y wrapped-y
                           :px (:x p)
                           :py (:y p)
                           :speed speed
                           :color-offset (:color-offset p)}))
                      particles)))))

(defn draw [state]
  "Draw flowing lines with color based on position and flow"
  ;; Subtle fade effect
  (q/fill 250 8)
  (q/rect 0 0 (q/width) (q/height))
  
  (q/stroke-weight 1.5)
  (doseq [p (:particles state)]
    (let [;; Color based on position and flow
          hue (mod (+ (* (q/noise (* (:x p) 0.003) 
                                  (* (:y p) 0.003) 
                                  (:time state)) 60)
                      (:color-offset p))
                   100)
          saturation 180
          brightness 220
          alpha 60]
      (q/stroke hue saturation brightness alpha)
      (q/line (:px p) (:py p) (:x p) (:y p)))))

(q/defsketch flow-fields
  :title "Demo 07: Advanced Flow Fields"
  :setup setup
  :update update-state
  :draw draw
  :size [900 900]
  :color-mode :hsb
  :middleware [quil.middleware/fun-mode])

(defn -main [& args]
  (println "Demo 07: Flow Fields - Run from REPL with (flow-fields)"))
