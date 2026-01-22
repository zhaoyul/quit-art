(ns quit-art.demos.gen-art.34-mandelbrot-set
  (:require [quil.core :as q]))

(defn mandelbrot-iter
  [^double cr ^double ci max-iter]
  (loop [zr 0.0
         zi 0.0
         n 0]
    (if (>= n max-iter)
      n
      (let [zr2 (- (* zr zr) (* zi zi) cr)
            zi2 (+ (* 2 zr zi) ci)
            zr zr2
            zi zi2]
        (if (> (+ (* zr zr) (* zi zi)) 4.0)
          n
          (recur zr zi (inc n)))))))

(defn setup []
  (q/color-mode :hsb 360 100 100)
  (q/background 0)
  (q/no-loop))

(defn draw []
  (let [w (q/width)
        h (q/height)
        max-iter 200
        xmin -2.5
        xmax 1.0
        ymin -1.5
        ymax 1.5]
    (doseq [x (range w)
            y (range h)]
      (let [cr (+ xmin (* (/ x w) (- xmax xmin)))
            ci (+ ymin (* (/ y h) (- ymax ymin)))
            n (mandelbrot-iter cr ci max-iter)
            hue (if (= n max-iter) 0 (* 360 (/ n max-iter)))
            br (if (= n max-iter) 0 100)]
        (q/stroke hue 80 br)
        (q/point x y))))
  (q/save "generated/mandelbrot.png"))

(q/defsketch gen-art-34
  :title "Mandelbrot Set"
  :setup setup
  :draw draw
  :size [800 800])

(defn -main [& args])
