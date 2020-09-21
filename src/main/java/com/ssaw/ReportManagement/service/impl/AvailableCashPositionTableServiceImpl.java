package com.ssaw.ReportManagement.service.impl;

import com.ssaw.ReportManagement.entity.AvailableCashPositionTable;
import com.ssaw.ReportManagement.mapper.AvailableCashPositionTableMapper;
import com.ssaw.ReportManagement.service.AvailableCashPositionTableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:    AvailableCashPositionTableServiceImpl
 * Package:    com.ssaw.ReportManagement.Service.impl
 * Description: 可用现金头寸表
 * Version:     1.0
 * Datetime:    2020/9/18 14:51
 * Author:   阙魁
 */

@Service
@Transactional
public class AvailableCashPositionTableServiceImpl implements AvailableCashPositionTableService {

    @Resource
    AvailableCashPositionTableMapper availableCashPositionTableMapper;
    @Override
    public Map<String, Object> selectAvailable(String pageSize, String page, String dateTime) {
        //创建一个结果集Map用于存放两个结果变量
        Map<String,Object> resultMap=new HashMap<>();
      //定义一个分页条数变量
        int v_pageSize=0;
        //判断传入的pageSize是否为空/null
        if (pageSize!=null && !pageSize.equals("")){
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

        String v_dateTime="";
        //创建Date类对象
        Date date=new Date();
        //new SimpleDateFormat对象
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        //判断传入的dateTime是否为null/空
        if(dateTime!=null&&!dateTime.equals("")){

          v_dateTime=dateTime;
        }else{

            //获取当天时间
            v_dateTime=simpleDateFormat.format(date);
        }
        String sqlSelect=" ( select  ACCOUNTNAME,CASHBLANCE from CASHINVENTORY c join ACCOUNT A2 on c.ACCOUNTID = A2.ACCOUNTID where DATETIME='"+v_dateTime+"' " +
                "union " +
                " select '申购赎回',nvl(sum(TOTALMONEY*BUSINESSSTATUS),0) from CASHCLOSEDPAYINVENTORY WHERE BUSINESSTYPE=4 and BUSINESSDATE='"+v_dateTime+"' " +
                " union " +
                " select '清算款',nvl(sum(TOTALPRICE*FLAG),0) from SECURITIESCLOSEDPAYINVENTORY WHERE SECURITIESTYPE=2 and DATETIME='"+v_dateTime+"' ) ";

        //创建一个Map 用于存款过程的调用传值
        Map<String,Object> map=new HashMap<>();
        //传入存储过程需要查询的表名
        map.put("p_tableName",sqlSelect);
        //传入查询的条件
        map.put("p_condition","");
        //传入分页显示条数
        map.put("p_pageSize",v_pageSize);
        //传入分页页码
        map.put("p_page",v_page);
        //创建out参数，返回数据总条数
        map.put("p_count",0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor",null);
        //调用Mapper执行查询
        availableCashPositionTableMapper.selectAvailable(map);
        //接收返回数据
        double qingsuankuan=0;
        double shengou=0;
        double cunkuan=0;
        double zonge=0;
        List<AvailableCashPositionTable> availableCashPositionTableList= (List<AvailableCashPositionTable>) map.get("p_cursor");
        for (AvailableCashPositionTable availableCashPositionTable : availableCashPositionTableList) {
            if(availableCashPositionTable.getAccountName().equals("申购赎回")){
                shengou+=availableCashPositionTable.getCashBlance();
            }else if(availableCashPositionTable.getAccountName().equals("清算款")){
                qingsuankuan+=availableCashPositionTable.getCashBlance();
            }else {
                cunkuan+=availableCashPositionTable.getCashBlance();
            }

        }
        zonge=cunkuan+qingsuankuan+shengou;

        AvailableCashPositionTable availableCashPositionTable1=new AvailableCashPositionTable("清算款合计",qingsuankuan);
        AvailableCashPositionTable availableCashPositionTable2=new AvailableCashPositionTable("申购赎回合计",shengou);
        AvailableCashPositionTable availableCashPositionTable3=new AvailableCashPositionTable("银行存款合计",cunkuan);
        AvailableCashPositionTable availableCashPositionTable4=new AvailableCashPositionTable("可用寸头金额",zonge);

        availableCashPositionTableList.add(availableCashPositionTable1);
        availableCashPositionTableList.add(availableCashPositionTable2);
        availableCashPositionTableList.add(availableCashPositionTable3);
        availableCashPositionTableList.add(availableCashPositionTable4);
        //接收返回总条数
        int v_count= (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("availableCashPositionTableList",availableCashPositionTableList);
        resultMap.put("count",v_count);
        System.out.println(resultMap.get("availableCashPositionTableList"));
        return resultMap;
    }
}
