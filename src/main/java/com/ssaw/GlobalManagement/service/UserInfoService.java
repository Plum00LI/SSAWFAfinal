package com.ssaw.GlobalManagement.service;

import com.ssaw.GlobalManagement.entity.UserInfo;

import java.util.Map;

/**
 * UserInfo服务类接口
 * @type: service_interface
 * @version: v1.0
 * @author: plum
 * @date: 2020/09/02
 */
public interface UserInfoService {

    //查询所有用户的服务类接口方法-待实现
    public Map<String,Object> selectUserInfo(String pageSize,String page);

    //通过UserId删除用户的服务类接口方法-待实现
    public boolean deleteUserInfoByUserId(String userId);

    //判断用户登录
    public int isLogin(String userName,String userPwd);

    //增加用户方法
    public int insertUserInfo(UserInfo userInfo);
}
