package com.goovy.mapper;
import com.goovy.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author y1nuo
 * @since 2023-07-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
