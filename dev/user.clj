(ns user
  "REPL helper namespace for loading and running demos"
  (:require [quit-art.demos.demo-01-basic-shapes :as demo01]
            [quit-art.demos.demo-02-colors-patterns :as demo02]
            [quit-art.demos.demo-03-animation :as demo03]
            [quit-art.demos.demo-04-perlin-flow :as demo04]
            [quit-art.demos.demo-05-generative-lines :as demo05]
            [quit-art.demos.demo-06-subdivisions :as demo06]
            [quit-art.demos.demo-07-flow-fields :as demo07]
            [quit-art.demos.demo-08-circle-packing :as demo08]))

(defn list-demos []
  "List all available demos"
  (println "\nAvailable Demos:")
  (println "================")
  (println "1. (demo01/basic-shapes)      - Basic geometric shapes")
  (println "2. (demo02/colors-patterns)   - Colorful patterns with HSB colors")
  (println "3. (demo03/animation)         - Animated rotating squares")
  (println "4. (demo04/perlin-flow)       - Flowing particles with Perlin noise")
  (println "5. (demo05/generative-lines)  - Tyler Hobbs inspired line art")
  (println "6. (demo06/subdivisions)      - Recursive space subdivisions")
  (println "7. (demo07/flow-fields)       - Advanced multi-layer flow fields")
  (println "8. (demo08/circle-packing)    - Organic circle packing algorithm")
  (println "\nTo run a demo, call its function, e.g.: (demo01/basic-shapes)")
  (println "To list demos again: (list-demos)\n"))

(println "\n=== Welcome to Quit-Art ===")
(println "Generative art with Clojure and Quil")
(list-demos)
