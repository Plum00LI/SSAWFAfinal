package com.ssaw.BusinessDescription.controller;

import com.ssaw.BusinessDescription.entity.Bond;
import com.ssaw.BusinessDescription.service.BondService;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.OperationType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:TescComment
 * @Description:实体类
 * @authod:洪彬峰
 * @create:2020-09-01
 */
@RestController
public class BondController  {
    @Resource
    BondService bondService;
    @Resource
    DbUtil dbUtil;
    @OperLog(message = "查询债券信息设置",operation = OperationType.QUERY)
   @RequestMapping("selectBond")
   public Map<String,Object> selectBond(String page, String limit,String securitiesId,String drawStartDate) {
       System.out.println(securitiesId+","+","+drawStartDate);
       Map<String,Object> map=bondService.selectBond(limit,page,securitiesId,drawStartDate);
       List<Bond> bondList= (List<Bond>) map.get("bondList");
       System.out.println("进来了");
       int count = (int) map.get("count");

       //以layui要求存储响应数据格式
       Map<String, Object> json = new HashMap<>();
       json.put("code",0);
       json.put("msg","");
       json.put("count",count);
       json.put("data",bondList);
       //返回数据
       return json;


    }
    @OperLog(message = "添加债券信息设置",operation = OperationType.ADD)
    @RequestMapping("insertBond")
    public int insertBond(Bond bond) {
        System.out.println("进来了");
        System.out.println("bond="+bond);
        int i = bondService.insertBond(bond);
        System.out.println(bond);
        return i;
    }
    @OperLog(message = "删除债券信息设置",operation = OperationType.DELETE)
    @RequestMapping("deleteBond")
    public int  deleteBond(String securitiesId) {
        System.out.println("进来了");
        int a = bondService.deleteBond(securitiesId);
        return a;

    }
    @OperLog(message = "修改债券信息设置",operation = OperationType.UPDATE)
    @RequestMapping("updateBond")
    public int updateBond(Bond bond) {
        System.out.println("进来了");
        System.out.println(bond);

        return bondService.updateBond(bond);
    }
}
