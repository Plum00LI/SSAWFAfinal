package com.ssaw.GlobalManagement.service;

import org.springframework.stereotype.Service;

/**
 * create by: 佘高鹏
 * description: TODO
 * 权限模块业务层
 * create time: 2020/9/9 9:04
 * version number 1.0
  * @Param: null
 * @return
 */
@Service
public interface EndowService {
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
