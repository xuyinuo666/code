package com.goovy.service.impl;

import com.goovy.pojo.User;
import com.goovy.mapper.UserMapper;
import com.goovy.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author y1nuo
 * @since 2023-07-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
