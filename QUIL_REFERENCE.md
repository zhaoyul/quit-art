# Quil Quick Reference / 快速参考

本文档提供常用 Quil 函数和模式的快速参考。

## 基础设置 / Basic Setup

### 最简单的 Sketch

```clojure
(ns my-sketch
  (:require [quil.core :as q]))

(defn setup []
  (q/smooth)
  (q/background 255))

(defn draw []
  (q/ellipse (/ (q/width) 2) (/ (q/height) 2) 50 50))

(q/defsketch my-sketch
  :setup setup
  :draw draw
  :size [500 500])
```

### Fun Mode (推荐用于动画)

```clojure
(defn setup []
  (q/smooth)
  {:x 0 :y 0})  ; 返回初始状态

(defn update-state [state]
  (update state :x inc))  ; 更新状态

(defn draw [state]
  (q/background 255)
  (q/ellipse (:x state) 250 50 50))

(q/defsketch my-sketch
  :setup setup
  :update update-state
  :draw draw
  :size [500 500]
  :middleware [quil.middleware/fun-mode])
```

## 常用函数 / Common Functions

### 绘图 / Drawing

```clojure
;; 点和线
(q/point x y)
(q/line x1 y1 x2 y2)

;; 基础形状
(q/ellipse x y w h)           ; 椭圆/圆
(q/rect x y w h)              ; 矩形
(q/triangle x1 y1 x2 y2 x3 y3) ; 三角形
(q/quad x1 y1 x2 y2 x3 y3 x4 y4) ; 四边形

;; 自定义形状
(q/begin-shape)
(q/vertex x y)
(q/curve-vertex x y)
(q/end-shape :close)  ; :close 表示闭合形状
```

### 颜色和样式 / Colors & Styles

```clojure
;; 填充和描边
(q/fill r g b)         ; RGB
(q/fill r g b alpha)   ; RGBA (透明度)
(q/no-fill)            ; 不填充
(q/stroke r g b)       ; 描边颜色
(q/no-stroke)          ; 不描边
(q/stroke-weight w)    ; 线条粗细

;; 背景
(q/background 255)     ; 白色背景
(q/background 0)       ; 黑色背景
(q/background r g b)

;; HSB 色彩模式
(q/color-mode :hsb)    ; 在 defsketch 中设置
(q/fill hue saturation brightness)
```

### 变换 / Transformations

```clojure
;; 平移
(q/translate x y)

;; 旋转 (弧度)
(q/rotate angle)
(q/rotate q/PI)        ; 旋转 180 度
(q/rotate q/HALF-PI)   ; 旋转 90 度

;; 缩放
(q/scale s)            ; 等比缩放
(q/scale sx sy)        ; 分别设置 x, y 缩放

;; 保存和恢复变换
(q/push-matrix)
;; ... 进行变换和绘图 ...
(q/pop-matrix)         ; 恢复之前的变换状态
```

### 数学函数 / Math Functions

```clojure
;; 三角函数
(q/sin angle)
(q/cos angle)
(q/tan angle)

;; 噪声 (Perlin Noise)
(q/noise x)            ; 1D 噪声
(q/noise x y)          ; 2D 噪声
(q/noise x y z)        ; 3D 噪声
(q/noise-seed seed)    ; 设置噪声种子

;; 随机
(q/random max)         ; 0 到 max
(q/random min max)     ; min 到 max
(q/random-seed seed)   ; 设置随机种子

;; 插值
(q/lerp start stop amt) ; 线性插值
(q/map-range value start1 stop1 start2 stop2) ; 映射范围

;; 约束
(q/constrain value min max) ; 限制在范围内
```

### 控制 / Control

```clojure
;; 帧率
(q/frame-rate fps)     ; 设置帧率
(q/frame-count)        ; 当前帧数

;; 循环控制
(q/no-loop)            ; 停止 draw 循环 (静态图)
(q/loop)               ; 恢复循环

;; 画布信息
(q/width)              ; 画布宽度
(q/height)             ; 画布高度
```

