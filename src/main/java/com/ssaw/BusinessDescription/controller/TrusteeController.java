package com.ssaw.BusinessDescription.controller;
import com.ssaw.BusinessDescription.entity.Trustee;
import com.ssaw.BusinessDescription.service.TrusteeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by: 曾钦辉
 * description: 托管人controller类
 * create time: 2020/9/7 10:55
 *
  * @Param: null
 * @return
 */
@RestController
public class TrusteeController {
    @Resource
    TrusteeService trusteeService;
    @RequestMapping("selectTrustee")
    public Map<String, Object> selectTrustee(String page, String limit) {
        System.out.println("查询托管人进来了");
        Map<String, Object> map = trusteeService.selectTrustee(limit, page);
        System.out.println(page+limit);
        List<Trustee> trusteeList = (List<Trustee>) map.get("trusteeList");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", count);
        json.put("data", trusteeList);
        //返回数据
        return json;
    }
}
