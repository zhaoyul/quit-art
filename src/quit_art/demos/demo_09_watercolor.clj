(ns quit-art.demos.demo-09-watercolor
  "Demo 09: Watercolor Algorithm - Gaussian polygon deformation with layered washes"
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn rand-gauss
  "Gaussian offset with standard deviation sd."
  [sd]
  (* sd (q/random-gaussian)))

(defn edge-normal
  "Unit normal vector for the edge p1->p2 (perpendicular)."
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
  [x y radius sides]
  (let [points (vec
                (for [i (range sides)]
                  (let [angle (* i (/ q/TWO-PI sides))]
                    [(+ x (* (Math/cos angle) radius))
                     (+ y (* (Math/sin angle) radius))])))
        vars (vec (repeatedly sides #(q/random 0.6 1.5)))]
    {:points points :vars vars}))

(defn deform-polygon
  "Subdivide each edge and displace midpoint along the edge normal.
   Variance per edge controls how soft or sharp that edge becomes."
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
  "Apply Gaussian midpoint displacement recursively.
   Deformation decays each iteration so small scales stay subtle."
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
  "Create 3 base shapes with increasing deformation to control pigment concentration."
  [base-poly]
  [(recursive-deform base-poly 2 18 0.55)
   (recursive-deform base-poly 3 20 0.55)
   (recursive-deform base-poly 4 22 0.55)])

(defn draw-polygon
  [{:keys [points]}]
  (q/begin-shape)
  (doseq [[x y] points]
    (q/vertex x y))
  (q/end-shape :close))

(defn layer-template
  "Pick template based on layer index to darken the center."
  [templates layer-idx total-layers]
  (let [third (int (/ total-layers 3))]
    (cond
      (< layer-idx third) (nth templates 0)
      (< layer-idx (* 2 third)) (nth templates 1)
      :else (nth templates 2))))

(defn draw-watercolor-layer
  [{:keys [x y color templates]} layer-idx total-layers]
  (let [[h s b] color
        template (layer-template templates layer-idx total-layers)
        ;; Micro-variation for soft edges
        layer-poly (recursive-deform template 3 5 0.6)
        alpha (+ 0.02 (* 0.015 (q/noise (* 0.08 layer-idx) (* 0.3 x) (* 0.3 y))))]
    (q/push-matrix)
    (q/translate (+ x (rand-gauss 0.6)) (+ y (rand-gauss 0.6)))
    (q/no-stroke)
    (q/fill h s b alpha)
    (draw-polygon layer-poly)
    (q/pop-matrix)))

(defn paper-texture
  "Lightweight paper grain overlay to soften color boundaries."
  []
  (q/no-stroke)
  (doseq [y (range 0 (q/height) 3)
          x (range 0 (q/width) 3)]
    (let [n (q/noise (* 0.02 x) (* 0.02 y))
          a (+ 0.02 (* 0.04 n))]
      (q/fill 0 0 100 a)
      (q/rect x y 2 2))))

(defn setup []
  (q/frame-rate 1)
  (q/smooth)
  (q/color-mode :hsb 360 100 100 1.0)
  (q/random-seed 42)
  (q/noise-seed 42)
  (let [[center-x center-y] (mapv #(/ % 2) [(q/width) (q/height)])
        blobs [{:x 0 :y 0 :color [205 55 85] :radius (/ (q/height) 4)}
               {:x 0 :y 0 :color [330 50 92] :radius (/ (q/height) 5)}
               {:x 0 :y 0 :color [40 65 95] :radius (/ (q/height) 6)}]
        centers [[(- center-x (/ (q/width) 8))
                  (- center-y (/ (q/height) 8))]
                 [center-x
                  (+ center-y (/ (q/height) 7))]
                 [(+ center-x (/ (q/width) 9))
                  (- center-y (/ (q/height) 9))]]
        resolved (mapv (fn [b [cx cy]]
                         (let [base (regular-polygon 0 0 (:radius b) 10)
                               templates (build-templates base)]
                           (assoc b :x cx :y cy :templates templates)))
                       blobs centers)]
    {:blobs resolved
     :layers 54
     :paper [0 0 100]}))

(defn draw [{:keys [blobs layers paper]}]
  (apply q/background paper)
  ;; Layer-by-layer drawing improves color blending between blobs.
  (doseq [layer-idx (range layers)]
    (doseq [blob blobs]
      (draw-watercolor-layer blob layer-idx layers)))
  (paper-texture)
  (q/no-loop))

(q/defsketch watercolor
  :title "Demo 09: Watercolor Algorithm"
  :size :fullscreen
  :setup setup
  :draw draw
  :middleware [m/fun-mode])

(defn -main [& args]
  (println "Demo 09: Watercolor Algorithm - Run from REPL with (watercolor)"))
