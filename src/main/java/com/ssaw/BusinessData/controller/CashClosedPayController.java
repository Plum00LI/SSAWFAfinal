package com.ssaw.BusinessData.controller;


import com.ssaw.BusinessData.entity.CashClosedPay;
import com.ssaw.BusinessData.service.CashClosedPayService;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.OperationType;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName CashClosedPayController
 * @Description: TODO
 * @Author 阙魁
 * @Date create in 9:53 2020/9/4
 * @Version 1.0
 **/
@RestController
@RequestMapping("cashClosedPay")
public class CashClosedPayController {
    @Resource
    CashClosedPayService cashClosedPayService;
    @Resource
    DbUtil dbUtil;

    @OperLog(message = "添加现金应收应付数据",operation = OperationType.ADD)
    @RequestMapping("insertCashClosedPay")
    public int insertCash(CashClosedPay cashClosedPay){
        System.out.println("进入了新增Controller");
        cashClosedPay.setCashClosedPayId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCP));
        int i = cashClosedPayService.insertCashClosedPay(cashClosedPay);
        return i;
    };
    @OperLog(message = "删除现金应收应付数据",operation = OperationType.DELETE)
    @RequestMapping("deleteCashClosedPay")
    public int deleteCashClosedPay(String cashClosedPayId){
        System.out.println("进入了删除Controller");
        int i = cashClosedPayService.deleteCashClosedPay(cashClosedPayId);
        return i;
    };
    @OperLog(message = "修改现金应收应付数据",operation = OperationType.UPDATE)
    @RequestMapping("updateCashClosedPay")
    public int updateCashClosedPay(CashClosedPay cashClosePay){
        System.out.println("进入了修改Controller");
        System.out.println(cashClosePay.toString());
        int i = cashClosedPayService.updateCashClosedPay(cashClosePay);
        return i;
    };
    @OperLog(message = "查询现金应收应付数据",operation = OperationType.QUERY)
    @RequestMapping("selectCashClosedPay")
    public Map<String,Object> selectCashClosedPay(String page, String limit,String dateTime,String serviceType){
        System.out.println("进入了查询Controller");
        //调用Service层 返回结果集map
        System.out.println(dateTime);
        System.out.println(serviceType);
        Map<String,Object> map =cashClosedPayService.selectCashClosedPay(limit,page,dateTime,serviceType);
        //从结果集中拿出结果
        //接收返回数据
        List<CashClosedPay> cashClosedPays= (List<CashClosedPay>) map.get("cashClosedPays");
        //接收返回总条数
        int count= (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",cashClosedPays);
        System.out.println(cashClosedPays.toString());
        //返回数据
        return json;
    };

    //傅赛赢
    @OperLog(message = "删除现金接口",operation = OperationType.DELETE)
    @RequestMapping("deleteNew2")
    public int deleteNew2(CashClosedPay cashClosedPay){
        int i = cashClosedPayService.deleteNew2(cashClosedPay);
        return i;
    }
}
