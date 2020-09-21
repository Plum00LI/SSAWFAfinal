package com.ssaw.BusinessDescription.controller;

import com.ssaw.BusinessDescription.entity.Manager;
import com.ssaw.BusinessDescription.service.ManagerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by: 曾钦辉
 * description: 管理人controller层
 * create time: 2020/9/7 16:10
 *
  * @Param: null
 * @return
 */
@RestController
public class ManagerController {
    @Resource
    ManagerService managerService;

    @RequestMapping("selectManager")
    public Map<String, Object> selectManager(String page, String limit) {
        System.out.println("查询管理人进来了");
        Map<String, Object> map = managerService.selectManager(limit, page);
        System.out.println(limit+page);
        List<Manager> managerList = (List<Manager>) map.get("managerList");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", count);
        json.put("data", managerList);
        //返回数据
        return json;
    }
}
