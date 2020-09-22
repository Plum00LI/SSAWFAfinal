package com.ssaw.GlobalManagement.controller;

import com.ssaw.GlobalManagement.entity.Fun;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.service.FunService;
import com.ssaw.GlobalManagement.util.OperationType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 角色功能控制层
 * create time: 2020/9/8 16:46
 * version number 1.0
  * @Param: null
 * @return
 */
@RestController
@RequestMapping("Fun")
public class FunController {
    @Resource
    FunService funService;

    @RequestMapping("SelectFun")
    @ResponseBody
    @OperLog(message = "功能查询方法",operation = OperationType.QUERY)
    public List<Fun> selectFun(int roleId){
        List<Fun> fun = funService.selectFun(0, roleId);

        return fun;
    }
}
