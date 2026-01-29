package top.okeng.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.okeng.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 不需要定义 selectOne，BaseMapper 已经提供
    // 可以添加其他自定义查询方法
}