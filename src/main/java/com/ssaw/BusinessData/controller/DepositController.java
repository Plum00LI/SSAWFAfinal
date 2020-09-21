package com.ssaw.BusinessData.controller;

import com.ssaw.BusinessData.entity.Deposit;
import com.ssaw.BusinessData.service.DepositService;
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
 * @Eescription:Controller控制层
 * @author:黄庆
 * @Version:1.0
 * @create:2020-09-05
 */
@RestController
@RequestMapping("/deposit")
public class DepositController {

    /**
     * 注入Deposit服务层
     */
    @Resource
    DepositService depositService;

    @Resource
    DbUtil dbUtil;

    /**
     * 界面请求的信息数据控制层查询所有存款业务的方法
     * @param page 当前查询页数
     * @param limit 分页数据条目数
     * @param businessType 业务类型
     * @param endDate 存款业务到期时间
     * @return 界面要求的数据格式
     */
    @RequestMapping("selectDeposit")
    @OperLog(message = "查询所有存款业务",operation = OperationType.QUERY)
    public Map<String,Object> selectDeposit(String page, String limit,String businessType,String endDate){
        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map = depositService.selectDeposit(limit,page,businessType,endDate);
        //从结果集中拿出结果
        List<Deposit> depositList= (List<Deposit>) map.get("depositList");
        int count= (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String,Object> json=new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",depositList);
        //返回数据
        return json;
    }

    /**
     * 界面请求的信息数据控制层增加存款业务的方法
     * @param deposit 存款业务的实体类
     * @return 返回影响的行数 判断是否增加成功
     */
    @RequestMapping("insertDeposit")
    @OperLog(message = "增加所有存款业务",operation = OperationType.ADD)
    public int insertDeposit(Deposit deposit){
        System.out.println(deposit);
        int i = depositService.insertDeposit(deposit);
        return i;
    }

    /**
     * 界面请求的信息数据控制层删除存款业务的方法
     * @param depositId 存款业务的Id
     * @return 返回影响的行数 判断是否删除成功
     */
    @RequestMapping("deleteDeposit")
    @OperLog(message = "删除存款业务",operation = OperationType.DELETE)
    public int deleteDeposit(String depositId){
        System.out.println(depositId);
        int i = depositService.deleteDeposit(depositId);
        return i;
    }

    /**
     * 界面请求的信息数据控制层存款业务到期处理的方法
     * @param deposit 存款业务的实体类
     * @return
     */
    @RequestMapping("updateDeposit")
    @OperLog(message = "存款业务的到期处理",operation = OperationType.UPDATE)
    public int updateDeposit(Deposit deposit){
        System.out.println("deposit进去了===========");
        int i = depositService.updateDeposit(deposit);
        System.out.println(deposit);
        return i;
    }
}


