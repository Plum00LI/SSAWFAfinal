package com.ssaw.ReportManagement.service.impl;

import com.ssaw.ReportManagement.entity.SeatSchedule;
import com.ssaw.ReportManagement.mapper.SeatScheduleMapper;
import com.ssaw.ReportManagement.service.SeatScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeatScheduleImpl implements SeatScheduleService {
@Resource
    SeatScheduleMapper seatScheduleMapper;
    @Override
    public Map<String, Object> selectSeatSchedule(String pageSize, String page, String seateId, String datefind, String settlementDate) {
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
        //其他变量判断
        String sql = "";
        if (seateId != null && !seateId.equals("")){
            sql = sql + " and seateId='" + seateId + "'";
        }
//        String[] split = settlementDate.split("-");
//        String v_settlementDate=split[0]+"-"+split[1];
//        System.out.println(v_settlementDate);
        if (datefind=="1"){
            String[] split = settlementDate.split("-");
            String v_settlementDate=split[0]+"-"+split[1];
            System.out.println(v_settlementDate);
            sql=sql+" and settlementDate='" + v_settlementDate + "'";
        }else if (datefind=="2"){
            String[] split = settlementDate.split("-");
            String v_settlementDate=split[0]+"-"+split[1];
            System.out.println(v_settlementDate);
            sql=sql+ " and securitiesId like '%" + v_settlementDate + "%'";
        }
        String p_tableName="(select s.settlementDate,s.fundId,t_netreceipts, t_totalSum,t_num,t_commission, t_ransfer,t_brokerage,t_stamp,t_management,seateId,securitiesId,f.fundName  FROM (\n" +
                "select  settlementDate,fundid,sum(netreceipts) as t_netreceipts,sum(totalSum) as t_totalSum,\n" +
                "       sum(num) as t_num,sum(commission) as t_commission,sum(transfer) as t_ransfer,sum(brokerage) as t_brokerage,sum(stamp) as t_stamp,\n" +
                "       sum(management) as t_management,seateId,securitiesId from transactionData group by fundid,seateId,securitiesId,settlementDate)\n" +
                "    s join (select * from fund) f on s.fundId=f.fundId)";
        System.out.println(p_tableName);
        //创建一个Map，用于存储过程的调用传值
        Map<String, Object> map = new HashMap<>();
        //传入存储过程需要查询的表名
        map.put("p_tableName", p_tableName);
        //传入查询条件
        map.put("p_condition", sql);
        //传入分页显示条数
        map.put("p_pageSize", v_pageSize);
        //传入分页页码
        map.put("p_page", v_page);
        //创建out参数，返回数据总条数
        map.put("p_count", 0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor", null);
        //调用Mapper执行查询
        //System.out.println(p_tableName);
        System.out.println(sql);
        seatScheduleMapper.selectSeatSchedule(map);
        //接收返回数据
        List<SeatSchedule> securities = (List<SeatSchedule>) map.get("p_cursor");
        //接收返回总条数
        int v_count = (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("securities", securities);
        resultMap.put("count", v_count);
        //返回结果集Map
        System.out.println("业务逻辑返回的=" + resultMap.get("securities"));
        return resultMap;
    }

//    public static void main(String[] args) {
//        String time = "2020-09-17";
//        String[] split = time.split("-");
//        time=split[0]+"-"+split[1];
//        System.out.println(time);
//    }
}
