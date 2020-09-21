package com.ssaw.BusinessDescription.controller;


import com.ssaw.BusinessDescription.entity.VarietiesRate;
import com.ssaw.BusinessDescription.service.VarietiesRateService;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.OperationType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易所品种费率的Controller控制层
 * @type:VarietiesRate
 * @version v1.0
 * @author:阙魁
 * @create:2020-09-02
 */
@RestController
@RequestMapping("varietiesRate")
public class VarietiesRateController {

    @Resource
    VarietiesRateService varietiesRateService;

    //查询controller
    @OperLog(message = "查询交易所品种费率数据",operation = OperationType.QUERY)
    @RequestMapping("selectVarietiesRate")
    public Map<String,Object> selectVarietiesRate(String page,String limit,String exchangeNameIds,String rateTypeIds){
        System.out.println("进入了查询Controller");
        //调用Service层 返回结果集map
        Map<String,Object> map =varietiesRateService.selectVarietiesRate(limit,page,exchangeNameIds,rateTypeIds);
        //从结果集中拿出结果
        //接收返回数据
        List<VarietiesRate> varietiesRates= (List<VarietiesRate>) map.get("varietiesRates");
        //接收返回总条数
        int count= (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",varietiesRates);
        //返回数据
        return json;
    }

    //删除方法
    @OperLog(message = "删除交易所品种费率数据",operation = OperationType.DELETE)
    @RequestMapping("deleteVarietiesRate")
    public void deleteVarietiesRate(int exchangeName, int rateType){
        System.out.println("进入删除controller了");
        varietiesRateService.deleteVarietiesRate(exchangeName,rateType);
    }


    //批量刪除
    @OperLog(message = "批量删除交易所品种费率数据",operation = OperationType.DELETE)
    @RequestMapping("deleteVarietiesRate2")
    public void deleteVarietiesRate2(String exchangeName, String rateType){

        System.out.println("进入批量删除controller了");

        varietiesRateService.deleteVarietiesRate2(exchangeName,rateType);

    }

    //增加controller
    @OperLog(message = "增加交易所品种费率数据",operation = OperationType.ADD)
    @RequestMapping("insertVarietiesRate")
    public int insertVarietiesRate(VarietiesRate varietiesRate){
        System.out.println("进入了增加controller了");
        int n=varietiesRateService.selectVarietiesRate2(varietiesRate.getExchangeName(),varietiesRate.getRateType());

        System.out.println("getExchangeName="+varietiesRate.getExchangeName());
        System.out.println("getRateType="+varietiesRate.getRateType());
        System.out.println("flag="+n);
        int i=0;
        if(n>0){
            i=varietiesRateService.insertVarietiesRate(varietiesRate);
            System.out.println("i="+i);
            return i;
        }else {
            System.out.println("添加失败");
            return 0;
        }
    }
    //修改controller
    @OperLog(message = "修改交易所品种费率数据",operation = OperationType.UPDATE)
    @RequestMapping("updateVarietiesRate")
    public int updateVarietiesRate(VarietiesRate varietiesRate){
        System.out.println("进入了修改controller了");
        int i=varietiesRateService.updateVarietiesRate(varietiesRate);
        return i;
    }


}
