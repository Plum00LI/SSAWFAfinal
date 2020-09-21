package com.ssaw.BusinessDescription.mapper;

import com.ssaw.BusinessDescription.entity.Fund;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * create by: 曾钦辉
 * description: 基金信息mapper接口
 * create time: 2020/9/1 9:45
 *
  * @Param: null
 * @return
 */
@Mapper
public interface FundMapper {
    /**
     * 增加基金信息
     * @param fund
     * @return int
     */
    int insertFund(Fund fund);
    /**
     * 删除基金信息
     * @param fundId
     */
    void deleteFund(String fundId);

    /**
     * 修改基金信息
     * @param fund
     * @return
     */
    int updateFund(Fund fund);
    /**
     * 查询基金信息
     * @param
     * @return List<Fund>
     */
    void selectFund(Map map);
}
