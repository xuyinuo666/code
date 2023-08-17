package com.goovy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.goovy.oauth2.dto.UserDTO;
import com.goovy.entity.User;
import com.goovy.mapper.UserMapper;
import com.goovy.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author y1nuo
 * @since 2023-07-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserDTO getUserByUserId(Long userId) {
        User user = this.lambdaQuery().eq(User::getId, userId).one();
        return BeanUtil.copyProperties(user, UserDTO.class);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = this.lambdaQuery().eq(User::getUsername, username).one();
        return BeanUtil.copyProperties(user, UserDTO.class);
    }
}
