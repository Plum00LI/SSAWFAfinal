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
        //查询指定角色所有父功能
        List<FunTest> funTestList = funTestMapper.selectPidFun(pid,roleId);
        //遍历功能集合
        for (FunTest funTest : funTestList) {
           //根据集合内功能对象的ID循环查询对应的所有子功能集合
            List<FunTest> funTests1 = funTestMapper.selectChildFun(funTest.getId(), roleId);
            //当子功能集合不为空时，将查到的子功能集合Set到夫功能对象的Child集合属性中
            if(funTests1.size()!=0){
                funTest.setChild(funTests1);
            }
            //再次遍历子功能集合，
            for (FunTest test : funTests1) {
              //根据集合内功能对象的ID循环查询对应的所有子功能集合
                List<FunTest> funTests2 = funTestMapper.selectChildFun(test.getId(), roleId);
                //当子功能集合不为空时，将查到的子功能集合Set到夫功能对象的Child集合属性中
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
