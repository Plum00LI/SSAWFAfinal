package com.ssaw.BusinessProcessing.controller;

import com.ssaw.BusinessProcessing.entity.EquityDispose;
import com.ssaw.BusinessProcessing.service.EquityDisposeService;
import com.ssaw.GlobalManagement.util.DbUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:TescComment
 * @Description:实体类
 * @author:孙浩
 * @create:2020-09-01
 */
@RestController
public class EquityDisposeController {
    @Resource
    EquityDisposeService equityDisposeService;
    @Resource
    DbUtil dbUtil;


    @RequestMapping("selectEquityDispose")
    public Map<String,Object> selectEquityDispose(String page, String limit, String equitiesType,String equitiesExright, String disposeStatus, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        String accountName = (String) session.getAttribute("accountName");


        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map = equityDisposeService.selectEquityDispose(limit,page,equitiesType,equitiesExright,disposeStatus);
        List<EquityDispose> equityDisposeList = (List<EquityDispose>) map.get("equityDisposeList");
        for (EquityDispose equityDispose : equityDisposeList){
            equityDispose.setAccountName(accountName);
        }

        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> equityDisposeMap = new HashMap<>();
        equityDisposeMap.put("code",0);
        equityDisposeMap.put("msg","");
        equityDisposeMap.put("count",count);
        equityDisposeMap.put("data",equityDisposeList);
        //返回数据
        System.out.println(equityDisposeList);
        return equityDisposeMap;
    }

    @RequestMapping("updateEquityDispose")
    public int updateEquityDispose(String equityDisPose){

        return equityDisposeService.updateEquityDispose(equityDisPose);
    }

    @RequestMapping("updateEquityDisposeTwo")
    public int updateEquityDisposeTwo(String equityDisPose){
        return equityDisposeService.updateEquityDisposeTwo(equityDisPose);
    }
}
