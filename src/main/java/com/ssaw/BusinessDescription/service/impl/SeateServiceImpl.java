package com.ssaw.BusinessDescription.service.impl;


import com.ssaw.BusinessDescription.entity.Seate;
import com.ssaw.BusinessDescription.mapper.SeateMapper;
import com.ssaw.BusinessDescription.service.SeateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @data 2020/9/5 am
 *席位信息表实现类
 */
@Service
public class SeateServiceImpl implements SeateService {

    @Resource
    SeateMapper seateMapper;

    /**
     * 增加
     * @param seate
     * @return
     */
    @Override
    public int seateInsert(Seate seate) {
        return seateMapper.seateInsert(seate);
    }


    /**
     * 删除
     * @param seateId
     * @return
     */
    @Override
    public int seateDelete(String seateId){
        //定义一个数组接收编号，切割字符串
        String[] split = seateId.split(",");
        //定义一个整型集合
        List<String> seateList = new ArrayList<String>();
        //循环数组
        for (String id : split) {
            //将数组循环的值添加到集合中，强转为整型
            seateList.add(id);
        }

        return seateMapper.seateDelete(seateList);
    }


    /**
     * 修改
     * @param seate
     * @return
     */
    @Override
    public int seateUpdate(Seate seate){
        return seateMapper.SeateUpdate(seate);
    }

    /**
     * 查询
     * @return
     */
    @Override
    public HashMap seateSelect(int page, int limit, String seateName,String brokersId,String modules) {
        String sql = "";
        //多表查询的拼接
        String tableName = "(select * from seate join brokers br on seate.brokersId = br.brokersId where 1=1";
        //券商Id条件查询
        if (brokersId != null && brokersId!="") {
            tableName = tableName + " and br.brokersId='"+brokersId+"')";
        }else {
            tableName = tableName + ")";
        }
        if (seateName != null) {
            sql = sql + " and seateName like '%" + seateName + "%'";
        }
        if (modules != null && modules!=""){
            sql=sql+" and seateType="+modules;
        }
        System.out.println(sql);
        HashMap seateMap = new HashMap();
        seateMap.put("p_tableName", tableName);
        seateMap.put("p_condition", sql);
        seateMap.put("p_pageSize", limit);
        seateMap.put("p_page", page);
        seateMap.put("p_count", 0);
        seateMap.put("p_cursor", null);
        seateMapper.seatSelect(seateMap);
        return seateMap;
    }
}
