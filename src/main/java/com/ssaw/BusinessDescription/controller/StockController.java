package com.ssaw.BusinessDescription.controller;

import com.ssaw.BusinessDescription.entity.Securities;
import com.ssaw.BusinessDescription.entity.Stock;
import com.ssaw.BusinessDescription.service.StockService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    @RequestMapping("updateStock")
    public int updateStock(Stock stock){
        int i=stockService.updateStock(stock);
        return i;
    }

    /**
     *删除
     */
    @RequestMapping("deleteStock")
    public int deleteStock(String stockId ){
        System.out.println("进入controller了");
        int i = stockService.deleteStock(stockId);
        return i;
    }
    /**
     * 查询夫类
     * 证券信息引用
     */
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

