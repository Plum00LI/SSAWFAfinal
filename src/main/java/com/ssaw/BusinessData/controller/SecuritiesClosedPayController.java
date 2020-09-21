package com.ssaw.BusinessData.controller;


import com.ssaw.BusinessData.entity.SecuritiesClosedPay;
import com.ssaw.BusinessData.service.SecuritiesClosedPayService;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

/**
 *@program: FA
 *@description: 证券应收应付控制类
 *@author: 瞿平
 *@create: 2020-09-09 15:45
 **/
@RequestMapping("securitiesClosedPay")
@RestController
public class SecuritiesClosedPayController {
    //调用用户Biz对象
    //自动装配 按照类型自动装配
    @Resource
    SecuritiesClosedPayService securitiesClosedPayService;

    //调用工具类
    @Resource
    DbUtil dbUtil;

    /**
     * 分页查询
     * @param page 页码
     * @param limit 每页的条数
     * @param securitiesName 证券名
     * @param dateTime 日期
     * @return
     */
    @RequestMapping("selectSecuritiesClosedPay")
    public Map<String,Object> selectSecuritiesClosedPay(String page, String limit,String securitiesName,String dateTime){
        System.out.println("证券应收应付分页查询控制器");
        Map<String,Object> map = securitiesClosedPayService.selectSecuritiesClosedPay(limit,page,securitiesName,dateTime);
        if (dateTime==null || dateTime.equals("")){
            Date today= new Date();
            dateTime = new SimpleDateFormat("yyyy-MM-dd").format(today);
            System.out.println("dateTime=" + dateTime);
        }
        List<SecuritiesClosedPay> securitiesClosedPayList = (List<SecuritiesClosedPay>) map.get("securitiesClosedPayList");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("code",0);
        hashMap.put("msg","");
        hashMap.put("count",count);
        hashMap.put("data",securitiesClosedPayList);
        System.out.println("信息的大小："+securitiesClosedPayList.size());
        return hashMap;
    }

    /**
     * 证券应收应付增加方法
     * @param securitiesClosedPay 证券应收应付实体对象
     * @return
     */
    @RequestMapping("insertSecuritiesClosedPay")
    public int insertSecuritiesClosedPay(SecuritiesClosedPay securitiesClosedPay){
        System.out.println("新增的控制类");
        securitiesClosedPay.setSecuritiesClosedPayId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.SCP));
        System.out.println("添加"+securitiesClosedPay);
        int i = securitiesClosedPayService.insertSecuritiesClosedPay(securitiesClosedPay);
        System.out.println("证券应收应付新增的条数为"+i);
        return i;
    }

    /**
     * 修改
     * @param securitiesClosedPay 证券应收应付实体对象
     * @return
     */
    @RequestMapping("updateSecuritiesClosedPay")
    public int updateSecuritiesClosedPay(SecuritiesClosedPay securitiesClosedPay){
        System.out.println("进入了修改的control");
        int i =securitiesClosedPayService.updateSecuritiesClosedPay(securitiesClosedPay);
        System.out.println("修改："+i);
        return i;
    }



    /**
     * 单行删除多行删除
     * @param securitiesClosedPayId 证券应收应付Id
     * @return
     */
    @RequestMapping("deleteSecuritiesClosedPay")
    public int deleteSecuritiesClosedPay(@RequestParam("securitiesClosedPayId")String securitiesClosedPayId) {
        return securitiesClosedPayService.deleteSecuritiesClosedPay(securitiesClosedPayId);
    }
}
