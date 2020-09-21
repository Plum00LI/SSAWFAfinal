package com.ssaw.GlobalManagement.controller;

import com.ssaw.GlobalManagement.service.EndowService;
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
    public void endows(String strIds,String roldId){
        System.out.println(strIds);
        System.out.println(roldId);
        int roleId=0;
        if(roldId!=null && !roldId.equals(""))
        {
            roleId=Integer.parseInt(roldId);
        }

        String[] ids = strIds.split(",");
        //先根据角色ID 删除所有的功能
        endowService.deleteEndow(roleId);
            for (String id : ids) {
                int funId=0;
                if(id!=null && !id.equals(""))
                {
                    funId=Integer.parseInt(id);
                }
                endowService.insertEndow(roleId,funId);
            }
        }





}
