# 智能学情与在线教学习题评测平台

> 🚧 项目开发中 | 面向管理员 / 教师 / 学生的在线教学测评平台

## 📖 项目简介

一个支持 **管理员、教师、学生** 三角色的在线教学测评平台，覆盖 **班级管理、题库维护、试卷发布、在线答题、主观题批阅、学情分析** 等完整教学场景。

平台支持 **客观题自动判分** 与 **主观题教师人工阅卷** 两种模式，并规划集成 AI 智能组卷与个性化习题推荐能力。

## 👥 核心角色

| 角色 | 主要功能 |
| :--- | :--- |
| **平台管理员** | 用户管理、题目审核、系统监控 |
| **任课教师** | 班级管理、试卷发布、学情分析、主观题批阅 |
| **学生** | 加入班级、在线答题、查看成绩与评语 |

## 🚧 开发进度

### ✅ 已完成

- [x] 项目骨架搭建（`com.exam` 分层架构）
- [x] RBAC 权限体系（管理员 / 教师 / 学生三角色）
- [x] JWT 无状态登录 + `@PreAuthorize` 接口级权限拦截
- [x] 全局异常处理、统一返回结果 `Result<T>`、TraceId 链路追踪
- [x] MyBatis-Plus 分页插件与字段自动填充
- [x] Knife4j 在线接口文档
- [x] 基础数据库表设计（用户 / 角色 / 权限 / 班级 / 知识点 / 题目）

### 🚧 进行中

- [ ] 班级管理模块（创建班级、导入学生、邀请码加入）
- [ ] 题库管理模块（多题型 CRUD、按知识点/难度筛选）
- [ ] 试卷管理模块（手动组卷、发布到班级）

### 📅 规划中

- [ ] 答题与判分（客观题自动判分 + 主观题待阅卷）
- [ ] 教师阅卷（打分、评语、状态流转）
- [ ] 学情统计与可视化（ECharts 看板）
- [ ] Kafka 事件流（异步判分、画像更新、消息推送）
- [ ] AI 智能组卷 + 个性化习题推荐（Python FastAPI 独立服务）
- [ ] 学生端 Android App（Kotlin）
- [ ] 管理后台 Vue3（Element Plus + ECharts）
- [ ] 单元测试（JUnit5 + Mockito）

## 🛠️ 技术栈

| 分类 | 技术 |
| :--- | :--- |
| 后端框架 | Spring Boot 4.0.8 + Spring MVC |
| ORM | MyBatis-Plus 3.5.16 |
| 安全 | Spring Security 7 + JWT（RBAC 三角色） |
| 数据库 | MySQL 8.0 |
| 接口文档 | Knife4j + springdoc-openapi |
| 测试 | JUnit5 + Mockito（规划中） |
| 缓存/分布式 | Redis（规划中） |
| 消息队列 | Apache Kafka（规划中） |
| AI 服务 | Python FastAPI（规划中） |
| 管理后台 | Vue3 + Element Plus + ECharts（规划中） |
| 学生端 | Android Kotlin + Retrofit（规划中） |

## 🚀 本地启动

### 环境要求

- JDK 17+
- MySQL 8.0+
- Maven 3.8+

### 启动步骤

1. 执行建表 SQL，创建 `exam_platform` 数据库
2. 复制 `src/main/resources/application.properties.example` 为 `application.properties`，修改数据库账号密码
3. 启动项目：

   ```bash
   ./mvnw spring-boot:run
