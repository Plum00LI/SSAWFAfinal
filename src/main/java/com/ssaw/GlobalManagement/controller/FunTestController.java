package com.ssaw.GlobalManagement.controller;

import com.ssaw.GlobalManagement.entity.FunTest;
import com.ssaw.GlobalManagement.service.FunTestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 功能测试处理器
 * create time: 2020/9/5 13:53
 * version number 1.0
  * @Param: null
 * @return
 */
@RestController
@RequestMapping("FunTest")
public class FunTestController {
    @Resource
    FunTestService funTestService;

    @RequestMapping("SelectFunTest")
    @ResponseBody
    public List<FunTest> testFun(){
        System.out.println("进来了");
        List<FunTest> funTestList = funTestService.selectPidFun(0,3);
        for (FunTest funTest : funTestList) {
            System.out.println(funTest);
        }
        return funTestList;
    }

}
