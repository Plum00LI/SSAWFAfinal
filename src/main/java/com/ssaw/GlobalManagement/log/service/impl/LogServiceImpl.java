package com.ssaw.GlobalManagement.log.service.impl;

import com.ssaw.GlobalManagement.log.entity.SysLog;
import com.ssaw.GlobalManagement.log.mapper.LogMapper;
import com.ssaw.GlobalManagement.log.service.LogService;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {
    @Resource
    LogMapper logMapper;

    @Override
    public int insertLog(SysLog log) {
        return logMapper.insertLogInfo(log);
    }

    @Override
    public Map<String, Object> selectLog(String limit,String page) {
        //创建一个结果集Map用于存放两个结果变量
        Map<String, Object> resultMap = new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize = 0;
        //判断传入的pageSize是否为null/空
        if (limit!=null&&!limit.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_pageSize=Integer.parseInt(limit);
        }
        //定义一个分页页码变量
        int v_page = 0;
        //判断传入的page是否为null/空
        if (page!=null&&!page.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_page=Integer.parseInt(page);
        }
        //创建一个Map，用于存储过程的调用传值
        Map<String,Object> map = new HashMap<>();
        String s = "(select * from "+ SysTableNameListUtil.UI+" ui join";
        //传入存储过程需要查询的表名
        map.put("p_tableName",SysTableNameListUtil.SL);
        //传入查询条件
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
        logMapper.selectLogInfo(map);
        //接收返回数据
        List<SysLog> logInfos = (List<SysLog>) map.get("p_cursor");
        //接收返回总条数
        int v_count = (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("logInfos",logInfos);
        resultMap.put("count",v_count);
        //返回结果集Map
        return resultMap;
    }
}
