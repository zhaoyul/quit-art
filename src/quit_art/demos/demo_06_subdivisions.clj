(ns quit-art.demos.demo-06-subdivisions
  "Demo 06: Recursive Subdivisions - Tyler Hobbs inspired recursive space filling"
  (:require [quil.core :as q]))

(defn setup
  "Setup drawing"
  []
  (q/smooth)
  (q/background 250)
  (q/stroke 30)
  (q/stroke-weight 1.5)
  (q/no-fill)
  (q/no-loop))

(defn should-subdivide?
  "Decide whether to subdivide a rectangle based on size and depth"
  [w h depth]
  (and (< depth 6)
       (or (> w 80) (> h 80))
       (> (q/random 1) 0.3)))

(defn draw-subdivision
  "Recursively subdivide rectangles with organic variations"
  [x y w h depth]
  (when (should-subdivide? w h depth)
    (if (> (q/random 1) 0.5)
      ;; Vertical split
      (let [split-point (+ 0.3 (q/random 0.4))
            split-x (+ x (* w split-point))]
        (q/line split-x y split-x (+ y h))
        (draw-subdivision x y (* w split-point) h (inc depth))
        (draw-subdivision split-x y (* w (- 1 split-point)) h (inc depth)))
      ;; Horizontal split
      (let [split-point (+ 0.3 (q/random 0.4))
            split-y (+ y (* h split-point))]
        (q/line x split-y (+ x w) split-y)
        (draw-subdivision x y w (* h split-point) (inc depth))
        (draw-subdivision x split-y w (* h (- 1 split-point)) (inc depth)))))

  ;; Draw some details in leaf rectangles
  (when (< (q/random 1) 0.3)
    (let [padding 5
          gray (q/random 200 240)]
      (q/fill gray)
      (q/rect (+ x padding) (+ y padding) 
              (- w (* 2 padding)) (- h (* 2 padding)))
      (q/no-fill))))

(defn draw
  "Create recursive subdivision artwork"
  []
  (let [margin 40
        w (- (q/width) (* 2 margin))
        h (- (q/height) (* 2 margin))]
    ;; Draw border
    (q/rect margin margin w h)
    ;; Start recursive subdivision
    (draw-subdivision margin margin w h 0)))

(q/defsketch subdivisions
  :title "Demo 06: Recursive Subdivisions"
  :setup setup
  :draw draw
  :size :fullscreen)

(defn -main [& args]
  (println "Demo 06: Subdivisions - Run from REPL with (subdivisions)"))
