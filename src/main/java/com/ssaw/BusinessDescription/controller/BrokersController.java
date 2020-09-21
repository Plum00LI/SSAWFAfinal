package com.ssaw.BusinessDescription.controller;

import com.ssaw.BusinessDescription.entity.Brokers;
import com.ssaw.BusinessDescription.service.BrokersService;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:TescComment
 * @Description:控制层
 * @author:孙浩
 * @create:2020-09-07
 */
@RestController
public class BrokersController {
    @Resource
    BrokersService brokersService;
    @Resource
    DbUtil dbUtil;

    @RequestMapping("insertBrokers")
    public int insertBrokers(Brokers brokers){
        brokers.setBrokersId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.B));
        int i = brokersService.insertBrokers(brokers);
        return i;
    }

    @RequestMapping("deleteBrokers")
    public int deleteBrokers(String brokersId){
        int i = brokersService.deleteBrokers(brokersId);
        return i;
    }

    @RequestMapping("updateBrokers")
    public int updateBrokers(Brokers brokers){
        return brokersService.updateBrokers(brokers);
    }

    @RequestMapping("selectBrokers")
    public Map<String,Object> selectBrokers(String page,String limit,String brokersName){
        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map = brokersService.selectBrokers(limit,page,brokersName);
        List<Brokers> brokersList = (List<Brokers>) map.get("brokersList");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> brokersMap = new HashMap<>();
        brokersMap.put("code",0);
        brokersMap.put("msg","");
        brokersMap.put("count",count);
        brokersMap.put("data",brokersList);
        //返回数据
        System.out.println(brokersList);
        return brokersMap;
    }
}
