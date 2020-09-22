package com.ssaw.BusinessData.service;

import com.ssaw.BusinessData.entity.Market;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
*@program: TescComment
*@Description:行情数据Biz类
*@author: 瞿平
*@version:1.0
*@create: 2020-09-01
*/
@Service
@Transactional
public interface MarketService {
    /**
     * 查询
     * @return 返回一个集合
     */
    public List<Market> selectMarket();

    /**
     * 增加
     * @return 返回一个int类型的参数
     */
    public int insertMarket(Market market);

    /**
     * 删除
     * @return 返回一个int类型的参数
     */
    public int deleteMarket(String marketId);

    /**
     * 修改
     * @return 返回一个int类型的参数
     */
    public int updateMarket(Market market);


    /**
     * 行情数据分页查询
     * @param pageSize 每页条数
     * @param page 页码
     * @param securitiesId 证券代码
     * @param dateTime 业务日期
     * @return
     */
    public Map<String,Object> selectMarketInfo(String pageSize, String page,String securitiesId,String dateTime);
}
