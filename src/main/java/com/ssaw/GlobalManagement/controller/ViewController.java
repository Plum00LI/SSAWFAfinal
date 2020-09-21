package com.ssaw.GlobalManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 视图访问控制层
 * @type: controller
 * @version: v1.0
 * @author: plum
 * @date: 2020/09/02
 */
@Controller
@RequestMapping("page")
public class ViewController {
//    @RequestMapping("index")
//    public String index(){
//        return "index";
//    }
//
//    @RequestMapping("userInfoTable")
//    public String userInfoTable(){
//        return "GlobalManagement/userInfoTable";
//    }

    @RequestMapping("*")
    public String getPage(HttpServletRequest req){
        String requestURI = req.getRequestURI();
        int length = requestURI.length();
        return requestURI.substring(6, length);
    }
}
