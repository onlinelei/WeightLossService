# React Native 认证服务

一个基于 Spring Boot 的轻量级 JWT 认证微服务

## 🚀 特性

- 🔐 **JWT 认证** - 基于 JSON Web Token 的无状态认证
- 👥 **用户管理** - 用户注册、登录、权限管理
- 🛡️ **安全防护** - Spring Security 集成，BCrypt 密码加密
- 📱 **跨平台支持** - 专为 React Native 应用优化
- 🗄️ **内存数据库** - 使用 H2 数据库，开箱即用
- 🔧 **简单配置** - 最小化配置，快速上手

## 📋 技术栈

- **Java 8** - 编程语言
- **Spring Boot 2.7.18** - 后端框架
- **Spring Security** - 安全框架
- **JWT** - 认证令牌
- **H2 Database** - 内存数据库
- **Maven** - 依赖管理

## 🏁 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6+

### 安装和运行

1. **克隆项目**
   ```bash
   git clone https://github.com/your-username/react-native-auth-service.git
   cd react-native-auth-service
   

2. 接口文档

```shell
// 注册用户
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "ray",
    "password": "123",
    "email": "test@example.com"
  }'
// 登录
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "ray",
    "password": "123"
  }'

// 请求Demo接口
curl -X GET "http://localhost:8080/api/protected/hello" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYXkiLCJleHAiOjE3Njk2MzQ0NDYsImlhdCI6MTc2OTU5ODQ0Nn0.Oae7ORmChDVsea1R_u-Vbyg4xHvbChiUwaAVD47TeOI"


```