package com.goovy.service.impl;

import com.goovy.entity.RoleMenu;
import com.goovy.mapper.RoleMenuMapper;
import com.goovy.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author y1nuo
 * @since 2023-07-15
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}