### 文本 / Text

```clojure
(q/text "Hello" x y)
(q/text-size size)
(q/text-align :left)   ; :left, :center, :right
```

### 图像导出 / Image Export

```clojure
(q/save-frame "output-####.png")  ; #### 会被帧数替换
(q/save-frame "my-art.png")
```

## 常见模式 / Common Patterns

### 1. 网格迭代

```clojure
(let [cols 10
      rows 10
      cell-w (/ (q/width) cols)
      cell-h (/ (q/height) rows)]
  (doseq [i (range cols)
          j (range rows)]
    (let [x (* i cell-w)
          y (* j cell-h)]
      (q/rect x y cell-w cell-h))))
```

### 2. 粒子系统

```clojure
(defn setup []
  {:particles (vec (repeatedly 100
                    (fn [] {:x (q/random (q/width))
                           :y (q/random (q/height))
                           :vx (q/random -2 2)
                           :vy (q/random -2 2)})))})

(defn update-state [state]
  (update state :particles
    (fn [particles]
      (mapv (fn [p]
              {:x (+ (:x p) (:vx p))
               :y (+ (:y p) (:vy p))
               :vx (:vx p)
               :vy (:vy p)})
            particles))))
```

### 3. 噪声流场

```clojure
(defn draw []
  (let [x 100
        y 100
        noise-val (q/noise (* x 0.01) (* y 0.01))
        angle (* noise-val q/TWO-PI)]
    (q/line x y
            (+ x (* (q/cos angle) 20))
            (+ y (* (q/sin angle) 20)))))
```

### 4. 鼠标交互

```clojure
(defn mouse-clicked [state event]
  ;; event: {:x :y :button}
  (assoc state :clicked-pos [(:x event) (:y event)]))

(q/defsketch my-sketch
  :mouse-clicked mouse-clicked
  :middleware [quil.middleware/fun-mode])
```

### 5. 键盘交互

```clojure
(defn key-pressed [state event]
  ;; event: {:key :key-code :raw-key}
  (case (:key event)
    :r (setup)  ; 重置
    :s (q/save-frame "output.png")  ; 保存
    state))

(q/defsketch my-sketch
  :key-pressed key-pressed
  :middleware [quil.middleware/fun-mode])
```

## 性能优化 / Performance Tips

1. **使用 `no-loop` 对于静态图**: 不需要动画时停止循环
2. **控制帧率**: `(q/frame-rate 30)` 降低帧率减少 CPU 使用
3. **减少透明度混合**: 透明度计算消耗较大
4. **避免在 draw 中创建大量对象**: 在 setup 中预分配
5. **使用合适的数据结构**: vector 比 list 快

## 调试技巧 / Debugging Tips

```clojure
;; 在画布上显示信息
(q/fill 0)
(q/text (str "Frame: " (q/frame-count)) 10 20)
(q/text (str "Mouse: " (q/mouse-x) "," (q/mouse-y)) 10 40)

;; 在 REPL 中打印
(println "State:" state)

;; 暂停动画检查
(defn key-pressed [state event]
  (when (= (:key event) :space)
    (if (:paused state)
      (q/start-loop)
      (q/no-loop)))
  (update state :paused not))
```

## 进阶主题 / Advanced Topics

### 使用着色器 (Shaders)

```clojure
(defn setup []
  (let [shader (q/load-shader "shader.frag")]
    {:shader shader}))

(defn draw [state]
  (q/shader (:shader state))
  ;; ... 绘图 ...
  )
```

### 3D 绘图

```clojure
(q/defsketch my-sketch
  :renderer :p3d  ; 或 :opengl
  :size [500 500])

(defn draw []
  (q/camera)
  (q/lights)
  (q/box 50))
```

## 资源链接 / Resources

- [Quil API 文档](http://quil.info/api)
- [Processing 参考](https://processing.org/reference)
- [Quil 示例](https://github.com/quil/quil-examples)
- [Fun Mode 教程](https://github.com/quil/quil/wiki/Functional-mode-%28fun-mode%29)
