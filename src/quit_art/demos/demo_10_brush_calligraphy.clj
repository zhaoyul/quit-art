(ns quit-art.demos.demo-10-brush-calligraphy
  "Demo 10: Brush Calligraphy - Wet ink strokes using watercolor layering"
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn rand-gauss
  [sd]
  (* sd (q/random-gaussian)))

(defn edge-normal
  [[x1 y1] [x2 y2]]
  (let [dx (- x2 x1)
        dy (- y2 y1)
        len (Math/sqrt (+ (* dx dx) (* dy dy)))]
    (if (zero? len)
      [0 0]
      [(/ (- dy) len) (/ dx len)])))

(defn midpoint
  [[x1 y1] [x2 y2]]
  [(/ (+ x1 x2) 2.0) (/ (+ y1 y2) 2.0)])

(defn regular-polygon
  "Create a regular polygon with per-edge variance factors." 
  [radius sides]
  (let [points (vec
                (for [i (range sides)]
                  (let [angle (* i (/ q/TWO-PI sides))]
                    [(* (Math/cos angle) radius)
                     (* (Math/sin angle) radius)])))
        vars (vec (repeatedly sides #(q/random 0.6 1.5)))]
    {:points points :vars vars}))

(defn deform-polygon
  [{:keys [points vars]} sd]
  (let [n (count points)]
    (loop [i 0
           new-points (transient [])
           new-vars (transient [])]
      (if (= i n)
        {:points (persistent! new-points)
         :vars (persistent! new-vars)}
        (let [p1 (nth points i)
              p2 (nth points (mod (inc i) n))
              v (nth vars i)
              [mx my] (midpoint p1 p2)
              [nx ny] (edge-normal p1 p2)
              offset (rand-gauss (* sd v))
              mid [(+ mx (* nx offset)) (+ my (* ny offset))]
              np (-> new-points (conj! p1) (conj! mid))
              nv (-> new-vars (conj! v) (conj! v))]
          (recur (inc i) np nv))))))

(defn recursive-deform
  [poly iterations start-sd decay]
  (loop [p poly
         iter iterations
         sd start-sd]
    (if (zero? iter)
      p
      (recur (deform-polygon p sd)
             (dec iter)
             (* sd decay)))))

(defn build-templates
  [base-poly]
  [(recursive-deform base-poly 2 0.18 0.6)
   (recursive-deform base-poly 3 0.22 0.6)
   (recursive-deform base-poly 4 0.26 0.6)])

(defn draw-polygon
  [{:keys [points]}]
  (q/begin-shape)
  (doseq [[x y] points]
    (q/vertex x y))
  (q/end-shape :close))

(defn layer-template
  [templates layer-idx total-layers]
  (let [third (int (/ total-layers 3))]
    (cond
      (< layer-idx third) (nth templates 0)
      (< layer-idx (* 2 third)) (nth templates 1)
      :else (nth templates 2))))

(defn pressure-curve
  "Thick in the middle, thin at ends." 
  [t]
  (Math/sin (* Math/PI t)))

(defn build-stroke
  "Create a wet brush stroke made of layered watercolor dabs." 
  [x y]
  (let [steps (int (q/random 18 26))
        length (q/random 160 280)
        step (/ length (dec steps))
        base-angle (q/random q/TWO-PI)
        curve-sd 0.22
        base-radius (q/random 14 26)]
    (loop [i 0
           angle base-angle
           pos [x y]
           dabs []]
      (if (= i steps)
        {:color [210 10 12]
         :dabs dabs}
        (let [t (/ i (dec steps))
              pressure (pressure-curve t)
              r (+ (* base-radius (+ 0.35 (* 0.9 pressure)))
                   (rand-gauss (* base-radius 0.05)))
              angle* (+ angle (rand-gauss curve-sd))
              dx (* step (Math/cos angle*))
              dy (* step (Math/sin angle*))
              next-pos [(+ (first pos) dx) (+ (second pos) dy)]
              templates (build-templates (regular-polygon 1 9))
              layers (+ 10 (int (* 12 pressure)))
              dab {:pos pos
                   :angle angle*
                   :radius r
                   :pressure pressure
                   :templates templates
                   :layers layers}]
          (recur (inc i) angle* next-pos (conj dabs dab)))))))

(defn draw-dab
  [{:keys [pos angle radius pressure templates layers]} color]
  (let [[h s b] color
        [x y] pos
        sx (* radius 1.8)
        sy (* radius 0.7)
        alpha-base (+ 0.012 (* 0.02 pressure))]
    (dotimes [layer-idx layers]
      (let [template (layer-template templates layer-idx layers)
            layer-poly (recursive-deform template 2 0.15 0.6)
            alpha (+ alpha-base (* 0.01 (q/noise (* 0.1 layer-idx) (* 0.2 x) (* 0.2 y))))]
        (q/push-matrix)
        (q/translate (+ x (rand-gauss (* 0.3 pressure)))
                     (+ y (rand-gauss (* 0.3 pressure))))
        (q/rotate angle)
        (q/scale sx sy)
        (q/no-stroke)
        (q/fill h s b alpha)
        (draw-polygon layer-poly)
        (q/pop-matrix)))))

(defn paper-texture
  []
  (q/no-stroke)
  (doseq [y (range 0 (q/height) 3)
          x (range 0 (q/width) 3)]
    (let [n (q/noise (* 0.02 x) (* 0.02 y))
          a (+ 0.015 (* 0.03 n))]
      (q/fill 0 0 100 a)
      (q/rect x y 2 2))))

(defn trim-strokes
  [strokes max-count]
  (let [v (if (coll? strokes) (vec strokes) [])
        n (count v)]
    (if (<= n max-count)
      v
      (subvec v (- n max-count)))))

(defn setup []
  (q/frame-rate 1)
  (q/smooth)
  (q/color-mode :hsb 360 100 100 1.0)
  (q/noise-seed 42)
  (q/random-seed 42)
  (q/no-loop)
  {:brush-strokes []
   :paper [0 0 100]})

(defn draw [{:keys [brush-strokes paper]}]
  (apply q/background paper)
  (doseq [{:keys [color dabs]} brush-strokes]
    (doseq [dab dabs]
      (draw-dab dab color)))
  (paper-texture))

(defn mouse-pressed [state event]
  (let [x (:x event)
        y (:y event)
        stroke (build-stroke x y)]
    (q/redraw)
    (update state :brush-strokes
            (fn [s]
              (trim-strokes
               (conj (if (coll? s) (vec s) []) stroke)
               12)))))

(q/defsketch brush-calligraphy
  :title "Demo 10: Brush Calligraphy"
  :size [900 700]
  :setup setup
  :draw draw
  :mouse-pressed mouse-pressed
  :middleware [m/fun-mode])

(defn -main [& args]
  (println "Demo 10: Brush Calligraphy - Click to place a wet brush stroke"))
