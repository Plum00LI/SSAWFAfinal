package com.ssaw.GlobalManagement.controller;

import com.ssaw.GlobalManagement.entity.Role;
import com.ssaw.GlobalManagement.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 角色控制层
 * create time: 2020/9/7 15:56
 * version number 1.0
  * @Param: null
 * @return
 */
@RestController
@RequestMapping("Role")
public class RoleController {
    @Resource
    RoleService roleService;

    @RequestMapping("selectRole")
    @ResponseBody
    public Object selectRole(){
        System.out.println("进来了");
        List<Role> roleList= roleService.selectRole();
        for (Role role : roleList) {
            if(role.getStatus()==1){
                role.setStatusRole("启用");
            }else if(role.getStatus()==0){
                role.setStatusRole("禁用");
            }
        }
        HashMap roldMap=new HashMap();
        roldMap.put("code",0);
        roldMap.put("msg","");
        roldMap.put("count",roleList.size());
        roldMap.put("data",roleList);
        return roldMap;
    }
}
