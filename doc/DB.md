

```sql
-- 创建数据库
CREATE DATABASE IF NOT EXISTS weight-loss 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE weight-loss;

-- 创建用户表
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '加密后的密码',
    enabled TINYINT(1) NOT NULL DEFAULT 1 COMMENT '是否启用',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 创建测试用户 密码：123456
INSERT INTO users (username, password) VALUES
('admin', '$2a$10$rOzZUVb7kCdUQp3qJ5x4E.4bQ4QZ8QjLpLkLmNpQrStUvWwXyZzAb' ),
('testuser', '$2a$10$rOzZUVb7kCdUQp3qJ5x4E.4bQ4QZ8QjLpLkLmNpQrStUvWwXyZzAb');
```

```
# 基本连接测试
mysql -h 8.129.8.11 -P 3306 -u root -p

# 或者指定数据库
mysql -h localhost -u root -p -D auth_service

```