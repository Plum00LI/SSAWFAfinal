package com.ssaw.BusinessData.service.impl;

import com.ssaw.BusinessData.entity.Market;
import com.ssaw.BusinessData.mapper.MarketMapper;
import com.ssaw.BusinessData.service.MarketService;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*@program: TescComment
*@Description:行情数据实现类
*@author: 瞿平
*@version:1.0
*@create: 2020-09-01
*/
@Service
@Transactional
public class MarketServiceImpl implements MarketService {
    //调用Dao类
    @Resource
    MarketMapper marketMapper;

    /**
     * 查询方法
     * @return
     */
    @Override
    public List<Market> selectMarket() {
        return marketMapper.selectMarket();
    }

    /**
     * 行情数据增加方法
     * @param market 行情数据实体对象
     * @return
     */
    @Override
    public int insertMarket(Market market) {
        return marketMapper.insertMarket(market);
    }

    /**
     * 删除方法
     * @param marketId 行情Id
     * @return
     */
    @Override
    public int deleteMarket(String marketId) {
        //切割字符串
        String[] split = marketId.split(",");
        ArrayList<Object> list=new ArrayList<>();
        for (String id : split) {
            list.add(id);
        }
        return marketMapper.deleteMarket(list);
    }

    /**
     * 修改方法
     * @param market 行情数据实体对象
     * @return
     */
    @Override
    public int updateMarket(Market market) {
        return marketMapper.updateMarket(market);
    }

    /**
     * 行情数据分页查询
     * @param pageSize 每页条数
     * @param page 页码
     * @param securitiesId 证券代码
     * @param dateTime 业务日期
     * @return
     */
    @Override
    public Map<String, Object> selectMarketInfo(String pageSize, String page,String securitiesId,String dateTime) {
        //创建一个结果集Map,用于存放两个结果变量
        Map<String,Object> resultMap = new HashMap<>();
        //定义一个分页条数变量
        int m_pageSize = 0;
        //判断传入的pageSize是否为null
        if(pageSize!=null && !pageSize.equals("")){
            //强转为int类型
            m_pageSize = Integer.parseInt(pageSize);
        }
        //定义一个分页码变量
        int m_page = 0;
        //判断传入的page是否为空
        if(page!=null && !page.equals("")){
            //强转为int类型
            m_page = Integer.parseInt(page);
        }

        StringBuffer sqlWhere = new StringBuffer();
        //高级搜索条件sql语句拼接  证券代码
        if (securitiesId!=null && !securitiesId.equals("")){
            sqlWhere.append(" and securitiesId like '%"+securitiesId+"%'");
        }
        //高级搜索条件sql语句拼接  业务日期
        if (dateTime!=null && !dateTime.equals("")){
            sqlWhere.append(" and dateTime like '%"+dateTime+"%'");
        }
        //按条件查询sql语句
        String tableName="(select m.marketId,m.securitiesId,s.securitiesName,m.dateTime,m.openPrice,m.closingPrice,m.marketdesc from " + SysTableNameListUtil.M +" m join (select securitiesName,securitiesId from "+ SysTableNameListUtil.SE+" )  s on m.securitiesId=s.securitiesId)";
        System.out.println("语句"+tableName);
        //创建一个Map,用来调用存储过程
        Map<String,Object> map = new HashMap<>();
        //传入存储过程要查询的表名
        map.put("p_tableName",tableName);
        //传入查询条件
        map.put("p_condition",sqlWhere.toString());
        //传入分页显示条数
        map.put("p_pageSize",m_pageSize);
        //传入分页页码
        map.put("p_page",m_page);
        //创建out参数，返回数据总条数
        map.put("p_count",0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor",null);
        //调用mapper执行查询
        marketMapper.selectMarketInfo(map);
        List<Market> marketList = (List<Market>) map.get("p_cursor");
        System.out.println(marketList);
        //接收返回总条数
        int m_count = (int)map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("markets",marketList);
        resultMap.put("count",m_count);
        //返回结果集Map
        return resultMap;
    }
}
