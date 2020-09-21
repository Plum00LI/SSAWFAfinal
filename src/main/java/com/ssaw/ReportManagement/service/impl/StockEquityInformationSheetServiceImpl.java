package com.ssaw.ReportManagement.service.impl;

import com.ssaw.ReportManagement.entity.StockEquityInformationSheet;
import com.ssaw.ReportManagement.mapper.StockEquityInformationSheetMapper;
import com.ssaw.ReportManagement.service.StockEquityInformationSheetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:    StockEquityInformationSheetServiceImpl
 * Package:    com.ssaw.ReportManagement.Service.impl
 * Description: 股票权益数据表
 * Version:     1.0
 * Datetime:    2020/9/19
 * Author:   阙魁
 */
@Service
@Transactional
public class StockEquityInformationSheetServiceImpl implements StockEquityInformationSheetService {
    @Resource
    StockEquityInformationSheetMapper stockEquityInformationSheetMapper;
    @Override
    public Map<String, Object> selectStockEquityInformationSheet(String pageSize, String page, String startTime, String endTime, String equitiesTypes) {
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

        StringBuffer sqlWhere=new StringBuffer();
        //判断传入的dateTime是否为null/空
        if(startTime!=null&&!startTime.equals("")){
            sqlWhere.append(" and EQUITIESRECORD >= '"+startTime+"' ");

        }
        //判断传入的dateTime是否为null/空
        if(endTime!=null&&!endTime.equals("")){
            sqlWhere.append(" and EQUITIESRECORD <= '"+endTime+"' ");
        }
        //判断传入的equitiesType是否为null/空
        int v_equitiesType=0;
        if(equitiesTypes!=null&&!equitiesTypes.equals("")){
            v_equitiesType=Integer.parseInt(equitiesTypes);
            sqlWhere.append(" and EQUITIESTYPE= "+v_equitiesType+" ");
        }

        String sqlSelect="(select * from SECURITIESINVENTORY se join (select * from EQUITYDATA) e on e.SECURITYID=se.SECURITIESID join SECURITIES S2 on se.SECURITIESID = S2.SECURITIESID )";

        //创建一个Map 用于存款过程的调用传值
        Map<String,Object> map=new HashMap<>();
        //传入存储过程需要查询的表名
        map.put("p_tableName",sqlSelect);
        //传入查询的条件
        map.put("p_condition",sqlWhere.toString());
        //传入分页显示条数
        map.put("p_pageSize",v_pageSize);
        //传入分页页码
        map.put("p_page",v_page);
        //创建out参数，返回数据总条数
        map.put("p_count",0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor",null);
        //调用Mapper执行查询
        stockEquityInformationSheetMapper.selectStockEquityInformationSheet(map);
        //接收返回数据
        List<StockEquityInformationSheet> stockEquityInformationSheetList= (List<StockEquityInformationSheet>) map.get("p_cursor");
        for (StockEquityInformationSheet list : stockEquityInformationSheetList) {
            //为送股计算出送股数量，金额为0
            if(list.getEquitiesType()==1){
                list.setTotal(0);
                list.setSecuritiesNum(list.getSecuritiesNum()*list.getProportion()/100);
            }else if(list.getEquitiesType()==2){
                //判断是否为分红，分红的话计算出金额
                list.setTotal(list.getSecuritiesNum()*list.getProportion()/100);
            }
        }

        //接收返回总条数
        int v_count= (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("stockEquityInformationSheetList",stockEquityInformationSheetList);
        resultMap.put("count",v_count);
        System.out.println(resultMap.get("stockEquityInformationSheetList"));
        return resultMap;
    }
}
