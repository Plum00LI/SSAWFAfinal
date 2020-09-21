package com.ssaw.BusinessDescription.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * create by: 曾钦辉
 * description: 托管人service类
 * create time: 2020/9/7 10:40
 *
  * @Param: null
 * @return
 */
@Service
@Transactional
public interface TrusteeService {
    /**
     * 查询托管人
     * @param
     * @return Map<String,Object>
     */
    Map<String,Object> selectTrustee(String pageSize, String page);
}
