package com.ssaw.GlobalManagement.service;

import com.ssaw.GlobalManagement.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 角色业务层
 * create time: 2020/9/7 15:47
 * version number 1.0
  * @Param: null
 * @return
 */
@Service
public interface RoleService {
    public List<Role> selectRole();

}
