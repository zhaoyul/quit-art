# quit-art

使用Clojure的quil库, 来生成漂亮的数字艺术作品

受 [Tyler Hobbs](https://www.tylerxhobbs.com/works) 艺术作品启发, 从简单到复杂创建生成式艺术。

## 安装 / Installation

确保已安装 Clojure CLI 工具。项目使用 `deps.edn` 进行依赖管理。

### 安装 Clojure

**macOS/Linux:**
```bash
curl -L -O https://github.com/clojure/brew-install/releases/latest/download/linux-install.sh
chmod +x linux-install.sh
sudo ./linux-install.sh
```

**Windows:**
参考 [Clojure 官方安装指南](https://clojure.org/guides/install_clojure)

### 克隆并运行项目

```bash
# 克隆仓库
git clone https://github.com/zhaoyul/quit-art.git
cd quit-art

# 首次运行会自动下载依赖 (需要网络连接到 repo.clojars.org)
clj
```

## 使用方法 / Usage

### 通过 REPL 交互运行

启动 REPL:

```bash
clj
```

REPL 启动后会自动加载 demo-01~08，你可以看到可用的演示列表。运行任意演示:

```clojure
;; 查看所有演示
(list-demos)

;; 基础演示 (静态)
(demo01/basic-shapes)
(demo02/colors-patterns)

;; 动画演示
(demo03/animation)
(demo04/perlin-flow)
(demo07/flow-fields)
(demo08/circle-packing)

;; 生成艺术 (Tyler Hobbs 风格)
(demo05/generative-lines)
(demo06/subdivisions)
```

### 直接运行单个演示

```bash
clj -M -m quit-art.demos.demo-01-basic-shapes
```

## 演示说明 / Demos

每个演示都展示了不同的生成艺术技术，从基础到高级逐步递进。

### Demo 01: 基础图形 (Basic Shapes)
- **复杂度**: ⭐
- **概念**: 基本几何绘图、坐标系统、静态构图
- **技术**: `line`, `ellipse`, `stroke`, `fill`
- **灵感**: 学习 Processing/Quil 的基础绘图 API

### Demo 02: 彩色图案 (Colors & Patterns)
- **复杂度**: ⭐⭐
- **概念**: 网格布局、色彩空间 (HSB)、数学函数的艺术应用
- **技术**: `doseq` 循环、HSB 色彩模式、三角函数
- **灵感**: 系统化的重复与变化

### Demo 03: 动画 (Animation)
- **复杂度**: ⭐⭐
- **概念**: 帧动画、状态管理、变换矩阵
- **技术**: `fun-mode` middleware、`update-state`、`rotate`、`push-matrix`/`pop-matrix`
- **灵感**: 运动中的几何美学

### Demo 04: Perlin 噪声流场 (Perlin Flow)
- **复杂度**: ⭐⭐⭐
- **概念**: Perlin 噪声、粒子系统、流场可视化
- **技术**: `q/noise`、向量场、粒子轨迹、渐变透明度
- **灵感**: 自然界的有机流动形态

### Demo 05: 生成式线条 (Generative Lines)
- **复杂度**: ⭐⭐⭐⭐
- **概念**: Tyler Hobbs 风格、噪声位移、曲线艺术
- **技术**: `curve-vertex`、Perlin 噪声扰动、分层构图
- **灵感**: [Tyler Hobbs 的《Fidenza》系列](https://www.tylerxhobbs.com/fidenza)

### Demo 06: 递归细分 (Recursive Subdivisions)
- **复杂度**: ⭐⭐⭐⭐
- **概念**: 递归算法、空间分割、随机性与控制的平衡
- **技术**: 递归函数、二叉空间分割 (BSP)、概率决策
- **灵感**: [Piet Mondrian 蒙德里安风格](https://en.wikipedia.org/wiki/Piet_Mondrian) 与 Tyler Hobbs 的现代诠释

### Demo 07: 高级流场 (Advanced Flow Fields)
- **复杂度**: ⭐⭐⭐⭐⭐
- **概念**: 多层噪声、复杂流场、颜色映射
- **技术**: 多层 Perlin 噪声、边界循环、动态色彩
- **灵感**: 复杂自然系统的可视化

### Demo 08: 圆形填充 (Circle Packing)
- **复杂度**: ⭐⭐⭐⭐⭐
- **概念**: 空间填充算法、碰撞检测、增长算法
- **技术**: 碰撞检测、圆形增长、有机布局
- **灵感**: 细胞结构、泡沫、自然界的空间利用

---

| 演示 | 描述 | 复杂度 | 特点 |
|------|------|--------|------|
| demo-01 | 基础几何图形绘制 | ⭐ | 静态、学习基础 API |
| demo-02 | 彩色网格图案 | ⭐⭐ | 网格系统、HSB 色彩 |
| demo-03 | 旋转动画效果 | ⭐⭐ | 动画循环、矩阵变换 |
| demo-04 | Perlin 噪声流场粒子系统 | ⭐⭐⭐ | 粒子、噪声、有机感 |
| demo-05 | 生成式线条艺术 | ⭐⭐⭐⭐ | Tyler Hobbs 风格、曲线 |
| demo-06 | 递归空间细分 | ⭐⭐⭐⭐ | 递归、空间分割、蒙德里安 |
| demo-07 | 高级流场可视化 | ⭐⭐⭐⭐⭐ | 多层噪声、复杂系统 |
| demo-08 | 圆形填充算法 | ⭐⭐⭐⭐⭐ | 空间填充、碰撞检测 |

## 更多例子 / More Examples

以下示例来自 Quil 官方 `quil-examples` 的 Generative Art 章节（Matt Pearson《Generative Art》一书中的示例）。已引入本仓库，位于 `src/quit_art/demos/gen_art/`，可以像其他 demo 一样直接运行。

运行示例（任意替换编号与名称）：

```bash
clj -M -m quit-art.demos.gen-art.01-cross-with-circle
```

| # | 示例 | 源码 |
|---|------|------|
| 01 | Cross with Circle | [01_cross_with_circle.clj](src/quit_art/demos/gen_art/01_cross_with_circle.clj) |
| 02 | Growing Circle | [02_growing_circle.clj](src/quit_art/demos/gen_art/02_growing_circle.clj) |
| 03 | Concentric Circles (Traces) | [03_concentric_circles.clj](src/quit_art/demos/gen_art/03_concentric_circles.clj) |
| 04 | Fading Horizontal Lines | [04_fading_horizontal_lines.clj](src/quit_art/demos/gen_art/04_fading_horizontal_lines.clj) |
| 05 | Random Scribble | [05_random_scribble.clj](src/quit_art/demos/gen_art/05_random_scribble.clj) |
| 06 | Random Walk Scribble | [06_rand_walk_scribble.clj](src/quit_art/demos/gen_art/06_rand_walk_scribble.clj) |
| 07 | Perlin Noise Scribble | [07_perlin_noise_scribble.clj](src/quit_art/demos/gen_art/07_perlin_noise_scribble.clj) |
| 08 | Sine Wave | [08_sine_wave.clj](src/quit_art/demos/gen_art/08_sine_wave.clj) |
| 09 | Sine Wave with Noise | [09_sine_wave_with_noise.clj](src/quit_art/demos/gen_art/09_sine_wave_with_noise.clj) |
| 10 | Custom Random Function | [10_custom_rand.clj](src/quit_art/demos/gen_art/10_custom_rand.clj) |
| 11 | Dotted Circle | [11_dotted_circle.clj](src/quit_art/demos/gen_art/11_dotted_circle.clj) |
| 12 | Spiral | [12_spiral.clj](src/quit_art/demos/gen_art/12_spiral.clj) |
| 13 | Noisy Spiral | [13_noisy_spiral.clj](src/quit_art/demos/gen_art/13_noisy_spiral.clj) |
| 14 | 100 Noisy Spirals | [14_hundred_noisy_spirals.clj](src/quit_art/demos/gen_art/14_hundred_noisy_spirals.clj) |
| 15 | Custom Noise Circle | [15_custom_noise_circle.clj](src/quit_art/demos/gen_art/15_custom_noise_circle.clj) |
| 16 | Circle from Opposing Lines | [16_circle_from_opposing_lines.clj](src/quit_art/demos/gen_art/16_circle_from_opposing_lines.clj) |
| 17 | Circle from Fading Opposing Lines | [17_circle_from_fading_opposing_lines.clj](src/quit_art/demos/gen_art/17_circle_from_fading_opposing_lines.clj) |
| 18 | Warped Circle from Fading Opposing Lines | [18_warped_circle_from_fading_opposing_lines.clj](src/quit_art/demos/gen_art/18_warped_circle_from_fading_opposing_lines.clj) |
| 19 | Wave Clock | [19_wave_clock.clj](src/quit_art/demos/gen_art/19_wave_clock.clj) |
| 20 | 2D Noise Grid | [20_noise_grid.clj](src/quit_art/demos/gen_art/20_noise_grid.clj) |
| 21 | Squared 2D Noise Grid | [21_squared_noise_grid.clj](src/quit_art/demos/gen_art/21_squared_noise_grid.clj) |
| 22 | Rotating Lines 2D Noise Grid | [22_rotating_lines_noise_grid.clj](src/quit_art/demos/gen_art/22_rotating_lines_noise_grid.clj) |
| 23 | Fluffy Clouds 2D Noise Grid | [23_fluffy_clouds_noise_grid.clj](src/quit_art/demos/gen_art/23_fluffy_clouds_noise_grid.clj) |
| 24 | Animated Fluffy Clouds | [24_animated_fluffy_clouds.clj](src/quit_art/demos/gen_art/24_animated_fluffy_clouds.clj) |
| 25 | Animated Rotated Lines | [25_animated_rotated_lines.clj](src/quit_art/demos/gen_art/25_animated_rotated_lines.clj) |
| 26 | 3D Sphere | [26_sphere.clj](src/quit_art/demos/gen_art/26_sphere.clj) |
| 27 | Noise from a 3D Perspective | [27_noise_perspective.clj](src/quit_art/demos/gen_art/27_noise_perspective.clj) |
| 28 | A Cube of 3D Noise | [28_cloud_cube.clj](src/quit_art/demos/gen_art/28_cloud_cube.clj) |
| 29 | Spiral Sphere | [29_spiral_sphere.clj](src/quit_art/demos/gen_art/29_spiral_sphere.clj) |
| 30 | Random Clicked Circles | [30_random_clicked_circles.clj](src/quit_art/demos/gen_art/30_random_clicked_circles.clj) |
| 31 | OO Circles | [31_oo_circles.clj](src/quit_art/demos/gen_art/31_oo_circles.clj) |
| 32 | Sierpinski Triangle | [32_sierpinski_triangle.clj](src/quit_art/demos/gen_art/32_sierpinski_triangle.clj) |
| 33 | Peter de Jong Attractor | [33_peter_de_jong.clj](src/quit_art/demos/gen_art/33_peter_de_jong.clj) |
| 34 | Mandelbrot Set | [34_mandelbrot_set.clj](src/quit_art/demos/gen_art/34_mandelbrot_set.clj) |

### 公式 / Formulas

**Sierpinski 三角形 (Triangle)**

$$
p_{n+1} = \frac{p_n + v_i}{2}, \quad v_i \in \{v_1, v_2, v_3\}
$$

**Mandelbrot 集**

$$
M = \{ c \in \mathbb{C} \mid z_0 = 0,\ z_{n+1} = z_n^2 + c,\ \sup_n |z_n| < \infty \}
$$

## 项目结构 / Structure

```
quit-art/
├── deps.edn                      # 依赖配置 (Clojure CLI)
├── .gitignore                    # Git 忽略文件
├── README.md                     # 项目文档
├── dev/
│   └── user.clj                  # REPL 辅助函数 (自动加载)
└── src/
    └── quit_art/
        └── demos/                # 所有演示作品
            ├── demo_01_basic_shapes.clj      # 基础图形
            ├── demo_02_colors_patterns.clj   # 彩色图案
            ├── demo_03_animation.clj         # 动画
            ├── demo_04_perlin_flow.clj       # Perlin 流场
            ├── demo_05_generative_lines.clj  # 生成式线条
            ├── demo_06_subdivisions.clj      # 递归细分
            ├── demo_07_flow_fields.clj       # 高级流场
            ├── demo_08_circle_packing.clj    # 圆形填充
            └── gen_art/                      # Quil 示例（31 个）
                ├── 01_cross_with_circle.clj
                ├── 02_growing_circle.clj
                ├── 03_concentric_circles.clj
                └── ...
```

## 依赖 / Dependencies

- Clojure 1.11.1
- Quil 4.3.1563 (Processing 的 Clojure 封装)

## 参考 / References

- [Quil](http://quil.info/) - Clojure/ClojureScript library for creating interactive drawings
- [Quil Examples](https://github.com/quil/quil-examples) - Official Quil examples
- [Tyler Hobbs](https://www.tylerxhobbs.com/works) - Generative artist inspiration
- [Processing](https://processing.org/) - The foundation of Quil

## 学习路径 / Learning Path

### 1. 基础阶段 (Demos 01-02)
学习 Quil 的基本 API，理解坐标系统和色彩模式。

### 2. 进阶阶段 (Demo 03)
掌握动画循环和状态管理，使用 `fun-mode` middleware。

### 3. 算法阶段 (Demo 04)
学习 Perlin 噪声等算法，创造有机的视觉效果。

### 4. 艺术创作 (Demos 05-06)
结合算法与艺术直觉，创作独特的生成艺术作品。

## 创作建议 / Creative Tips

1. **从简单开始**: 先理解基础概念，再尝试复杂效果
2. **实验参数**: 调整数值参数，观察视觉变化
3. **研究艺术家**: 学习 Tyler Hobbs、Vera Molnár、Georg Nees 等生成艺术先驱
4. **保存作品**: 使用 `q/save-frame` 保存你喜欢的画面
5. **交互探索**: 在 REPL 中实时修改代码，立即看到效果

## 下一步 / Next Steps

- 尝试组合多个技术创作新作品
- 添加鼠标/键盘交互 (参考 Quil 文档的 event functions)
- 探索 3D 绘图 (使用 P3D renderer)
- 导出高分辨率图片用于打印
- 学习着色器 (shader) 实现更复杂的视觉效果
