package top.okeng.services;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.okeng.entity.User;
import top.okeng.exception.BaseError;
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

    public User registerUser(String username, String password, String confirmPassword) {
        // 检查用户名是否已存在
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password) || !StringUtils.hasLength(confirmPassword)) {
            throw BaseError.ILLEGAL_PARAMETER.getException();
        }

        if (!password.equals(confirmPassword)) {
            throw BaseError.PASSWORD_NOT_MATCH.getException();
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw BaseError.USERNAME_EXISTS.getException();
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.insert(user) == 1 ? user : null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> BaseError.USER_NOT_EXISTS.getException());
    }

    public void changePassword(String username, String oldPassword, String newPassword) {
        if (!StringUtils.hasLength(oldPassword) || !StringUtils.hasLength(newPassword)) {
            throw BaseError.ILLEGAL_PARAMETER.getException();
        }

        User user = findByUsername(username);

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw BaseError.PASSWORD_ERROR.getException();
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.update(user);
    }
}