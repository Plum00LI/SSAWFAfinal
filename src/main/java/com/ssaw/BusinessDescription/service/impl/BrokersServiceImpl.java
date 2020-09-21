package com.ssaw.BusinessDescription.service.impl;

import com.ssaw.BusinessDescription.entity.Brokers;
import com.ssaw.BusinessDescription.mapper.BrokersMapper;
import com.ssaw.BusinessDescription.service.BrokersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:TescComment
 * @Description:实现类
 * @author:孙浩
 * @create:2020-09-07
 */
@Service
@Transactional
public class BrokersServiceImpl implements BrokersService {
    @Resource
    BrokersMapper brokersMapper;

    @Override
    public int insertBrokers(Brokers brokers) {
        return brokersMapper.insertBrokers(brokers);
    }

    @Override
    public int deleteBrokers(String brokersId) {
        //将id转为数据
        String[] split = brokersId.split(",");
        //创建存放id的集合
        ArrayList<Object> brokersIdList = new ArrayList<>();
        for (String id : split) {
            //将id存入集合
            brokersIdList.add(id);
        }
        return brokersMapper.deleteBrokers(brokersIdList);
    }

    @Override
    public int updateBrokers(Brokers brokers) {
        return brokersMapper.updateBrokers(brokers);
    }

    @Override
    public Map<String, Object> selectBrokers(String pageSize, String page, String brokersName) {
        //创建一个结果集Map用于存放两个结果变量
        Map<String, Object> resultMap = new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize = 0;
        //判断v_pageSize是否为空
        if (pageSize != null && !pageSize.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_pageSize=Integer.parseInt(pageSize);
        }
        //定义一个分页页码变量
        int v_page = 0;
        //判断传入的page是否为null/空
        if (page != null && !page.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_page=Integer.parseInt(page);
        }


        //条件查询
        StringBuffer sqlWhere=new StringBuffer();
        if(brokersName != null && !brokersName.equals("")){
            sqlWhere.append(" AND brokersName LIKE  '%"+brokersName+"%'" );
        }



        //创建一个Map，用于存储过程的调用传值
        Map<String,Object> map = new HashMap<>();
        //传入存储过程需要的查询的表名
        map.put("p_tableName","brokers");
        //传入查询条件
        map.put("p_condition", sqlWhere.toString());
        //传入分页显示条数
        map.put("p_pageSize",v_pageSize);
        //传入分页页码
        map.put("p_page",v_page);
        //创建out参数，返回数据总条数
        map.put("p_count",0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor",null);
        //调用Mapper执行查询
        System.out.println(map.toString());
        brokersMapper.selectBrokers(map);
        //接收返回数据
        List<Brokers> brokersList = (List<Brokers>) map.get("p_cursor");
        //接收返回总条数
        int v_count = (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("brokersList",brokersList);
        resultMap.put("count",v_count);
        System.out.println(resultMap.get("brokersList"));
        String p_condition = (String) map.get("p_condition");
        System.out.println(p_condition);
        System.out.println(resultMap);
        //返回结果集Map
        return resultMap;
    }
}
