(ns quit-art.demos.demo-02-colors-patterns
  "Demo 02: Colors and Patterns - Colorful geometric patterns"
  (:require [quil.core :as q]))

(defn setup "Setup creates a grid of colored circles"
  []
  (q/smooth)
  (q/background 250)
  (q/no-stroke)
  (q/no-loop))

(defn draw
  "Draw a grid of colored circles with varying sizes"
  []
  (let [cols 10
        rows 6
        cell-w (/ (q/width) cols)
        cell-h (/ (q/height) rows)]
    (doseq [i (range cols)
            j (range rows)]
      (let [x (+ (* i cell-w) (/ cell-w 2))
            y (+ (* j cell-h) (/ cell-h 2))
            hue (* 255 (/ (* (+ i j) 15) (* 15 15)))
            size (+ 20 (* 250 (Math/sin (+ i j))))]
        (q/fill hue 10 255)
        (q/ellipse x y size size)))))

(q/defsketch colors-patterns
  :title "Demo 02: Colors and Patterns"
  :setup setup
  :draw draw
  :size :fullscreen
  :color-mode :hsb)

(defn -main [& args]
  (println "Demo 02: Colors and Patterns - Run from REPL with (colors-patterns)"))
