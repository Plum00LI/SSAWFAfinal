package com.ssaw.GlobalManagement.service;

import com.ssaw.GlobalManagement.entity.Fun;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 功能业务层
 * create time: 2020/9/8 11:30
 * version number 1.0
  * @Param: null
 * @return
 */
@Service
public interface FunService {
    public List<Fun> selectFun(int pid, int roleId);
}
