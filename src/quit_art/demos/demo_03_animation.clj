(ns quit-art.demos.demo-03-animation
  "Demo 03: Animation - Simple animated artwork with rotation"
  (:require [quil.core :as q]))

(defn setup []
  "Initialize state with rotation angle"
  (q/smooth)
  (q/frame-rate 30)
  {:angle 0})

(defn update-state [state]
  "Update the rotation angle"
  (update state :angle #(+ % 0.02)))

(defn draw [state]
  "Draw rotating squares"
  (q/background 240)
  (q/translate (/ (q/width) 2) (/ (q/height) 2))
  
  ;; Draw multiple rotating squares
  (doseq [i (range 5)]
    (q/push-matrix)
    (q/rotate (+ (:angle state) (* i 0.5)))
    (q/stroke 50 100 200 150)
    (q/stroke-weight 2)
    (q/no-fill)
    (let [size (+ 50 (* i 30))]
      (q/rect (- (/ size 2)) (- (/ size 2)) size size))
    (q/pop-matrix)))

(q/defsketch animation
  :title "Demo 03: Animation"
  :setup setup
  :update update-state
  :draw draw
  :size [500 500]
  :middleware [quil.middleware/fun-mode])

(defn -main [& args]
  (println "Demo 03: Animation - Run from REPL with (animation)"))
