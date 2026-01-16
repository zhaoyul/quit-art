(ns quit-art.demos.demo-04-perlin-flow
  "Demo 04: Perlin Noise Flow Field - Organic flowing lines using Perlin noise"
  (:require [quil.core :as q]))

(defn setup []
  "Initialize particles with random positions"
  (q/smooth)
  (q/background 250)
  (q/frame-rate 60)
  {:particles (vec (repeatedly 500
                                (fn []
                                  {:x (q/random (q/width))
                                   :y (q/random (q/height))
                                   :px (q/random (q/width))
                                   :py (q/random (q/height))})))})

(defn update-state [state]
  "Update particle positions based on Perlin noise flow field"
  (update state :particles
          (fn [particles]
            (mapv (fn [p]
                    (let [noise-val (q/noise (* (:x p) 0.01) (* (:y p) 0.01) (* (q/frame-count) 0.01))
                          angle (* noise-val q/TWO-PI 2)
                          speed 0.5]
                      {:x (+ (:x p) (* (q/cos angle) speed))
                       :y (+ (:y p) (* (q/sin angle) speed))
                       :px (:x p)
                       :py (:y p)}))
                  particles))))

(defn draw [state]
  "Draw flowing lines connecting particle positions"
  (q/fill 250 5)
  (q/rect 0 0 (q/width) (q/height))
  
  (q/stroke-weight 1)
  (doseq [p (:particles state)]
    (let [noise-val (q/noise (* (:x p) 0.01) (* (:y p) 0.01))
          hue (* noise-val 100)]
      (q/stroke hue 200 200 80)
      (q/line (:px p) (:py p) (:x p) (:y p)))))

(q/defsketch perlin-flow
  :title "Demo 04: Perlin Noise Flow"
  :setup setup
  :update update-state
  :draw draw
  :size [800 600]
  :color-mode :hsb
  :middleware [quil.middleware/fun-mode])

(defn -main [& args]
  (println "Demo 04: Perlin Flow - Run from REPL with (perlin-flow)"))
