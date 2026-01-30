package top.okeng.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Repository;
import top.okeng.entity.User;
import top.okeng.mapper.UserMapper;

import java.util.Optional;

@Repository
public class UserRepository {

    private final UserMapper userMapper;

    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int insert(User user) {
        return userMapper.insert(user);
    }

    public Optional<User> findByUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);

        // 直接使用继承的 selectOne 方法
        return Optional.ofNullable(userMapper.selectOne(queryWrapper));
    }


}