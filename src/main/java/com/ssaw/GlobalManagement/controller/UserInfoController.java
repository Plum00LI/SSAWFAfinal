package com.ssaw.GlobalManagement.controller;

import com.ssaw.GlobalManagement.entity.UserInfo;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.service.UserInfoService;
import com.ssaw.GlobalManagement.util.OperationType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserInfo用户信息的控制层
 * @type: controller
 * @version: v1.0
 * @author: plum
 * @date: 2020/09/02
 */
@RestController
@RequestMapping("userInfo")
public class UserInfoController {

    /**
     * 注入UserInfo服务层
     */
    @Resource
    UserInfoService userInfoService;

    /**
     * UI-Table界面请求用户信息数据控制层查询所有用户的方法
     * @param page 当前查询页数
     * @param limit 分页数据条目数
     * @return UI界面要求的数据格式
     */
    @OperLog(message = "用户查询",operation = OperationType.QUERY)
    @RequestMapping("selectUserInfo")
    public Map<String,Object> getUserInfos(String page,String limit){
        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map = userInfoService.selectUserInfo(limit,page);
        //从结果集中拿出结果
        List<UserInfo> userInfos = (List<UserInfo>) map.get("userInfos");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",userInfos);
        //返回数据
        return json;
    }

    /**
     * ajax请求以UserId删除UserInfo的控制层方法
     * @param userId 请求数据中的要删除UserInfo的UserId
     * @return 执行信息
     */
    @RequestMapping("deleteUserInfoByUserId")
    public Map<String,Object> deleteUserInfoByUserId(String userId){
        //调用Service层执行删除方法，并接收返回结果
        boolean result = userInfoService.deleteUserInfoByUserId(userId);
        //判断结果，响应数据
        Map<String, Object> json = new HashMap<>();
        if (result){
            //成功返回msg信息success
            json.put("msg","success");
        }else {
            //失败返回msg信息fail
            json.put("msg","fail");
        }
        //返回数据
        return json;
    }
}
