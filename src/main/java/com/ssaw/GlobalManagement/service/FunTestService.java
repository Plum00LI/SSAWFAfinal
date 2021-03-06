package com.ssaw.GlobalManagement.service;

import com.ssaw.GlobalManagement.entity.FunTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 模块加载业务层
 * create time: 2020/9/5 12:51
 * version number 1.0
 * @Param: null
 * @return
 */
@Service
@Transactional
public interface FunTestService {
    /**
     * 查询指定角色所有父功能属性
     * @return FunTestList
     */
    public List<FunTest> selectPidFun(int pid,int roleId);
    /**
     * 查询指定父功能下的所有子功能属性
     * @return FunTestList
     */
    public List<FunTest> selectChildFun(int pid,int roleId);



}
