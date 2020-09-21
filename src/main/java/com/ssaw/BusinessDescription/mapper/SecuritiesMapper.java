package com.ssaw.BusinessDescription.mapper;

import com.ssaw.BusinessDescription.entity.Securities;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 债券参数
 *
 * @author fusaiying
 * @version 1.0
 * @type Securities的dao层
 * @date 2020-09-01
 */
@Mapper
public interface SecuritiesMapper {
    /**
     * 按条件查询 分页查询
     * @return 条件查询的集合
     */
    public void selectSecurities(Map map);

    /**
     * 增加
     * @param securities
     */
    public int insertSecurities(Securities securities);

    /**
     * 删除
     * 根据证券编号 securitiesId
     * @param securitiesId
     */
    public int deleteSecurities(List securitiesId);

    /**
     * 修改
     * 根据证券编号
     */
    public int updateSecurities(Securities securities);

}
