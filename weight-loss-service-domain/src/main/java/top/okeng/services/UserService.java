package top.okeng.services;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.okeng.entity.User;
import top.okeng.exception.BaseError;
import top.okeng.mapper.UserMapper;
import top.okeng.repository.UserRepository;

/**
 * @author ray
 * @description
 * @since 2026/1/28
 */


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password) {
        // 检查用户名是否已存在
        if (userRepository.findByUsername(username).isPresent()) {
            throw BaseError.USERNAME_EXISTS.getException();
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.insert(user) == 1 ? user : null;
    }
}