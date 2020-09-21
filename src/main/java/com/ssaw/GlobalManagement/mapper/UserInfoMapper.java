package com.ssaw.GlobalManagement.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


/**
 * UserInfo-Mapper接口
 * @type: mapper
 * @version: v1.0
 * @author: plum
 * @date: 2020/09/02
 */
@Mapper
public interface UserInfoMapper {

    //查询所有用户的UserMapper方法
    public void selectUserInfo(Map map);

    //通过ID删除用户的UserMapper方法
    public int deleteUserInfoByUserId(int userId);

    //判断用户登录
    public int isLogin(String userName,String userPwd);
}
