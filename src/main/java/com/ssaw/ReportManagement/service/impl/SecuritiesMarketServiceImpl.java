package com.ssaw.ReportManagement.service.impl;

import com.ssaw.GlobalManagement.util.DateTimeUtil;
import com.ssaw.ReportManagement.entity.SecuritiesMarket;
import com.ssaw.ReportManagement.mapper.SecuritiesMarketMapper;
import com.ssaw.ReportManagement.service.SecuritiesMarketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ClassName:    SecuritiesMarketServiceImpl
 * Package:    com.ssaw.ReportManagement.service.impl
 * Description:
 * Version:
 * Datetime:    2020/9/19   10:18
 * Author:   SYT
 */
@Service
@Transactional
public class SecuritiesMarketServiceImpl implements SecuritiesMarketService {

    @Resource
    SecuritiesMarketMapper securitiesMarketMapper;

    /**
     * 证券市值变动数据
     * @param dateTime
     * @return
     */
    @Override
    public HashMap selectSecuritiesMarket(String dateTime) {
        if (dateTime==null){
            dateTime = DateTimeUtil.getSystemDateTime("yyyy-MM-dd");
        }
        ArrayList<SecuritiesMarket> securitiesMarketList = (ArrayList<SecuritiesMarket>) securitiesMarketMapper.selectSecuritiesMarket(dateTime);
        int count = securitiesMarketList.size();
        //资产净值
//        double projectId=securitiesMarketMapper.selectProjectId(dateTime,fundId);
        for (SecuritiesMarket securitiesMarket : securitiesMarketList) {
            System.out.println("证券市场变动"+securitiesMarket);
            //市值变动值
            double marketChangeValue=(securitiesMarket.getClosingPrice()-securitiesMarket.getPrice())/securitiesMarket.getPrice();
            BigDecimal bg = new BigDecimal(marketChangeValue);
            marketChangeValue = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            securitiesMarket.setMvcr(marketChangeValue);
            //市值占净值比
            double ratio=(securitiesMarket.getSecuritiesNum()*securitiesMarket.getClosingPrice()/securitiesMarket.getProjectId());
            System.out.println("市值 占净值比="+ratio);
            BigDecimal bg1 = new BigDecimal(ratio);
            ratio = bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            securitiesMarket.setMvnv(ratio);
        }
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("count",count);
        hashMap.put("list",securitiesMarketList);
        return hashMap;
    }

}
