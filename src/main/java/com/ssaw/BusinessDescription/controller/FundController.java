package com.ssaw.BusinessDescription.controller;

import com.ssaw.BusinessDescription.entity.Fund;
import com.ssaw.BusinessDescription.service.FundService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by: 曾钦辉
 * description: 基金信息Controller控制层
 * create time: 2020/9/1 11:14
 *
 * @Param: null
 * @return
 */
@RestController
public class FundController {
    @Resource
    FundService fundService;


    @RequestMapping(value = "insertFund")
    public int insertFund(Fund fund) {
        System.out.println("进来了");


        System.out.println(fund);
        return fundService.insertFund(fund);
    }

    @RequestMapping(value = "deleteFund")
    public void deleteFund(String fundId) {
        System.out.println("进来了");

        fundService.deleteFund(fundId);

    }

    @RequestMapping(value = "updateFund")
    public int updateFund(Fund fund) {
        System.out.println("进来了");

        System.out.println(fund);
        return fundService.updateFund(fund);
    }

    @RequestMapping(value = "selectFund")
    public Map<String, Object> selectFund(String page, String limit,String fundId,String fundType) {
        System.out.println("查询进来了");
        Map<String, Object> map = fundService.selectFund(limit, page,fundId,fundType);
        System.out.println(fundId+fundType);
        List<Fund> fundList = (List<Fund>) map.get("fundList");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", count);
        json.put("data", fundList);
        //返回数据
        return json;
    }
}
