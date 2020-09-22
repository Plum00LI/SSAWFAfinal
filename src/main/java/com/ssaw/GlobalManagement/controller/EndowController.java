package com.ssaw.GlobalManagement.controller;

import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.service.EndowService;
import com.ssaw.GlobalManagement.util.OperationType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * create by: 佘高鹏
 * description: TODO
 * 权限控制层
 * create time: 2020/9/9 9:28
 * version number 1.0
  * @Param: null
 * @return
 */
@RestController
@RequestMapping("Endow")
public class EndowController {
    @Resource
    EndowService endowService;

    @RequestMapping("deInEndow")
    @OperLog(message = "角色绑定模块方法",operation = OperationType.ADD)
    public void endows(String strIds,String roldId){
        //将角色字符串转int类型
        int roleId=0;
        if(roldId!=null && !roldId.equals(""))
        {
            roleId=Integer.parseInt(roldId);
        }
        //将ID字符串切割成数组
        String[] ids = strIds.split(",");
        //先根据角色ID 删除所有的功能
        endowService.deleteEndow(roleId);
        //根据ID循环给指定角色绑定功能
            for (String id : ids) {
                int funId=0;
                //将切割的ID字符串转成int
                if(id!=null && !id.equals(""))
                {
                    funId=Integer.parseInt(id);
                }
                //调用增加方法插入数据库
                endowService.insertEndow(roleId,funId);
            }
        }





}
