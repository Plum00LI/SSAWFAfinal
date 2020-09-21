package com.ssaw.GlobalManagement.service.impl;

import com.ssaw.GlobalManagement.mapper.EndowMapper;
import com.ssaw.GlobalManagement.service.EndowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * create by: 佘高鹏
 * description: TODO
 * 权限模块业务层实现类
 * create time: 2020/9/9 9:04
 * version number 1.0
 * @Param: null
 * @return
 */
@Service
@Transactional
public class EndowServiceImpl implements EndowService {
    @Resource
    EndowMapper endowMapper;
    @Override
    public void deleteEndow(int roleId) {
        endowMapper.deleteEndow(roleId);
    }

    @Override
    public void insertEndow(int rolId, int funId) {
        endowMapper.insertEndow(rolId,funId);
    }
}
