package com.ssaw.BusinessDescription.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * create by: 曾钦辉
 * description:管理人service类
 * create time: 2020/9/7 16:04
 *
  * @Param: null
 * @return
 */
@Service
public interface ManagerService {
    /**
     * 查询管理人
     * @param
     * @return Map<String,Object>
     */
    Map<String,Object> selectManager(String pageSize, String page);
}
