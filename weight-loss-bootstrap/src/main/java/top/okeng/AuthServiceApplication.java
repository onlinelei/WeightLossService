package top.okeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ray
 * @description
 * @since 2026/1/28
 */
@SpringBootApplication
@MapperScan("top.okeng.mapper")  // 添加这行
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}