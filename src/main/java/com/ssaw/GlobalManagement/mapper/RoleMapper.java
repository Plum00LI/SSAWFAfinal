package com.ssaw.GlobalManagement.mapper;

import com.ssaw.GlobalManagement.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 角色持久层
 * create time: 2020/9/7 15:42
 * version number 1.0
  * @Param: null
 * @return
 */
@Mapper
public interface RoleMapper {
    /**
     * 查询所有角色信息
     * @return RoleList
     */
    public List<Role> selectRole();

}
