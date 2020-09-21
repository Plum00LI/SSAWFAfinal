package com.ssaw.BusinessDescription.service;

import com.ssaw.BusinessDescription.entity.Seate;

import java.util.HashMap;

/**
 * @data 2020/9/5 am
 *席位信息表数据库访问接口类
 */

public interface SeateService {
    /**
     * 增加
     * @param seate
     * @return
     */
    int seateInsert(Seate seate);


    /**
     * 删除
     * @param seateId
     * @return
     */
    int seateDelete(String seateId);


    /**
     * 修改
     * @param seate
     * @return
     */
    int seateUpdate(Seate seate);


    /**
     * 查询
     * @return
     */
    public HashMap seateSelect(int page, int limit, String seateName,String brokersId,String modules);
}
