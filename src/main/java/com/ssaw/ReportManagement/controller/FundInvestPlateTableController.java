package com.ssaw.ReportManagement.controller;

import com.ssaw.DayEndProcessing.entity.ValueStatistics;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.DateTimeUtil;
import com.ssaw.GlobalManagement.util.OperationType;
import com.ssaw.ReportManagement.entity.FundInvestPlateTable;
import com.ssaw.ReportManagement.entity.FundInvestPlateTable2;
import com.ssaw.ReportManagement.service.FundInvestPlateTableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * create by: 佘高鹏
 * description: TODO
 *
 * create time: 2020/9/21 11:42
 * version number 1.0
  * @Param: null
 * @return
 */
@RestController
@RequestMapping("FundInvestPlateTable")
public class FundInvestPlateTableController {
    @Resource
    FundInvestPlateTableService fundInvestPlateTableService;

    @RequestMapping("selectFundInvestPlateTable")
    @ResponseBody
    public Object selectFundInvestPlateTable(String valueStatisticsDate,String fundId) throws ParseException {
        valueStatisticsDate = valueStatisticsDate.trim();
        fundId=fundId.trim();
        if(valueStatisticsDate.equals("")){
            valueStatisticsDate= DateTimeUtil.getSystemDateTime("yyyy-MM-dd");

        }
        valueStatisticsDate=valueStatisticsDate.trim();
        //行情数据周六周日特殊处理模块
        String dateTimeTwo=valueStatisticsDate;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = df.parse(valueStatisticsDate);
        Calendar instance = Calendar.getInstance();
        instance.setTime(parse);
        int i = instance.get(Calendar.DAY_OF_WEEK);
        i--;
        if(i==6){
            instance.add(Calendar.DATE, -1); //得到前一天
            Date time = instance.getTime();
            dateTimeTwo=df.format(time);

        }else if(i==7){
            instance.add(Calendar.DATE, -2); //得到前一天
            Date time = instance.getTime();
            dateTimeTwo=df.format(time);
        }
        dateTimeTwo=dateTimeTwo.trim();



        //查询当日资产净值
        ValueStatistics valueStatistics = fundInvestPlateTableService.selectValue(valueStatisticsDate, fundId);

        //查询证券板块

            List<FundInvestPlateTable> fundInvestPlateTables = fundInvestPlateTableService.selectFundInvestPlate(valueStatisticsDate, fundId, dateTimeTwo);
            //遍历板块集合，生成页面需要的对象
            if(fundInvestPlateTables.size()!=0){
                for (FundInvestPlateTable fundInvestPlateTable : fundInvestPlateTables) {
                    if(fundInvestPlateTable!=null){
                        //计算市值占净值百分比
                            if(valueStatistics!=null){
                                if(valueStatistics.getCost()!=0){
                                    fundInvestPlateTable.setMarketValueStatistics((fundInvestPlateTable.getMarketValue()/valueStatistics.getCost()));
                                }

                            }
                    }
                }
            }


        Map<String,Object> josn = new HashMap<String,Object>();
        josn.put("code", 0);
        josn.put("count",fundInvestPlateTables.size());
        josn.put("msg", "");
        josn.put("data", fundInvestPlateTables);
        return josn;


    }

    @RequestMapping("selectFundInvestPlateTable2")
    @ResponseBody
    public Object selectFundInvestPlateTable2(String valueStatisticsDate,String fundId) throws ParseException {
        valueStatisticsDate = valueStatisticsDate.trim();
        fundId=fundId.trim();
        if(valueStatisticsDate.equals("")){
            valueStatisticsDate= DateTimeUtil.getSystemDateTime("yyyy-MM-dd");

        }
        valueStatisticsDate=valueStatisticsDate.trim();
        //行情数据周六周日特殊处理模块
        String dateTimeTwo=valueStatisticsDate;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = df.parse(valueStatisticsDate);
        Calendar instance = Calendar.getInstance();
        instance.setTime(parse);
        int i = instance.get(Calendar.DAY_OF_WEEK);
        i--;
        if(i==6){
            instance.add(Calendar.DATE, -1); //得到前一天
            Date time = instance.getTime();
            dateTimeTwo=df.format(time);

        }else if(i==7){
            instance.add(Calendar.DATE, -2); //得到前一天
            Date time = instance.getTime();
            dateTimeTwo=df.format(time);
        }
        dateTimeTwo=dateTimeTwo.trim();


        List josnList =new ArrayList();
        //查询当日资产净值
        ValueStatistics valueStatistics = fundInvestPlateTableService.selectValue(valueStatisticsDate, fundId);

        //查询证券板块
        List<FundInvestPlateTable> fundInvestPlateTables = fundInvestPlateTableService.selectFundInvestPlate(valueStatisticsDate, fundId, dateTimeTwo);
        if(fundInvestPlateTables.size()!=0){
            for (FundInvestPlateTable fundInvestPlateTable : fundInvestPlateTables) {
                if(fundInvestPlateTable!=null){
                    //计算市值占净值百分比
                    if(valueStatistics!=null){
                        if(valueStatistics.getCost()!=0){
                            fundInvestPlateTable.setMarketValueStatistics((fundInvestPlateTable.getMarketValue()/valueStatistics.getCost()));
                        }

                    }
                }
            }
        }
        //遍历板块集合，生成页面需要的对象
        if(fundInvestPlateTables.size()!=0) {
            for (FundInvestPlateTable fundInvestPlateTable : fundInvestPlateTables) {
                if(fundInvestPlateTable!=null){
                    FundInvestPlateTable2 fundInvestPlateTable2=new FundInvestPlateTable2(fundInvestPlateTable.getStockName(),fundInvestPlateTable.getMarketValue());
                    josnList.add(fundInvestPlateTable2);
                }
            }
        }
        return josnList;


    }

}
