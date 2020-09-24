package com.ssaw.BusinessProcessing.service.impl;

import com.ssaw.BusinessProcessing.entity.Settlement;
import com.ssaw.BusinessProcessing.mapper.SettlementMapper;
import com.ssaw.BusinessProcessing.service.SettlementService;
import com.ssaw.CashManagement.entity.BankTreasurer;
import com.ssaw.CashManagement.mapper.BankTreasurerMapper;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import com.ssaw.GlobalManagement.util.SysUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * create by: 曾钦辉
 * description: 交易结算Service实现类
 * create time: 2020/9/10 9:31
 *
  * @Param: null
 * @return
 */

@Service
@Transactional
public class SettlementServiceImpl implements SettlementService {
    @Resource
    SettlementMapper settlementMapper;
    @Resource
    BankTreasurerMapper bankTreasurerMapper;
    @Resource
    DbUtil dbUtil;
    @Override
    public HashMap selectSettlement(int page, int limit, String dateTime, String transactionType,String status) {
        StringBuffer sqlWhere=new StringBuffer();
        if(dateTime!=null && !dateTime.equals("")){
            sqlWhere.append(" AND dateTime LIKE  '%"+dateTime+"%'" );
        }
        if(transactionType!=null && !transactionType.equals("")){
            sqlWhere.append(" AND v_transactionType LIKE '%"+transactionType+"%'");
        }
        int v_status=-1;//结算状态 结算1 or 未结算0
        if(status!=null && !status.equals("")){
            v_status=Integer.parseInt(status);
            sqlWhere.append(" AND status ="+v_status);
        }

        HashMap tranMap = new HashMap();
        String transactionData=" (select * from transactionData tr left join securities se on tr.securitiesId=se.securitiesId left join account ac on tr.accountId=ac.accountId left join seate se on tr.seateId=se.seateId left join brokers br on tr.brokersId=br.brokersId left join fund f on tr.fundId = f.fundId) ";
        tranMap.put("p_tableName", transactionData);
        tranMap.put("p_condition",sqlWhere.toString());
        tranMap.put("p_pageSize",limit);
        tranMap.put("p_page",page);
        tranMap.put("p_count",0);
        tranMap.put("p_cursor",null);
        settlementMapper.selectSettlement(tranMap);
        return tranMap;
    }

    @Override
    public int deleteSettlement(String transactionDataId) {
        return settlementMapper.deleteSettlement(transactionDataId);
    }

    @Override
    public int updateSettlement(String settlement) {
        List<Settlement> settlementList = SysUtil.jsonToArrayList(settlement, Settlement.class);

        for (Settlement settlement1 : settlementList) {
            BankTreasurer bankTreasurerPojo = new BankTreasurer();
            bankTreasurerPojo.setBankTreasurerId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.BT));
            bankTreasurerPojo.setFundId(settlement1.getFundId());

            bankTreasurerPojo.setAccountId(settlement1.getAccountId());
            bankTreasurerPojo.setAccountName(settlement1.getAccountName());
            bankTreasurerPojo.setFlag(settlement1.getFlag());
            bankTreasurerPojo.setTotalPrice(settlement1.getTotalSum());
            bankTreasurerPojo.setDbTime(settlement1.getSettlementDate());
            bankTreasurerPojo.setDateTime(settlement1.getDateTime());
            bankTreasurerPojo.setBusinessId(settlement1.getTransactionDataId());

            bankTreasurerPojo.setAllocatingType(3);

            settlement1.setTransactionDataDesc("交易结算资金调拨");
            bankTreasurerPojo.setBankTreasurerDesc(settlement1.getTransactionDataDesc());
            int status = settlement1.getStatus();
            String transactionDataId = settlement1.getTransactionDataId();
            if (status==0){
                settlementMapper.updateSettlement(1,transactionDataId);
                bankTreasurerMapper.insertBankTreasurer(bankTreasurerPojo);
            }
            System.out.println(bankTreasurerPojo);
        }
        return 1;

    }
    //反结算修改状态删除资金调拨
    @Override
    public int updateSettlementTwo(String settlement) {
        List<Settlement> settlementList = SysUtil.jsonToArrayList(settlement, Settlement.class);
        for (Settlement settlement1 : settlementList) {
            System.out.println(settlement1);
            int status = settlement1.getStatus();
            String transactionDataId = settlement1.getTransactionDataId();
            System.out.println(status);
            if (status==1){
                settlementMapper.updateSettlementTwo(0,transactionDataId);
                bankTreasurerMapper.deleteBankTreasurerByDepositId(transactionDataId);
            }
        }
        return 1;
    }
}
