package com.goovy.service.impl;

import com.goovy.pojo.UserRole;
import com.goovy.mapper.UserRoleMapper;
import com.goovy.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author y1nuo
 * @since 2023-07-15
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
