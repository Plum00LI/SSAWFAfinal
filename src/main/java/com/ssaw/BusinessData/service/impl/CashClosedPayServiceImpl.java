package com.ssaw.BusinessData.service.impl;


import com.ssaw.BusinessData.entity.CashClosedPay;
import com.ssaw.BusinessData.mapper.CashClosedPayMapper;
import com.ssaw.BusinessData.service.CashClosedPayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName CashClosePayServiceImpl
 * @Description: TODO
 * @Author 阙魁
 * @Date create in 22:53 2020/9/6
 * @Version 1.0
 **/
@Service
@Transactional
public class CashClosedPayServiceImpl implements CashClosedPayService {
    /**
     * 注入cashClosedPay的Mapper层
     */
    @Resource
    CashClosedPayMapper cashClosedPayMapper;

    /**
     * 增加数据的实现类方法
     * @param cashClosedPay
     * @return
     */
    @Override
    public int insertCashClosedPay(CashClosedPay cashClosedPay) {
        return cashClosedPayMapper.insertCashClosedPay(cashClosedPay);
    }

    /**
     * 通过cashClosedPayId删除的实体类
     * @param cashClosedPayId
     * @return
     */
    @Override
    public int deleteCashClosedPay(String cashClosedPayId) {
        //定义String类型数组 接收cashClosedPayId.split切割的数据
        String[] split = cashClosedPayId.split(",");
        //创建一个ArrayLIst集合
        ArrayList<Object> cashClosePayList=new ArrayList<>();
        //String数组加强for循环
        for (String id : split) {
            //将数据添加到ArrayList集合中
            cashClosePayList.add(id);
        }
        //return返回
        return cashClosedPayMapper.deleteCashClosedPay(cashClosePayList);
    }

    @Override
    public int updateCashClosedPay(CashClosedPay cashClosedPay) {
        return cashClosedPayMapper.updateCashClosedPay(cashClosedPay);
    }

    @Override
    public Map<String,Object> selectCashClosedPay(String pageSize, String page,String dateTime,String serviceType) {
        //创建一个结果集Map用于存放两个结果变量
        Map<String, Object> resultMap = new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize = 0;
        //判断传入的pageSize是否为null/空
        if (pageSize != null && !pageSize.equals("")) {
            //通过Integer包装类将String类型转换成int基础数据类型
            v_pageSize = Integer.parseInt(pageSize);
        }
        //定义一个分页页码变量
        int v_page = 0;
        //判断传入的page是否为null/空
        if (page != null && !page.equals("")) {
            //通过Integer包装类将String类型转换成int基础数据类型
            v_page = Integer.parseInt(page);
        }
        //定义一个StringBuffer变量
        StringBuffer sqlWhere=new StringBuffer();
        //定义一个v_serviceType变量
        int v_serviceType=0;
        //判断传入的serviceType是否为null/空
        if (serviceType!=null && !serviceType.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_serviceType=Integer.parseInt(serviceType);
            //使用sqlWhere.append添加方法添加SQL判断语句
            sqlWhere.append(" and serviceType =" +v_serviceType);
        }
        //判断传入的dateTime是否为null/空
        if (dateTime!=null && !dateTime.equals("")){

                sqlWhere.append(" and dateTime = '" +dateTime+"' ");

        }
        //创建一个结果集Map用于接收数据库存储过程所需条件
        Map<String,Object> map = new HashMap<>();
        //定义一个String类型用来接收查询呢的sql语句cashClosedPay、fund、account
        String sqlSelect="(select * from cashClosedPay c join fund f on f.fundId=c.fundId join account a on a.accountId=c.accountId ) ";
        //将 p_tableName、p_condition、p_pageSize、p_page、p_count、p_cursor的值放入map结果集
        map.put("p_tableName",sqlSelect);
        map.put("p_condition",sqlWhere.toString());
        map.put("p_pageSize",v_pageSize);
        map.put("p_page",v_page);
        map.put("p_count",0);
        map.put("p_cursor",null);

        //调用Mapper执行查询
        cashClosedPayMapper.selectCashClosedPay(map);
        //接收返回数据
        List<CashClosedPay> cashClosedPays = (List<CashClosedPay>) map.get("p_cursor");
        //接收返回总条数
        int count = (int) map.get("p_count");
        //将cashClosedPays、count结果放入结果集Map
        resultMap.put("cashClosedPays",cashClosedPays);
        resultMap.put("count",count);
        return resultMap;
    }
    //创建一个根据实体类来删除
    @Override
    public void deleteNew(CashClosedPay cashClosePay) {
        cashClosedPayMapper.deleteNew(cashClosePay);
    }
    //创建一个根据实体类来查询
    @Override
    public List selectNew(CashClosedPay cashClosePay) {
      return  cashClosedPayMapper.selectNew(cashClosePay);
    }

    //收益计提删除方法
    //老傅大傻逼
    @Override
    public int deleteNew2(CashClosedPay cashClosedPay) {
        return cashClosedPayMapper.deleteNew2(cashClosedPay);
    }
}
