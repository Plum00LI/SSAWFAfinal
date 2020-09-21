package com.ssaw.BusinessDescription.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * create by: 曾钦辉
 * description: 管理人mapper类
 * create time: 2020/9/7 15:58
 *
  * @Param: null
 * @return
 */
@Mapper
public interface ManagerMapper {
    /**
     * 查询托管人信息
     * @param map
     */
    void selectManager(Map map);
}
