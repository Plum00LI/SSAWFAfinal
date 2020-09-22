package com.ssaw.DayEndProcessing.service.impl;


import com.ssaw.DayEndProcessing.entity.BondInterestIncome;
import com.ssaw.DayEndProcessing.entity.CashInterestIncome;
import com.ssaw.DayEndProcessing.entity.PayTwoFees;
import com.ssaw.DayEndProcessing.mapper.IncomePaymentMapper;
import com.ssaw.DayEndProcessing.service.IncomePaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 收益支付 的impl层
 *
 * @version v1.0
 * @type:IncomePaymentServiceImpl
 * @author:阙魁
 * @create:2020-09-15
 */
@Service
@Transactional
public class IncomePaymentServiceImpl implements IncomePaymentService {

    @Resource
    IncomePaymentMapper incomePaymentMapper;

    //证券利息收入
    @Override
    public Map<String, Object> selectCashInterestIncome(String pageSize, String page, String statDate, String fundId) {
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
        //String类型时间减一天
        //new SimpleDateFormat对象
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //创建一个 String类型的空时间
        String datetime="";
        //创建Date类对象
        Date date=new Date();
        //判断传入的statDate是否为null/空
        if(statDate!=null&& !statDate.equals("")){
            try {
                //减一天 86400*1000
                long dif= df.parse(statDate).getTime() - 86400 * 1000;
                //把 dif 传给Date
                date.setTime(dif);
                //将使用format将date转换成日期类格式
                datetime=df.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            //new SimpleDateFormat对象
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            //创建Calendar 的实例
            Calendar calendar = Calendar.getInstance();
            //当前时间减去一天，即一天前的时间
            calendar.add(Calendar.DAY_OF_MONTH,-1);
            //获取当天时间
            statDate=simpleDateFormat.format(date);
            //获取当天-1的时间 calendar.getTime()
            datetime=simpleDateFormat.format(calendar.getTime());
        }
        System.out.println("statDate="+statDate);
        System.out.println("日期"+datetime);

        //查询表
        String sqlSelect=" (select * from (select * from cashClosedPayInventory where fundId='"+fundId+"' and businessType in 3 and businessDate='"+datetime+"' ) c join account a on c.accountId=a.accountId )  ";
        //创建一个Map，用于存储过程的调用传值
        Map<String, Object> map = new HashMap<>();
        //传入存储过程需要查询的表名
        map.put("p_tableName", sqlSelect);
        //传入查询条件
        map.put("p_condition", "");
        //传入分页显示条数
        map.put("p_pageSize", v_pageSize);
        //传入分页页码
        map.put("p_page", v_page);
        //创建out参数，返回数据总条数
        map.put("p_count", 0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor", null);
        //接收返回数据
        incomePaymentMapper.selectCashInterestIncome(map);

        List<CashInterestIncome> cashInterestIncomes = (List<CashInterestIncome>) map.get("p_cursor");

        for (CashInterestIncome cashInterestIncome : cashInterestIncomes) {
                    cashInterestIncome.setBusinessDate(statDate);
        }
        //接收返回总条数
        int count = (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("cashInterestIncomes",cashInterestIncomes);
        resultMap.put("count",count);
        System.out.println("实现类："+cashInterestIncomes);
        System.out.println(count);
        return resultMap;
    }

    //债券利息收入
    @Override
    public Map<String, Object> selectBondInterestIncome(String pageSize, String page, String statDate, String fundId) {
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
        //new SimpleDateFormat对象
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date date=new Date();

        //创建一个 String类型的空时间
        String datetime="";
        //判断传入的statDate是否为null/空
        if(statDate!=null&& !statDate.equals("")){
            try {
                //减一天 86400*1000
                long dif= df.parse(statDate).getTime() - 86400 * 1000;
                //把 dif 传给Date
                date.setTime(dif);
                //将使用format将date转换成日期类格式
                datetime=df.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            //new SimpleDateFormat对象
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            //创建Calendar 的实例
            Calendar calendar = Calendar.getInstance();
            //当前时间减去一天，即一天前的时间
            calendar.add(Calendar.DAY_OF_MONTH,-1);
            //获取当天时间
            statDate=simpleDateFormat.format(date);
            //获取当天-1的时间 calendar.getTime()
            datetime=simpleDateFormat.format(calendar.getTime());
        }
        System.out.println("statDate="+statDate);
        System.out.println("日期"+datetime);
        //查询表
        String sqlSelect=" (select * from (select * from securitiesClosedPayInventory where fundId='"+fundId+"' and securitiesType=3 and datetime='"+datetime+"' ) s  join securities se on se.securitiesId= s.securitiesId ) ";
        //创建一个Map，用于存储过程的调用传值
        Map<String, Object> map = new HashMap<>();
        //传入存储过程需要查询的表名
        map.put("p_tableName", sqlSelect);
        //传入查询条件
        map.put("p_condition", "");
        //传入分页显示条数
        map.put("p_pageSize", v_pageSize);
        //传入分页页码
        map.put("p_page", v_page);
        //创建out参数，返回数据总条数
        map.put("p_count", 0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor", null);

        incomePaymentMapper.selectBondInterestIncome(map);
        //接收返回数据
        List<BondInterestIncome> bondInterestIncomes = (List<BondInterestIncome>) map.get("p_cursor");
       //bondInterestIncomes集合for循环
        for (BondInterestIncome bondInterestIncome : bondInterestIncomes) {
            //业务时间set当前时间staDate
            bondInterestIncome.setDateTime(statDate);
        }
        //接收返回总条数
        int count = (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("bondInterestIncomes",bondInterestIncomes);
        resultMap.put("count",count);
        System.out.println("实现类："+bondInterestIncomes);
        System.out.println(count);
        return resultMap;
    }

    //支付两费
    @Override
    public Map<String, Object> selectPayTwoFees(String pageSize, String page, String statDate, String fundId) {
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
        //String类型时间减一天
        //new SimpleDateFormat对象
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //创建一个 String类型的空时间
        String datetime="";
        //创建Date类对象
        Date date=new Date();
        //判断传入的statDate是否为null/空
        if(statDate!=null&& !statDate.equals("")){
            try {
                //减一天 86400*1000
                long dif= df.parse(statDate).getTime() - 86400 * 1000;
                //把 dif 传给Date
                date.setTime(dif);
                //将使用format将date转换成日期类格式
                datetime=df.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            //new SimpleDateFormat对象
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            //创建Calendar 的实例
            Calendar calendar = Calendar.getInstance();
            //当前时间减去一天，即一天前的时间
            calendar.add(Calendar.DAY_OF_MONTH,-1);
            //获取当天时间
            statDate=simpleDateFormat.format(date);
            //获取当天-1的时间 calendar.getTime()
            datetime=simpleDateFormat.format(calendar.getTime());
        }
        System.out.println("statDate="+statDate);
        System.out.println("日期"+datetime);

        //查询表
        String sqlSelect=" (select * from (select * from cashClosedPayInventory where fundId='"+fundId+"' and businessType in (1,2) and businessDate='"+datetime+"' ) c join account a " +
                "on c.accountId=a.accountId ) ";
        //创建一个Map，用于存储过程的调用传值
        Map<String, Object> map = new HashMap<>();
        //传入存储过程需要查询的表名
        map.put("p_tableName", sqlSelect);
        //传入查询条件
        map.put("p_condition", "");
        //传入分页显示条数
        map.put("p_pageSize", v_pageSize);
        //传入分页页码
        map.put("p_page", v_page);
        //创建out参数，返回数据总条数
        map.put("p_count", 0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor", null);

        incomePaymentMapper.selectPayTwoFees(map);
        //接收返回数据
        List<PayTwoFees> payTwoFees = (List<PayTwoFees>) map.get("p_cursor");
        //payTwoFees集合for循环
        for (PayTwoFees payTwoFee : payTwoFees) {
                payTwoFee.setBusinessDate(statDate);
        }
        //接收返回总条数
        int count = (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("payTwoFees",payTwoFees);
        resultMap.put("count",count);
        System.out.println("实现类："+payTwoFees);
        System.out.println(count);
        return resultMap;
    }


}
