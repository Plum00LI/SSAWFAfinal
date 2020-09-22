package com.ssaw.DayEndProcessing.service.impl;
/**
 * 收益计提
 * @type 服务实现类
 * @author fusaiying
 * @date 2020-09-10
 * @version 1.0
 */
import com.ssaw.DayEndProcessing.entity.BondInterest;
import com.ssaw.DayEndProcessing.entity.TwoFees;
import com.ssaw.DayEndProcessing.mapper.RevenueProvisionMapper;
import com.ssaw.DayEndProcessing.service.RevenueProvisionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
@Service
public class RevenueProvisionServiceImpl implements RevenueProvisionService {
    @Resource
    RevenueProvisionMapper revenueProvisionMapper;
    @Override
    public HashMap selectRevenueProvision(int page, int limit,String statDate) {
        HashMap revenueProvisionMap = new HashMap();
        revenueProvisionMap.put("p_tableName","(select round(ca.cashBlance* (a.cardRate/100/(case when a.procisionDays = 1 then 360\n" +
                "              when a.procisionDays=2 then 365 else 366 end )  ),4) as interest,a.cardRate,\n" +
                "                a.blankName,a.accountName,a.accountId,ca.cashBlance,ca.fundId,a.deposit,(case when a.deposit = 1 then '活期'\n" +
                "                else  '定期' end) as depositName,ca.dateTime,(case when a.procisionDays = 1 then 360\n" +
                "                  when a.procisionDays=2 then 365 else 366 end )as procisionDayName\n" +
                "                from (select * from cashInventory where dateTime='"+statDate+"')\n" +
                "                  ca join account a on ca.accountId=a.accountId)");
        revenueProvisionMap.put("p_condition","");
        revenueProvisionMap.put("p_pageSize",limit);
        revenueProvisionMap.put("p_page",page);
        revenueProvisionMap.put("p_count",0);
        revenueProvisionMap.put("p_cursor",null);
        revenueProvisionMapper.selectRevenueProvision(revenueProvisionMap);
        return revenueProvisionMap;
    }

    @Override
    public HashMap selectBondInterest(int page, int limit ,String statDate) {
        HashMap BondInterestMap = new HashMap();
        BondInterestMap.put("p_tableName","     (select round(se.securitiesNum* (b.BONDRATEAMOUNT*b.PARRATE/100/365),4) as interest,\n" +
                "       b.securitiesId,se.dateTime,se.fundId,b.bondName,b.parRate,(case when b.payInterestNum=1 then '一年一次' when b.payInterestNum=2 then '一年两次' else '一年三次' end)as payInterest ,b.drawStartDate,b.payInterestNum,se.securitiesNum,b.bondRateAmount\n" +
                "from (select * from securitiesInventory where dateTime='"+statDate+"')\n" +
                "    se join bond b on se.securitiesId = b.securitiesId)");
        BondInterestMap.put("p_condition","");
        BondInterestMap.put("p_pageSize",limit);
        BondInterestMap.put("p_page",page);
        BondInterestMap.put("p_count",0);
        BondInterestMap.put("p_cursor",null);
        revenueProvisionMapper.selectBondInterest(BondInterestMap);
        return BondInterestMap;
    }

    @Override
    public HashMap selectTwoFees(int page, int limit ,String statDate) {
        HashMap twoFeesMap = new HashMap();
        System.out.println("jjjjjjjjj"+statDate);
        if(statDate!="" && statDate!=null ){
            twoFeesMap.put("p_tableName","(select f.fundId,f.managerRate,f.accountId,f.hostingRate,va.valueStatisticsDate,va.cost,\n" +
                    "       ROUND((va.cost*f.managerRate/100/365 ),4)as managementMoney,\n" +
                    "       ROUND((va.cost*f.hostingRate/100/365 ),4)as CustodyMoney from  fund f\n" +
                    "    join (select valueStatisticsDate,cost,FUNDID from valueStatistics where valueStatisticsDate=to_char(to_date('"+statDate+"','yyyy-MM-dd')-1,'yyyy-MM-dd') and PROJECTNAME='资产净值') va on f.fundId=va.fundId)");
        }else {
            twoFeesMap.put("p_tableName","(select f.fundId,f.managerRate,f.accountId,f.hostingRate,va.valueStatisticsDate,va.cost,\n" +
                    "       ROUND((va.cost*f.managerRate/100/365 ),4)as management,\n" +
                    "       ROUND((va.cost*f.hostingRate/100/365 ),4)as Custody from  fund f\n" +
                    "    join (select valueStatisticsDate,cost,FUNDID from valueStatistics where valueStatisticsDate='2020-08-01') va on f.fundId=va.fundId)");
        }
        twoFeesMap.put("p_condition","");
        twoFeesMap.put("p_pageSize",limit);
        twoFeesMap.put("p_page",page);
        twoFeesMap.put("p_count",0);
        twoFeesMap.put("p_cursor",null);
        ArrayList<TwoFees> p_cursor = (ArrayList<TwoFees>) twoFeesMap.get("p_cursor");
        System.out.println(p_cursor);
        revenueProvisionMapper.selectTwoFees(twoFeesMap);
        return twoFeesMap;
    }
}

