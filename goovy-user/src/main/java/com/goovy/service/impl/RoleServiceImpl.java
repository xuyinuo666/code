package com.goovy.service.impl;

import com.goovy.entity.Role;
import com.goovy.mapper.RoleMapper;
import com.goovy.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author y1nuo
 * @since 2023-07-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
