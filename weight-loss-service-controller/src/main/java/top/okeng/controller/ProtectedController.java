package top.okeng.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/protected")
public class ProtectedController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from protected endpoint! Authentication successful!";
    }

    @GetMapping("/user-info")
    public String getUserInfo() {
        // 这里可以获取当前认证用户的信息
        return "This is user-specific protected data";
    }
}
