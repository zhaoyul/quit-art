(ns quit-art.demos.demo-05-generative-lines
  "Demo 05: Generative Lines - Tyler Hobbs inspired line art with subtle variations"
  (:require [quil.core :as q]))

(defn setup
  "Setup with light background"
  []
  (q/smooth)
  (q/background 245)
  (q/stroke 20)
  (q/stroke-weight 2)
  (q/no-loop))

(defn draw-curved-line
  "Draw a curved line with multiple segments and noise-based displacement"
  [x1 y1 x2 y2 segments]
  (q/begin-shape)
  (q/vertex x1 y1)
  (doseq [i (range 1 segments)]
    (let [t (/ i segments)
          x (q/lerp x1 x2 t)
          y (q/lerp y1 y2 t)
          noise-x (* (- (q/noise (* x 0.01) (* y 0.01) 100) 0.5) 40)
          noise-y (* (- (q/noise (* x 0.01) (* y 0.01) 200) 0.5) 40)]
      (q/curve-vertex (+ x noise-x) (+ y noise-y))))
  (q/vertex x2 y2)
  (q/end-shape))

(defn draw
  "Draw generative line art"
  []
  (let [num-lines 60
        margin 50
        spacing (/ (- (q/width) (* 2 margin)) (dec num-lines))]

    ;; Draw vertical flowing lines
    (doseq [i (range num-lines)]
      (let [x (+ margin (* i spacing))
            y1 margin
            y2 (- (q/height) margin)
            segments 50]
        (q/stroke 20 20 30 (+ 100 (q/random 100)))
        (q/stroke-weight (+ 0.5 (q/random 2)))
        (draw-curved-line x y1 x y2 segments)))
    
    ;; Add some horizontal accent lines
    (doseq [i (range 8)]
      (let [y (+ (* (q/height) 0.2) (* i (/ (* (q/height) 0.6) 7)))
            x1 margin
            x2 (- (q/width) margin)]
        (q/stroke 20 80 120 80)
        (q/stroke-weight 1)
        (draw-curved-line x1 y x2 y 80)))))

(q/defsketch generative-lines
  :title "Demo 05: Generative Lines"
  :setup setup
  :draw draw
  :size :fullscreen)

(defn -main [& args]
  (println "Demo 05: Generative Lines - Run from REPL with (generative-lines)"))
