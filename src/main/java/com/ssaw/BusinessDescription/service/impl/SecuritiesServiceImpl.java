package com.ssaw.BusinessDescription.service.impl;

import com.ssaw.BusinessDescription.entity.Securities;
import com.ssaw.BusinessDescription.entity.SecuritiesAndStock;
import com.ssaw.BusinessDescription.mapper.SecuritiesMapper;
import com.ssaw.BusinessDescription.service.SecuritiesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 债券参数
 * @type Securities的service的实现类
 * @author fusaiying
 * @date 2020-09-01
 * @version 1.0
 */
@Service
public class SecuritiesServiceImpl implements SecuritiesService {
    @Resource
    SecuritiesMapper securitiesMapper;


    /**
     * 删除
     * @param securitiesId
     */
    @Override
    public int deleteSecurities(String securitiesId) {
        String[] split = securitiesId.split(",");
        ArrayList<Object> securitiestList = new ArrayList<>();
        for (String id : split) {
            securitiestList.add(id);
        }
        return securitiesMapper.deleteSecurities(securitiestList);

    }

    /**
     * 查询所有用户的实现类方法（带分页，返回数据和总条目数）
     * @param pageSize 当前查询页数
     * @param page 分页数据条目数
     * @return 查询的结果集Map
     */
    @Override
    public Map<String, Object> selectSecurities(String pageSize,String page,String securitiesId,String exchange,String securitiesType) {
        //创建一个结果集Map用于存放两个结果变量
        Map<String, Object> resultMap = new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize = 0;

        //判断传入的pageSize是否为null/空
        if (pageSize!=null&&!pageSize.equals("")){
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
        int v_exchange=0;
        String sql="";
        if (exchange!=null&&!exchange.equals("")){
            v_exchange=Integer.parseInt(exchange);
            sql=sql+" and exchange='"+v_exchange+"'";
        }

        if(securitiesId!=null && !securitiesId.equals("")){
            sql=sql+" and securitiesId like '%"+securitiesId+"%'";
        }

        if(securitiesType!=null && !securitiesType.equals("")){
            int i = Integer.parseInt(securitiesType);
            sql=sql+" and securitiesType='"+i+"'";
        }
        String p_tableName="(select * from securities se join stock st on st.stockId=se.stockId)";
        //创建一个Map，用于存储过程的调用传值
        Map<String,Object> map = new HashMap<>();
        //传入存储过程需要查询的表名
        map.put("p_tableName", p_tableName);
        //传入查询条件
        map.put("p_condition",sql);
        //传入分页显示条数
        map.put("p_pageSize",v_pageSize);
        //传入分页页码
        map.put("p_page",v_page);
        //创建out参数，返回数据总条数
        map.put("p_count",0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor",null);
        //调用Mapper执行查询
        //System.out.println(p_tableName);
        System.out.println(sql);
        securitiesMapper.selectSecurities(map);
        //接收返回数据
        List<SecuritiesAndStock> securities = (List<SecuritiesAndStock>) map.get("p_cursor");
        //接收返回总条数
        int v_count = (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("securities",securities);
        resultMap.put("count",v_count);
        //返回结果集Map
        System.out.println("业务逻辑返回的=" + resultMap.get("securities"));
        return resultMap;
    }
//添加
    @Override
    public int insertSecurities(Securities securities) {
        int i = securitiesMapper.insertSecurities(securities);
        return i;
    }
//修改
    @Override
    public int updateSecurities(Securities securities) {
        int i = securitiesMapper.updateSecurities(securities);
        return i;
    }
}
