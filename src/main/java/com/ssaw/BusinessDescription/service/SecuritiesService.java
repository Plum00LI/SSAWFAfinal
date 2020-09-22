package com.ssaw.BusinessDescription.service;

import com.ssaw.BusinessDescription.entity.Securities;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

/**
 * 债券参数
 * @type Securities的service类
 * @author fusaiying
 * @date 2020-09-02
 * @version 1.0
 */
@Service
public interface SecuritiesService {
    /**
     * 分页查询 调用存储过程
     * @return 条件查询的集合
     */
    public Map<String,Object> selectSecurities(String pageSize, String page,String securitiesId,String exchange,String securitiesType);
    /**
     * 增加
     * @param securities
     */
    public int insertSecurities(Securities securities);
    /**
     * 删除
     *根据证券编号 securitiesId
     */
    public int deleteSecurities(String securitiesId);
    /**
     * 修改
     * 根据证券编号
     */
    public int updateSecurities(Securities securities);


}
