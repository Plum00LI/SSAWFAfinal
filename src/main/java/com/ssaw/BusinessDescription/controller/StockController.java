package com.ssaw.BusinessDescription.controller;

import com.ssaw.BusinessDescription.entity.Fund;
import com.ssaw.BusinessDescription.entity.Securities;
import com.ssaw.BusinessDescription.entity.SecuritiesAndStock;
import com.ssaw.BusinessDescription.entity.Stock;
import com.ssaw.BusinessDescription.service.FundService;
import com.ssaw.BusinessDescription.service.StockService;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.OperationType;
import oracle.net.ano.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 股票板块
 * @type 控制层
 * @author fusaiying
 * @date 2020-09-01
 * @version 1.0
 */
@RestController
@RequestMapping("Stock")
public class StockController {
    @Resource
    StockService stockService;

    //查询
    @OperLog(message = "股票板块的查询",operation = OperationType.QUERY)
    @RequestMapping("selectStock")
    public Map<String,Object> selectStock() {
        List<Securities> securitiesList = stockService.selectStock();
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",null);
        json.put("data",securitiesList);
        //返回数据
        return json;

    }
    //增加
    @OperLog(message = "股票板块的增加",operation = OperationType.ADD)
    @RequestMapping("insertStock")
    public int insertStock(Stock stock){
        System.out.println("进入控制层了");
        int i = stockService.insertStock(stock);
        return i;
    }

    /**
     * 查询子类
     * 证券信息引用
     * @return
     */
    @OperLog(message = "股票板块的子类查询",operation = OperationType.QUERY)
    @RequestMapping("selectSonStock")
    public Map<String,Object> selectSonStock() {
        List<Stock> securitiesList = stockService.selectSonStock();
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",null);
        json.put("data",securitiesList);
        //返回数据
        return json;

    }

    /**
     * 修改
     */
    @OperLog(message = "股票板块的修改",operation = OperationType.UPDATE)
    @RequestMapping("updateStock")
    public int updateStock(Stock stock){
        int i=stockService.updateStock(stock);
        return i;
    }

    /**
     *删除
     */
    @OperLog(message = "股票板块的删除",operation = OperationType.DELETE)
    @RequestMapping("deleteStock")
    public int deleteStock(String stockId ){
        System.out.println("进入controller了");
        int i = stockService.deleteStock(stockId);
        return i;
    }
    /**
     * 查询父类
     * 证券信息引用
     */
    @OperLog(message = "股票板块的父类查询",operation = OperationType.QUERY)

    @RequestMapping("selectParentStock")
    public Map<String,Object> selectParentStock() {
        List<Stock> stockList = stockService.selectParentStock();
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",null);
        json.put("data",stockList);
        //返回数据
        return json;

    }
}

