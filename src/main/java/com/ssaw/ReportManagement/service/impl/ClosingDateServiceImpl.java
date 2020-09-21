package com.ssaw.ReportManagement.service.impl;

import com.ssaw.GlobalManagement.util.DateTimeUtil;
import com.ssaw.ReportManagement.entity.ClosingDate;
import com.ssaw.ReportManagement.mapper.ClosingDateMapper;
import com.ssaw.ReportManagement.service.ClosingDateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *@program: FA
 *@description: 成交清算报表实现类
 *@author: 瞿平
 *@create: 2020-09-16 16:41
 **/
@Service
public class ClosingDateServiceImpl implements ClosingDateService {
    //自动注入成交清算报表Dao层
    @Resource
    ClosingDateMapper closingDateMapper;

    @Override
    public HashMap selectClosingDate(String dateTime) {

        if (dateTime==null){
            dateTime = DateTimeUtil.getSystemDateTime("yyyy-MM-dd");
        }

        ArrayList<ClosingDate> cdsList = (ArrayList<ClosingDate>) closingDateMapper.selectClosingDate(dateTime);
        int count = cdsList.size();
        //添加流出合计，流入合计，清算合计实体类
        double inTotalMoney=0;
        double outTotalMoney=0;
        for (ClosingDate closingDate : cdsList) {
            if (closingDate.getFlag()==1){
                inTotalMoney=inTotalMoney+closingDate.getTotalSum();
            }
            else {
                outTotalMoney=outTotalMoney+closingDate.getTotalSum();
            }
        }
        double finalToalMoney=inTotalMoney-outTotalMoney;    //大于0
        //double留2个小数点
        BigDecimal bg = new BigDecimal(inTotalMoney);
        inTotalMoney = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        BigDecimal bg1 = new BigDecimal(outTotalMoney);
        outTotalMoney = bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        BigDecimal bg2 = new BigDecimal(finalToalMoney);
        finalToalMoney = bg2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        ClosingDate closingDate = new ClosingDate();
        closingDate.setSecuritiesId("流入合计");
        closingDate.setTotalSum(inTotalMoney);
        ClosingDate closingDate1 = new ClosingDate();
        closingDate1.setSecuritiesId("流出合计");
        closingDate1.setTotalSum(outTotalMoney);
        ClosingDate closingDate3 =new ClosingDate();
        closingDate3.setSecuritiesId("清算合计");
        closingDate3.setTotalSum(finalToalMoney);
        cdsList.add(closingDate);
        cdsList.add(closingDate1);
        cdsList.add(closingDate3);
        System.out.println(cdsList);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("list",cdsList);
        hashMap.put("count",count);
        return hashMap;
    }
}
