package top.okeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.okeng.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}