package com.goovy.service.impl;

import com.goovy.pojo.UserAddr;
import com.goovy.mapper.UserAddrMapper;
import com.goovy.service.IUserAddrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户地址 服务实现类
 * </p>
 *
 * @author y1nuo
 * @since 2023-07-15
 */
@Service
public class UserAddrServiceImpl extends ServiceImpl<UserAddrMapper, UserAddr> implements IUserAddrService {

}
