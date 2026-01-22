(ns quit-art.demos.gen-art.33-peter-de-jong
  (:require [quil.core :as q]))

(defn peter-iterate-func [[x y]]
  (let [a 1.419
        b -2.284
        c 2.4275
        d -2.177196]
    [(- (q/sin (* a y)) (q/cos (* b x)))
     (- (q/sin (* c x)) (q/cos (* d y)))]))

(defn setup []
  (q/no-loop)
  (q/color-mode :hsb)
  (q/background 0)
  (q/stroke 180 80 100 80)
  (q/stroke-weight 1))

(defn draw []
  (q/with-translation [(/ (q/width) 2) (/ (q/height) 2)]
    (q/with-rotation [q/PI]
      (loop [times 3000000
             point [1.1 1.0]]
        (when (> times 1)
          (apply q/point (map #(* (/ (q/width) 4) %) point))
          (recur (dec times) (peter-iterate-func point)))))))

(q/defsketch gen-art-33
  :title "Peter de Jong Attractor"
  :setup setup
  :draw draw
  :size [1200 1200])

(defn -main [& args])
