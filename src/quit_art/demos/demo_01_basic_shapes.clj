(ns quit-art.demos.demo-01-basic-shapes
  "Demo 01: Basic Shapes - Simple static drawing with basic geometric shapes"
  (:require [quil.core :as q]))

(defn setup "Setup function runs once at the start"
  []
  (q/smooth)
  (q/background 240)
  (q/no-loop))

(defn draw "Draw function creates the artwork"
  []
  ;; Draw a cross with two lines
  (q/stroke 130 0 0)
  (q/stroke-weight 4)
  (let [cross-size 70
        center-x (/ (q/width) 2)
        center-y (/ (q/height) 2)]
    (q/line (- center-x cross-size) (- center-y cross-size)
            (+ center-x cross-size) (+ center-y cross-size))
    (q/line (+ center-x cross-size) (- center-y cross-size)
            (- center-x cross-size) (+ center-y cross-size))

    ;; Draw a filled circle in the center
    (q/fill 255 150)
    (q/ellipse center-x center-y 50 50)))

(q/defsketch basic-shapes
  :title "Demo 01: Basic Shapes"
  :setup setup
  :draw draw
  :size [1000 600])

(defn -main [& args]
  (println "Demo 01: Basic Shapes - Run from REPL with (basic-shapes)"))
