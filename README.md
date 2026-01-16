# quit-art

使用Clojure的quil库, 来生成漂亮的数字艺术作品

受 [Tyler Hobbs](https://www.tylerxhobbs.com/works) 艺术作品启发, 从简单到复杂创建生成式艺术。

## 安装 / Installation

确保已安装 Clojure CLI 工具。项目使用 `deps.edn` 进行依赖管理。

```bash
# 克隆仓库
git clone https://github.com/zhaoyul/quit-art.git
cd quit-art
```

## 使用方法 / Usage

### 通过 REPL 交互运行

启动 REPL:

```bash
clj
```

REPL 启动后会自动加载所有 demos，你可以看到可用的演示列表。运行任意演示:

```clojure
;; 查看所有演示
(list-demos)

;; 运行演示 1: 基础图形
(demo01/basic-shapes)

;; 运行演示 2: 彩色图案
(demo02/colors-patterns)

;; 运行演示 3: 动画
(demo03/animation)

;; 运行演示 4: Perlin 噪声流场
(demo04/perlin-flow)

;; 运行演示 5: 生成式线条艺术
(demo05/generative-lines)

;; 运行演示 6: 递归细分
(demo06/subdivisions)
```

### 直接运行单个演示

```bash
clj -M -m quit-art.demos.demo-01-basic-shapes
```

## 演示说明 / Demos

| 演示 | 描述 | 复杂度 |
|------|------|--------|
| demo-01-basic-shapes | 基础几何图形绘制 | ⭐ |
| demo-02-colors-patterns | 彩色网格图案 | ⭐⭐ |
| demo-03-animation | 旋转动画效果 | ⭐⭐ |
| demo-04-perlin-flow | Perlin 噪声流场粒子系统 | ⭐⭐⭐ |
| demo-05-generative-lines | 生成式线条艺术 (Tyler Hobbs 风格) | ⭐⭐⭐⭐ |
| demo-06-subdivisions | 递归空间细分 (Tyler Hobbs 风格) | ⭐⭐⭐⭐ |

## 项目结构 / Structure

```
quit-art/
├── deps.edn                      # 依赖配置
├── dev/
│   └── user.clj                  # REPL 辅助函数
└── src/
    └── quit_art/
        └── demos/                # 所有演示
            ├── demo_01_basic_shapes.clj
            ├── demo_02_colors_patterns.clj
            ├── demo_03_animation.clj
            ├── demo_04_perlin_flow.clj
            ├── demo_05_generative_lines.clj
            └── demo_06_subdivisions.clj
```

## 依赖 / Dependencies

- Clojure 1.11.1
- Quil 4.3.1563 (Processing 的 Clojure 封装)

## 参考 / References

- [Quil](http://quil.info/) - Clojure/ClojureScript library for creating interactive drawings
- [Quil Examples](https://github.com/quil/quil-examples) - Official Quil examples
- [Tyler Hobbs](https://www.tylerxhobbs.com/works) - Generative artist inspiration
- [Processing](https://processing.org/) - The foundation of Quil
