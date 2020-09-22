package com.ssaw.BusinessDescription.controller;

import com.ssaw.BusinessDescription.entity.Securities;
import com.ssaw.BusinessDescription.entity.SecuritiesAndStock;
import com.ssaw.BusinessDescription.entity.Stock;
import com.ssaw.BusinessDescription.service.SecuritiesService;
import com.ssaw.GlobalManagement.entity.UserInfo;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.OperationType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 债券参数
 * @type 控制层
 * @author fusaiying
 * @date 2020-09-01
 * @version 1.0
 */
@RestController
@RequestMapping("Securities")
public class SecuritiesController {
    @Resource
    SecuritiesService securitiesService;

    //查询
    @OperLog(message = "证券板块的查询",operation = OperationType.QUERY)
    @RequestMapping("selectSecurities")
    public Map<String,Object> selectSecurities(String page, String limit,String securitiesId,String exchange,String securitiesType){
        System.out.println(page+limit);
        System.out.println(securitiesId);
        System.out.println(exchange);
        System.out.println(securitiesType);
        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map = securitiesService.selectSecurities(limit,page,securitiesId,exchange,securitiesType);
        System.out.printf(map.toString());
        //从结果集中拿出结果
        List<SecuritiesAndStock> securitiesList = (List<SecuritiesAndStock>) map.get("securities");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",securitiesList);
        //返回数据
        return json;
    }
    /**
     *删除
     */
    @OperLog(message = "证券板块的删除",operation = OperationType.DELETE)

    @RequestMapping("deleteSecurities")
    public int deleteSecurities(String securitiesId ){
        System.out.println("进入controller了");
        int i = securitiesService.deleteSecurities(securitiesId);
        return i;
    }

    /**
     * 增加
     */
    @OperLog(message = "证券板块的增加",operation = OperationType.ADD)

    @RequestMapping("insertSecurities")
    public int insertSecurities(Securities securities){
        System.out.println("我是页面数据"+securities);
        int i=securitiesService.insertSecurities(securities);
        System.out.println(i+"进con了");
        return i;
    }
    /**
     * 修改
     */
    @OperLog(message = "证券板块的修改",operation = OperationType.UPDATE)
    @RequestMapping("updateSecurities")
    public int updateSecurities(Securities securities){
        System.out.println("进来了控制测");
        int i=securitiesService.updateSecurities(securities);
        return i;
    }
}
