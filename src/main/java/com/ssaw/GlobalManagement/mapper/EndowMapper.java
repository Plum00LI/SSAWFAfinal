package com.ssaw.GlobalManagement.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * create by: 佘高鹏
 * description: TODO
 * 权限模块持久层
 * create time: 2020/9/9 8:46
 * version number 1.0
  * @Param: null
 * @return
 */
@Mapper
public interface EndowMapper {
    /**
     * 根据角色删除绑定功能方法
     * @param roleId
     */
    public void deleteEndow(int roleId);

    /**
     * 指定角色绑定功能方法
     * @param rolId
     * @param funId
     */
    public void  insertEndow(int rolId,int funId );
}
