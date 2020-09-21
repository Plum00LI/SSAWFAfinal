package com.ssaw.BusinessProcessing.service.impl;

import com.ssaw.BusinessProcessing.entity.TaSettlement;
import com.ssaw.BusinessProcessing.mapper.TaSettlementMapper;
import com.ssaw.BusinessProcessing.service.TaSettlementService;
import com.ssaw.CashManagement.entity.BankTreasurer;
import com.ssaw.CashManagement.mapper.BankTreasurerMapper;
import com.ssaw.GlobalManagement.util.DateTimeUtil;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import com.ssaw.GlobalManagement.util.SysUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
@Transactional
public class TaSettlementServiceImpl implements TaSettlementService {
    @Resource
    DbUtil dbUtil;
    @Resource
    TaSettlementMapper taSettlementMapper;
    @Resource
    BankTreasurerMapper bankTreasurerMapper;
    @Override
    public Map<String, Object> selectTaSettlement(String pageSize, String page, String dateTime, String transactionType, String status) {
        StringBuffer sqlWhere = new StringBuffer();
        Map<String,Object> resultMap=new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize = 0;
        //判断传入的pageSize是否为null/空
        if (pageSize!=null&&!pageSize.equals("")){
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
        if(dateTime!=null&&!dateTime.equals("")){
            sqlWhere.append(" AND dateTime = '"+dateTime+"'" );
        }
        int v_transactionType=0;
        if(transactionType!=null && !transactionType.equals("")){
            v_transactionType=Integer.parseInt(transactionType);
            sqlWhere.append(" AND transactionType ="  +v_transactionType );
        }
        int v_status=0;
        if (status!=null&&!status.equals("")){
            v_status=Integer.parseInt(status);
            sqlWhere.append(" AND transactionstatus =  "+v_status );
        }

        //创建一个Map，用于存储过程的调用传值
        Map<String,Object> map = new HashMap<>();
        //传入存储过程需要查询的表名
        map.put("p_tableName","taTransaction");
        //传入查询条件
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
        taSettlementMapper.selectTaSettlement(map);
        //接收返回数据
        List<TaSettlement> TaSettlementList= (List<TaSettlement>) map.get("p_cursor");
        //接收返回总条数
        int v_count = (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("TaSettlementList",TaSettlementList);
        resultMap.put("count",v_count);
        String p_condition = (String) map.get("p_condition");
        System.out.println(p_condition);
        //返回结果集Map
        System.out.println(v_count);
        System.out.println(TaSettlementList);
        System.out.println("查询结果为"+sqlWhere.toString());
        return resultMap;
    }

    @Override
    public int updateSettlement(String taSettlement) {
        List<TaSettlement> TaSettlementList = SysUtil.jsonToArrayList(taSettlement, TaSettlement.class);
        for (TaSettlement taSettlement1:TaSettlementList){
            BankTreasurer bankTreasurer = new BankTreasurer();
            bankTreasurer.setBankTreasurerId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.BT));
            bankTreasurer.setFundId(taSettlement1.getFundId());
            bankTreasurer.setTotalPrice(taSettlement1.getPrice());
            bankTreasurer.setAccountId(taSettlement1.getAccountId());
            bankTreasurer.setFlag(1);
            bankTreasurer.setDbTime(taSettlement1.getDateTime());
            String date= DateTimeUtil.getSystemDateTime("yyyy-MM-dd");
            bankTreasurer.setDateTime(date);
            bankTreasurer.setBusinessId(taSettlement1.getTaTransactionId());
            bankTreasurer.setAllocatingType(4);
            bankTreasurer.setBankTreasurerDesc("");
            bankTreasurerMapper.insertBankTreasurer(bankTreasurer);
            int status = taSettlement1.getTransactionStatus();
            String transactionDataId = taSettlement1.getTaTransactionId();
                if (status==0){
                taSettlementMapper.updateTaSettlement(1,taSettlement1.getTaTransactionId());
                bankTreasurerMapper.insertBankTreasurer(bankTreasurer);
            }
            System.out.println(bankTreasurer);
        }
        return 1;
    }
    @Override
    public int updateSettlementTwo(String taSettlement) {
        List<TaSettlement> TaSettlementList = SysUtil.jsonToArrayList(taSettlement, TaSettlement.class);
        for (TaSettlement taSettlement1 : TaSettlementList) {
            System.out.println(taSettlement1);
            int status = taSettlement1.getTransactionStatus();
            String transactionDataId = taSettlement1.getTaTransactionId();
            System.out.println(status);
            if (status==1){
                taSettlementMapper.updateTaSettlementTwo(0,taSettlement1.getTaTransactionId());
                bankTreasurerMapper.deleteBankTreasurerByDepositId(transactionDataId);
            }
        }
        return 1;
    }
}
