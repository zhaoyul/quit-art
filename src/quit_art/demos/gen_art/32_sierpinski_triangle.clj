(ns quit-art.demos.gen-art.32-sierpinski-triangle
  (:require [quil.core :as q]))

(defn st-iterate-func [[x y]]
  (let [a (/ (q/width) 2)
        x2 (/ x 2)
        y2 (/ y 2)]
    (rand-nth [[x2 y2]
               [(+ x2 (* a 0.5)) (+ y2 (* a 0.86))]
               [(+ x2 (* a 1)) y2]])))

(defn setup []
  (q/no-loop)
  (q/background 0)
  (q/stroke 255 255 255)
  (q/stroke-weight 1))

(defn draw []
  (loop [times 200000
         point [5 5]]
    (when (> times 1)
      (apply q/point point)
      (recur (dec times) (st-iterate-func point)))))

(q/defsketch gen-art-32
  :title "Sierpinski Triangle"
  :setup setup
  :draw draw
  :size [800 800])

(defn -main [& args])
