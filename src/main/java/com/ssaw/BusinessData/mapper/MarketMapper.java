package com.ssaw.BusinessData.mapper;



import com.ssaw.BusinessData.entity.Market;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
*@program: TescComment
*@Description:行情数据Dao类
*@author: 瞿平
*@version:1.0
*@create: 2020-09-01
*/
@Mapper
public interface MarketMapper {
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
     * 分页查询
     * @param map 结果集
     * @return
     */
      public Map<String,Object> selectMarketInfo(Map map);

     /**
     * 批量删除
     * @param marketId 行情Id
     * @return marketId 行情Id
     */
      int deleteMarket(List marketId);
}
