package com.ssaw.CashManagement.controller;

import com.ssaw.CashManagement.entity.BankTreasurer;
import com.ssaw.CashManagement.service.BankTreasurerService;
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
 * @Eescription:实体类
 * @author:黄庆
 * @Version:1.0
 * @create:2020-09-01
 */
@RestController
@RequestMapping("/bankTreasurer")
public class BankTreasurerController {
    /**
     * 注入BankTreasurer服务层
     */
    @Resource
    BankTreasurerService bankTreasurerService;
    @Resource
    DbUtil dbUtil;

    /**
     * 界面请求的信息数据控制层查询所有资金调拨的方法
     * @param page 当前查询页数
     * @param limit 分页数据条目数
     * @param allocatingType 调拨类型
     * @param flag 调拨方向
     * @param dbTime 调拨日期
     * @return 界面要求的数据格式
     */
    @RequestMapping("selectBankTreasurer")
    @OperLog(message = "查询所有资金调拨",operation = OperationType.QUERY)
    public Map<String,Object> selectAccount(String page, String limit,String allocatingType,String flag,String dbTime){
        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map = bankTreasurerService.selectBankTreasurer(limit,page,allocatingType,flag,dbTime);
        System.out.println(dbTime);
        //从结果集中拿出结果
        List<BankTreasurer> bankTreasurerList= (List<BankTreasurer>) map.get("bankTreasurerList");
        int count= (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String,Object> json=new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",bankTreasurerList);
        //返回数据
        return json;
    }

    /**
     * 界面请求的信息数据控制层增加资金调拨的方法
     * @param bankTreasurer 资金调拨的实体类
     * @return 返回影响的行数 判断是否增加成功
     */
    @RequestMapping("insertBankTreasurer")
    @OperLog(message = "增加资金调拨",operation = OperationType.ADD)
    public int insertBankTreasurer(BankTreasurer bankTreasurer){
        int i = bankTreasurerService.insertBankTreasurer(bankTreasurer);
        return i;
    }

    /**
     * 界面请求的信息数据控制层删除资金调拨的方法
     * @param bankTreasurerId 资金调拨的Id
     * @return
     */
    @RequestMapping("deleteBankTreasurer")
    @OperLog(message = "删除资金调拨",operation = OperationType.DELETE)
    public int deleteBankTreasurer(String bankTreasurerId){
        System.out.println(bankTreasurerId);
        return  bankTreasurerService.deleteBankTreasurer(bankTreasurerId);
    }

    /**
     * 界面请求的信息数据控制层修改资金调拨的方法
     * @param bankTreasurer 资金调拨的实体类
     * @return
     */
    @RequestMapping("updateBankTreasurer")
    @OperLog(message = "修改资金调拨",operation = OperationType.UPDATE)
    public int updateBankTreasurer(BankTreasurer bankTreasurer){
        int i = bankTreasurerService.updateBankTreasurer(bankTreasurer);
        return i;
    }
}
