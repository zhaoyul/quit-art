(ns quit-art.demos.demo-08-circle-packing
  "Demo 08: Circle Packing - Organic space filling with non-overlapping circles"
  (:require [quil.core :as q]))

(defn circle-intersects?
  "Check if a circle at (x, y) with radius r intersects any existing circles"
  [x y r circles]
  (some (fn [c]
          (let [dx (- x (:x c))
                dy (- y (:y c))
                dist (q/sqrt (+ (* dx dx) (* dy dy)))
                min-dist (+ r (:r c) 2)] ; 2px gap
            (< dist min-dist)))
        circles))

(defn try-add-circle
  "Try to add a new circle to the collection"
  [circles attempts]
  (let [min-r 3    ; Minimum starting radius
        max-r 60]  ; Maximum circle radius
    (loop [i 0]
      (if (>= i attempts)
        circles ; Failed to add after attempts
        (let [x (q/random (q/width))
              y (q/random (q/height))]
          (if (circle-intersects? x y min-r circles)
            (recur (inc i))
            ;; Found a spot, now grow the circle
            (let [grown-circle (loop [current-r min-r]
                                 (if (>= current-r max-r)
                                   {:x x :y y :r current-r}
                                   (if (circle-intersects? x y (inc current-r) circles)
                                     {:x x :y y :r current-r}
                                     (recur (inc current-r)))))]
              (conj circles grown-circle))))))))

(defn setup
  "Setup circle packing algorithm"
  []
  (q/smooth)
  (q/background 250)
  (q/no-fill)
  (q/stroke-weight 1.5)
  {:circles []
   :generation 0
   :max-circles 300})

(defn update-state 
  "Add circles gradually"
  [state]
  (if (< (count (:circles state)) (:max-circles state))
    (-> state
        (update :circles try-add-circle 50)
        (update :generation inc))
    state))

(defn draw 
  "Draw all circles with colors based on size"
  [state]
  (q/background 250)

  (doseq [c (:circles state)]
    (let [hue (* (/ (:r c) 60) 60)
          saturation 150
          brightness 180]
      (q/stroke hue saturation brightness)
      (q/stroke-weight (+ 1 (* (/ (:r c) 60) 2)))
      (q/ellipse (:x c) (:y c) (* 2 (:r c)) (* 2 (:r c)))))

  ;; Show progress
  (q/fill 0)
  (q/text-size 12)
  (q/text (str "Circles: " (count (:circles state)) " / " (:max-circles state))
          10 20))

(q/defsketch circle-packing
  :title "Demo 08: Circle Packing"
  :setup setup
  :update update-state
  :draw draw
  :size :fullscreen
  :color-mode :hsb
  :middleware [quil.middleware/fun-mode])

(defn -main [& args]
  (println "Demo 08: Circle Packing - Run from REPL with (circle-packing)"))
