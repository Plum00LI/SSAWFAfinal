package com.ssaw.ReportManagement.service.impl;

import com.ssaw.ReportManagement.entity.StockPriceFluctuationTable;
import com.ssaw.ReportManagement.mapper.StockPriceFluctuationTableMapper;
import com.ssaw.ReportManagement.service.StockPriceFluctuationTableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:    StockPriceFluctuationTableServiceImpl
 * Package:    com.ssaw.ReportManagement.Service.impl
 * Description: 股票价格波动表
 * Version:     1.0
 * Datetime:    2020/9/19
 * Author:   阙魁
 */
@Service
@Transactional
public class StockPriceFluctuationTableServiceImpl implements StockPriceFluctuationTableService {

    @Resource
    StockPriceFluctuationTableMapper stockPriceFluctuationTableMapper;

    @Override
    public Map<String, Object> SelectStockPrice(String pageSize, String page, String dateTime) {
        System.out.println("进入了实现类");

        //创建一个结果集Map用于存放两个结果变量
        Map<String,Object> resultMap=new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize=0;
        //判断传入的pageSize是否为空/null
        if (pageSize!=null && !pageSize.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_pageSize=Integer.parseInt(pageSize);
        }
        //定义一个分页页码变量
        int v_page = 0;
        //判断传入的page是否为null/空
        if (page!=null&&!page.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_page=Integer.parseInt(page);
        }

        String  v_dateTime="";
        //判断传入的dateTime是否为null/空
        if(dateTime!=null&&!dateTime.equals("")){
            v_dateTime=dateTime;
        }else {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            Date date=new Date();
            v_dateTime=simpleDateFormat.format(date);
        }


        String sqlSelect="( select s.SECURITIESNAME,s.SECURITIESID,a.DATETIME,a.OPENPRICE as todayIce,c.OPENPRICE as beforeIce  from (select * from MARKET where DATETIME='"+v_dateTime+"') a join (select * from MARKET where DATETIME=to_char(to_date('"+v_dateTime+"','yyyy-MM-dd')-1,'yyyy-MM-dd') ) c on a.SECURITIESID=c.SECURITIESID join SECURITIES s on s.SECURITIESID=a.SECURITIESID ) ";
        System.out.println("sqlselect="+sqlSelect);
        //创建一个Map 用于存款过程的调用传值
        Map<String,Object> map=new HashMap<>();
        //传入存储过程需要查询的表名
        map.put("p_tableName",sqlSelect);
        //传入查询的条件
        map.put("p_condition","");
        //传入分页显示条数
        map.put("p_pageSize",v_pageSize);
        //传入分页页码
        map.put("p_page",v_page);
        //创建out参数，返回数据总条数
        map.put("p_count",0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor",null);
        //调用Mapper执行查询
        stockPriceFluctuationTableMapper.SelectStockPrice(map);
        //接收返回数据
        List<StockPriceFluctuationTable> stockPriceFluctuationTableList= (List<StockPriceFluctuationTable>) map.get("p_cursor");
       //stockPriceFluctuationTableList集合for循环
        for (StockPriceFluctuationTable list : stockPriceFluctuationTableList) {
            //计算出增跌幅（今日金额-昨日金额）/昨日金额*100
            list.setQuoteChange((list.getTodayIce()-list.getBeforeIce())/list.getBeforeIce()*100);
        }
        System.out.println("集合值:"+stockPriceFluctuationTableList);
        //接收返回总条数
        int v_count= (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("stockPriceFluctuationTableList",stockPriceFluctuationTableList);
        resultMap.put("count",v_count);
        System.out.println(resultMap.get("stockPriceFluctuationTableList"));
        return resultMap;
    }
}
