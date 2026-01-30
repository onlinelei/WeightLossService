# WeightLossService

WeightLossService 后端服务，一个基于 Spring Boot 的轻量级 JWT 认证微服务系统

## 🚀 特性

- 🔐 **JWT 认证** - 基于 JSON Web Token 的无状态认证
- 👥 **用户管理** - 用户注册、登录、权限管理
- 🛡️ **安全防护** - Spring Security 集成，BCrypt 密码加密
- 📱 **跨平台支持** - 专为前端应用优化
- 🗄️ **数据库支持** - 支持 MySQL 数据库
- 🔧 **模块化架构** - 基于多模块微服务设计
- ⚡ **MyBatis-Plus** - ORM 框架集成，简化数据库操作
- 📊 **体重管理** - 提供完整的体重记录和追踪功能

## 🏗️ 项目架构

该项目采用多模块微服务架构设计，主要包括以下模块：

- **weight-loss-service-bootstrap** - 项目启动模块，负责应用初始化
- **weight-loss-service-common** - 公共模块，包含基础类、异常处理、RPC 模板等
- **weight-loss-service-controller** - 控制层模块，处理 HTTP 请求和 JWT 认证
- **weight-loss-service-domain** - 业务逻辑层，包含服务实现和业务处理
- **weight-loss-service-dao** - 数据访问层，包含实体类和数据访问接口

### 微服务设计

- **RPC 模板** - 提供 [SOAProviderTemplate](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-common/src/main/java/top/okeng/rpc/template/SOAProviderTemplate.java) 和 [WebProviderTemplate](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-common/src/main/java/top/okeng/rpc/template/WebProviderTemplate.java) 统一的服务调用模板
- **异常处理** - 统一的异常处理机制，包含 [BizException](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-common/src/main/java/top/okeng/exception/BizException.java) 和 [BaseError](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-common/src/main/java/top/okeng/exception/BaseError.java) 错误码定义
- **响应封装** - 统一的响应格式 [RPCBaseResult](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-common/src/main/java/top/okeng/rpc/response/RPCBaseResult.java) 和 [WebBaseResult](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-common/src/main/java/top/okeng/rpc/response/WebBaseResult.java)

## 📋 技术栈

- **Java 8** - 编程语言
- **Spring Boot 2.7.18** - 后端框架
- **Spring Security** - 安全框架
- **JWT** - 认证令牌
- **MySQL** - 数据库
- **MyBatis-Plus** - ORM 框架
- **Maven** - 依赖管理
- **MapStruct** - 对象映射工具
- **Fastjson2** - JSON 解析库

## 📁 目录结构

```
WeightLossService/
├── doc/                    # 文档目录
│   ├── API.md             # API 接口文档
│   └── DB.md              # 数据库设计文档
├── weight-loss-service-bootstrap/    # 启动模块
├── weight-loss-service-common/       # 公共模块
├── weight-loss-service-controller/   # 控制层模块
├── weight-loss-service-domain/       # 业务逻辑层
├── weight-loss-service-dao/          # 数据访问层
└── pom.xml                # 项目依赖配置
```

## 🏁 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6+
- MySQL 

### 安装和运行

1. **克隆项目**
   ```bash
   git clone https://github.com/your-username/WeightLossService.git
   cd WeightLossService
   ```

2. **编译项目**
   ```bash
   mvn clean install -U
   ```

3. **配置数据库** (参考 [doc/DB.md](file:///Users/ray/meworkspace/WeightLossService/doc/DB.md))
   ```sql
   CREATE DATABASE IF NOT EXISTS weight-loss 
   CHARACTER SET utf8mb4 
   COLLATE utf8mb4_unicode_ci;
   ```

4. **修改配置** - 根据需要修改 [application-dev.yml](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-bootstrap/src/main/resources/application-dev.yml) 中的数据库连接信息

5. **启动服务**
   ```bash
   cd weight-loss-service-bootstrap
   mvn spring-boot:run
   ```

## 🌐 API 接口

完整的 API 接口文档请参见 [API.md](file:///Users/ray/meworkspace/WeightLossService/doc/API.md)

主要功能包括：
- 用户认证（注册、登录、登出、刷新Token）
- 用户设置管理
- 体重记录管理（增删改查、统计分析）

## 🛠️ 开发指南

### 模块依赖关系

- [weight-loss-service-bootstrap](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-bootstrap/pom.xml) → [weight-loss-service-controller](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-controller/pom.xml)
- [weight-loss-service-controller](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-controller/pom.xml) → [weight-loss-service-domain](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-domain/pom.xml)
- [weight-loss-service-domain](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-domain/pom.xml) → [weight-loss-service-dao](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-dao/pom.xml)
- 所有模块 → [weight-loss-service-common](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-common/pom.xml)

### 配置说明

- JWT 配置: 参考 [application.yml](file:///Users/ray/meworkspace/WeightLossService/weight-loss-service-bootstrap/src/main/resources/application.yml) 中的 jwt 配置项
- 数据库配置: 修改对应环境的配置文件（dev/prod）
- MyBatis-Plus 配置: ORM 框架相关配置

## 📝 文档

- [API 文档](file:///Users/ray/meworkspace/WeightLossService/doc/API.md) - 详细的接口说明
- [数据库设计](file:///Users/ray/meworkspace/WeightLossService/doc/DB.md) - 数据库表结构和初始化脚本

## 🤝 贡献

欢迎提交 Issue 和 Pull Request 来帮助改进此项目。

## 📄 许可证

MIT License