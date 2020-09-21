package com.ssaw.GlobalManagement.service.impl;

import com.ssaw.GlobalManagement.entity.UserInfo;
import com.ssaw.GlobalManagement.mapper.UserInfoMapper;
import com.ssaw.GlobalManagement.service.UserInfoService;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserInfo服务实现类
 * @type: impl
 * @version: v1.0
 * @author: plum
 * @date: 2020/09/02
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    /**
     * 注入UserInfo的Mapper层
     */
    @Resource
    UserInfoMapper userInfoMapper;

    /**
     * 查询所有用户的实现类方法（带分页，返回数据和总条目数）
     * @param pageSize 当前查询页数
     * @param page 分页数据条目数
     * @return 查询的结果集Map
     */
    @Override
    public Map<String, Object> selectUserInfo(String pageSize,String page) {
        //创建一个结果集Map用于存放两个结果变量
        Map<String, Object> resultMap = new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize = 0;
        //判断传入的pageSize是否为null/空
        if (pageSize!=null&&!pageSize.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_pageSize=Integer.parseInt(pageSize);
        }
        //定义一个分页页码变量
        int v_page = 0;
        //判断传入的page是否为null/空
        if (page!=null&&!page.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_page=Integer.parseInt(page);
        }
        //创建一个Map，用于存储过程的调用传值
        Map<String,Object> map = new HashMap<>();
        String s = "(select * from "+SysTableNameListUtil.UI+" ui join";
        //传入存储过程需要查询的表名
        map.put("p_tableName",SysTableNameListUtil.UI);

        //传入查询条件
        map.put("p_condition","");
        //传入分页显示条数
        map.put("p_pageSize",v_pageSize);
        //传入分页页码
        map.put("p_page",v_page);
        //创建out参数，返回数据总条数
        map.put("p_count",0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor",null);
        //调用Mapper执行查询
        userInfoMapper.selectUserInfo(map);
        //接收返回数据
        List<UserInfo> userInfos = (List<UserInfo>) map.get("p_cursor");
        //接收返回总条数
        int v_count = (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("userInfos",userInfos);
        resultMap.put("count",v_count);
        //返回结果集Map
        return resultMap;
    }

    /**
     * 通过UserId删除用户的实现类方法
     * @param userId 要删除用户的ID
     * @return 成功与否
     */
    @Override
    public boolean deleteUserInfoByUserId(String userId) {
        //定义一个用户ID变量
        int v_userId = 0;
        //判断传入的userId是否为null/空
        if (userId!=null&&!userId.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_userId=Integer.parseInt(userId);
        }
        //调用Mapper执行删除，并接收影响条目数
        int result = userInfoMapper.deleteUserInfoByUserId(v_userId);
        //判断影响条目数是否不等于0
        if (result!=0){
            //不为0，返回成功
            return true;
        }
        //为0，返回失败
        return false;
    }

    /**
     * 判断登录
     * @param userName 用户名
     * @param userPwd 用户密码
     * @return 是否登录 0失败 1成功
     */
    @Override
    public int isLogin(String userName, String userPwd) {
        int status = userInfoMapper.isLogin(userName, userPwd);
        return status;
    }
}
