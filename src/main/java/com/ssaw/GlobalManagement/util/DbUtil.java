package com.ssaw.GlobalManagement.util;

import com.ssaw.GlobalManagement.mapper.DbUtilMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 数据库工具类
 * @type: util
 * @version: v1.0
 * @author: plum
 * @date: 2020/09/03
 */
@Service
public class DbUtil {

    @Resource
    DbUtilMapper dbUtilMapper;

    /**
     * 请求当前 id编号/流水编号
     * @param tableName 需要请求 id编号/流水编号 的表名
     * @return 表内 当前应 可用的 id编号/流水编号
     */
    public String requestDbTableMaxId(String tableName){
        //获取当前传入表名的简写
        String tableNameAbbr = SysTableNameListUtil.getTableNameAbbr(tableName);
        //获取系统当前时间，返回时间字符串
        String systemDateTime = DateTimeUtil.getSystemDateTime(DateTimeUtil.type4);
        //创建一个Map传值
        HashMap<String,Object> map = new HashMap<>();
        //传入查询的表名
        map.put("tableName",tableName);
        //传入查询的表名的缩写
        map.put("tableNameAbbr",tableNameAbbr);
        //传入当前系统时间
        map.put("systemDateTime",systemDateTime);
        //放入结果对象
        map.put("returnAvailableNumber",null);
        //执行数据库查询
        dbUtilMapper.selectTableMaxId(map);
        //从结果map中获取返回的编号
        String result = (String) map.get("returnAvailableNumber");
        //返回结果
        return result;
    }

}
