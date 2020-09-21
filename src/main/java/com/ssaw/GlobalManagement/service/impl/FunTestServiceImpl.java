package com.ssaw.GlobalManagement.service.impl;

import com.ssaw.GlobalManagement.entity.FunTest;
import com.ssaw.GlobalManagement.mapper.FunTestMapper;
import com.ssaw.GlobalManagement.service.FunTestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 模块加载业务层实现类
 * create time: 2020/9/5 12:51
 * version number 1.0
 * @Param: null
 * @return
 */
@Service
@Transactional
public class FunTestServiceImpl implements FunTestService {
    @Resource
    FunTestMapper funTestMapper;


    @Override
    public List<FunTest> selectPidFun(int pid,int roleId) {
        List<FunTest> funTestList = funTestMapper.selectPidFun(pid,roleId);
        for (FunTest funTest : funTestList) {
            System.out.println(funTest);
            List<FunTest> funTests1 = funTestMapper.selectChildFun(funTest.getId(), roleId);
            if(funTests1.size()!=0){
                funTest.setChild(funTests1);
            }
            for (FunTest test : funTests1) {
                System.out.println(test);
                List<FunTest> funTests2 = funTestMapper.selectChildFun(test.getId(), roleId);
                if(funTests2.size()!=0){
                    test.setChild(funTests2);
                }
            }
        }
        return funTestList;
    }

    @Override
    public List<FunTest> selectChildFun(int pid,int roleId) {
        return funTestMapper.selectChildFun(pid,roleId);
    }


}
