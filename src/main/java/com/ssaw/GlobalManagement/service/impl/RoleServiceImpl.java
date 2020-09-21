package com.ssaw.GlobalManagement.service.impl;

import com.ssaw.GlobalManagement.entity.Role;
import com.ssaw.GlobalManagement.mapper.RoleMapper;
import com.ssaw.GlobalManagement.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 角色业务层实现类
 * create time: 2020/9/7 15:48
 * version number 1.0
  * @Param: null
 * @return
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;
    @Override
    public List<Role> selectRole() {
        return roleMapper.selectRole();
    }
}
