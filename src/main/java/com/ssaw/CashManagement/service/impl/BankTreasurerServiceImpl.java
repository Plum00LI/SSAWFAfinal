package com.ssaw.CashManagement.service.impl;

import com.ssaw.CashManagement.entity.BankTreasurer;
import com.ssaw.CashManagement.mapper.BankTreasurerMapper;
import com.ssaw.CashManagement.service.BankTreasurerService;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*@program:TescComment
*@Eescription:资金调拨
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
@Service
@Transactional
public class BankTreasurerServiceImpl implements BankTreasurerService {
    /**
     * 注入BankTreasurer的Mapper层
     */
    @Resource
    BankTreasurerMapper bankTreasurerMapper;
    @Resource
    DbUtil dbUtil;

    /**
     * 查询所有资金调拨的实现类方法（带分页，返回数据和总条目数）
     * @param pageSize  当前查询页数
     * @param page 分页数据条目数
     * @param allocatingType 调拨类型
     * @param flag 调拨方向
     * @param dbTime 调拨日期
     * @return 查询的结果集Map
     */
    @Override
    public Map<String, Object> selectBankTreasurer(String pageSize, String page,String allocatingType,String flag,String dbTime) {
        System.out.println(allocatingType);
        System.out.println(flag);
        System.out.println(dbTime);
        //创建一个结果集Map用于存放两个结果变量
        Map<String,Object> resultMap=new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize=0;
        //判断传入的pageSize是否为空/null
        if (pageSize!=null && !pageSize.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_pageSize=Integer.parseInt(pageSize);
        }
        //定义一个分页页码变量
        int v_page = 0;
        //判断传入的page是否为null/空
        if (page!=null&&!page.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_page=Integer.parseInt(page);
        }
        //创建一个Map 用于存款过程的调用传值
        Map<String,Object> map=new HashMap<>();
        String p_tableName="(select * from " + SysTableNameListUtil.BT +" b join (select accountName,accountId from "+ SysTableNameListUtil.A+" )  a on b.accountId=a.accountId)";

        System.out.println(p_tableName);
        //传入存储过程需要查询的表名
        map.put("p_tableName",p_tableName);
        int v_allocatingType=0;
        int v_flag=0;
        StringBuffer sqlWhere=new StringBuffer();
        if (allocatingType!=null && !allocatingType.equals("")){
            v_allocatingType=Integer.parseInt(allocatingType);
            sqlWhere.append(" and allocatingType =" +v_allocatingType);
        }
        if (flag!=null && !flag.equals("")){
            v_flag=Integer.parseInt(flag);
            sqlWhere.append(" and flag =" +v_flag);
        }
        if (dbTime!=null && !dbTime.equals("")){
            sqlWhere.append(" and dbTime ='"+dbTime+"'");
        }
        //传入查询的条件
        map.put("p_condition",sqlWhere.toString());
        //传入分页显示条数
        map.put("p_pageSize",v_pageSize);
        //传入分页页码
        map.put("p_page",v_page);
        //创建out参数，返回数据总条数
        map.put("p_count",0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor",null);
        //调用Mapper执行查询
        bankTreasurerMapper.selectBankTreasurer(map);
        //接收返回数据
        List<BankTreasurer> bankTreasurerList= (List<BankTreasurer>) map.get("p_cursor");

        //接收返回总条数
        int v_count= (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("bankTreasurerList",bankTreasurerList);
        resultMap.put("count",v_count);
        System.out.println(resultMap.get("bankTreasurerList"));
        return resultMap;
    }

    /**
     * 资金调拨的增加实现类方法
     * @param bankTreasurer 资金调拨实体类
     * @return
     */
    @Override
    public int insertBankTreasurer(BankTreasurer bankTreasurer) {
        bankTreasurer.setBankTreasurerId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.BT));
        return bankTreasurerMapper.insertBankTreasurer(bankTreasurer);
    }

    /**
     * 资金调拨的修改实现类方法
     * @param bankTreasurer 资金调拨实体类
     * @return
     */
    @Override
    public int updateBankTreasurer(BankTreasurer bankTreasurer) {
        return bankTreasurerMapper.updateBankTreasurer(bankTreasurer);
    }

    /**
     * 资金调拨通过资金调拨Id的删除实现类方法
     * @param bankTreasurerId 资金调拨Id
     * @return
     */
    @Override
    public int deleteBankTreasurer(String bankTreasurerId) {
        String[] split = bankTreasurerId.split(",");
        ArrayList<Object> bankTreasurerList=new ArrayList<>();
        for (String id : split) {
            bankTreasurerList.add(id);
        }
        return bankTreasurerMapper.deleteBankTreasurer(bankTreasurerList);
    }

    /**
     * 资金调拨通过存款业务Id的删除实现类方法
     * @param depositId 存款业务Id
     * @return
     */
    @Override
    public int deleteBankTreasurerByDepositId(String depositId) {
        return bankTreasurerMapper.deleteBankTreasurerByDepositId(depositId);
    }


}
