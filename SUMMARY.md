# Project Summary / 项目总结

## 项目概述 / Project Overview

这是一个完整的 Clojure/Quil 生成艺术项目，从基础到高级循序渐进，共包含 8 个精心设计的演示。项目受 Tyler Hobbs 等生成艺术家启发，展示了如何使用代码创作视觉艺术。

This is a complete Clojure/Quil generative art project with 8 progressively complex demos, inspired by Tyler Hobbs and other generative artists.

## 技术栈 / Tech Stack

- **Language**: Clojure 1.11.1
- **Graphics Library**: Quil 4.3.1563 (Clojure wrapper for Processing)
- **Build Tool**: Clojure CLI (deps.edn)
- **Development**: REPL-driven interactive development

## 项目特色 / Key Features

### 1. 渐进式学习路径
从最基础的几何图形开始，逐步引入动画、噪声算法、递归等高级概念。

Progressive learning path from basic shapes to advanced algorithms.

### 2. REPL 交互式开发
所有演示都可以在 REPL 中实时加载和修改，支持探索式学习。

All demos are REPL-friendly and support live coding.

### 3. 生成艺术算法
- Perlin 噪声 (Perlin Noise)
- 粒子系统 (Particle Systems)
- 流场可视化 (Flow Fields)
- 递归空间分割 (Recursive Space Partitioning)
- 圆形填充算法 (Circle Packing)

### 4. 双语文档
中英文双语文档，包括详细的技术说明和艺术概念解释。

Comprehensive bilingual documentation (Chinese/English).

### 5. 最佳实践
- Fun-mode middleware for state management
- Clean code structure with proper namespacing
- Well-commented code explaining concepts
- Extracted constants and meaningful variable names

## 演示列表 / Demo List

| # | 名称 | 复杂度 | 类型 | 主要概念 |
|---|------|--------|------|----------|
| 01 | Basic Shapes | ⭐ | 静态 | 基础绘图 API |
| 02 | Colors & Patterns | ⭐⭐ | 静态 | 网格系统、HSB 色彩 |
| 03 | Animation | ⭐⭐ | 动画 | 状态管理、变换矩阵 |
| 04 | Perlin Flow | ⭐⭐⭐ | 动画 | Perlin 噪声、粒子系统 |
| 05 | Generative Lines | ⭐⭐⭐⭐ | 静态 | 曲线艺术、噪声扰动 |
| 06 | Subdivisions | ⭐⭐⭐⭐ | 静态 | 递归算法、空间分割 |
| 07 | Flow Fields | ⭐⭐⭐⭐⭐ | 动画 | 多层噪声、复杂流场 |
| 08 | Circle Packing | ⭐⭐⭐⭐⭐ | 动画 | 碰撞检测、增长算法 |

## 文件结构 / File Structure

```
quit-art/
├── README.md                     # 主要文档
├── QUIL_REFERENCE.md            # Quil 快速参考
├── deps.edn                      # 依赖配置
├── .gitignore                    # Git 忽略文件
├── dev/
│   └── user.clj                  # REPL 辅助工具
└── src/
    └── quit_art/
        └── demos/                # 8 个演示
            ├── demo_01_basic_shapes.clj
            ├── demo_02_colors_patterns.clj
            ├── demo_03_animation.clj
            ├── demo_04_perlin_flow.clj
            ├── demo_05_generative_lines.clj
            ├── demo_06_subdivisions.clj
            ├── demo_07_flow_fields.clj
            └── demo_08_circle_packing.clj
```

## 使用指南 / Quick Start

```bash
# 1. 克隆项目 / Clone
git clone https://github.com/zhaoyul/quit-art.git
cd quit-art

# 2. 启动 REPL / Start REPL
clj

# 3. 运行演示 / Run demos
(list-demos)           # 查看所有演示
(demo01/basic-shapes)  # 运行演示 1
(demo05/generative-lines)  # 运行生成式线条艺术
```

## 学习建议 / Learning Recommendations

### 初学者 (Beginners)
1. 从 demo-01 和 demo-02 开始，熟悉 Quil API
2. 阅读 QUIL_REFERENCE.md 了解常用函数
3. 在 REPL 中修改参数，观察效果

### 进阶学习 (Intermediate)
1. 学习 demo-03 的动画和状态管理
2. 理解 demo-04 的 Perlin 噪声应用
3. 尝试修改和组合不同的技术

### 高级创作 (Advanced)
1. 研究 demo-05 到 demo-08 的算法
2. 创作自己的生成艺术作品
3. 探索 3D、着色器等高级特性

## 扩展方向 / Future Directions

1. **交互性**: 添加鼠标/键盘交互
2. **3D 艺术**: 使用 P3D renderer 创作 3D 作品
3. **参数化**: 创建可调参数的 UI 界面
4. **导出**: 高分辨率图片导出和视频录制
5. **NFT**: 探索生成艺术在 NFT 领域的应用
6. **着色器**: 学习 GLSL 着色器编程
7. **音频可视化**: 结合声音创作交互艺术

## 参考资源 / References

### 生成艺术家 (Generative Artists)
- [Tyler Hobbs](https://www.tylerxhobbs.com/) - Fidenza 等作品
- [Vera Molnár](https://en.wikipedia.org/wiki/Vera_Moln%C3%A1r) - 计算机艺术先驱
- [Georg Nees](https://en.wikipedia.org/wiki/Georg_Nees) - 最早的生成艺术家之一
- [Manolo Gamboa Naon](https://www.behance.net/manoloide) - 当代生成艺术

### 技术资源 (Technical Resources)
- [Quil 官方文档](http://quil.info/)
- [Processing 官方网站](https://processing.org/)
- [The Coding Train](https://thecodingtrain.com/) - Processing 视频教程
- [Generative Artistry](https://generativeartistry.com/) - 生成艺术教程

### 书籍推荐 (Books)
- "Generative Design" by Hartmut Bohnacker et al.
- "The Nature of Code" by Daniel Shiffman
- "Algorithms for Visual Design" by Kostas Terzidis

## 贡献指南 / Contributing

欢迎贡献新的演示、改进文档或修复 bug！

Contributions welcome! Feel free to:
- Add new demos
- Improve documentation
- Fix bugs
- Share your artwork

## 许可证 / License

本项目采用开源许可证，具体请参考 LICENSE 文件。

This project is open source. See LICENSE file for details.

## 致谢 / Acknowledgments

感谢 Quil 和 Processing 社区的贡献，以及所有生成艺术先驱的启发。

Thanks to the Quil and Processing communities, and all generative art pioneers for inspiration.

---

**Happy Coding! 享受创作!** 🎨✨
