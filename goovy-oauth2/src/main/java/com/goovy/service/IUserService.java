package com.goovy.service;

import com.goovy.oauth2.dto.UserDTO;
import com.goovy.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author y1nuo
 * @since 2023-07-10
 */
public interface IUserService extends IService<User> {
    UserDTO getUserByUserId(Long userId);
    UserDTO getUserByUsername(String username);

}
