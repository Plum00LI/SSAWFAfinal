package com.ssaw.BusinessDescription.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * create by: 曾钦辉
 * description: 托管人mapper类
 * create time: 2020/9/7 10:29
 *
  * @Param: null
 * @return
 */
@Mapper
public interface TrusteeMapper {
    /**
     * 查询托管人信息
     * @param map
     */
    void selectTrustee(Map map);
}
