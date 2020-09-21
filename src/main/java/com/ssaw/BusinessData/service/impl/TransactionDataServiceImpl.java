package com.ssaw.BusinessData.service.impl;

import com.ssaw.BusinessData.entity.TransactionData;
import com.ssaw.BusinessData.mapper.TransactionDataMapper;
import com.ssaw.BusinessData.service.TransactionDataService;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * create by: 曾钦辉
 * description: 交易数据Service实现类
 * create time: 2020/9/1 11:26
 *
  * @Param: null
 * @return
 */
@Service
@Transactional
public class TransactionDataServiceImpl implements TransactionDataService {
    @Resource
    TransactionDataMapper transactionDataMapper;
    @Resource
    DbUtil dbUtil;
    @Override
    public HashMap selectTransactionData(int page, int limit, String dateTime, String securitiesName) {
        StringBuffer sqlWhere=new StringBuffer();
        if(dateTime!=null && !dateTime.equals("")){
            sqlWhere.append(" AND dateTime LIKE  '%"+dateTime+"%'" );
        }
        if(securitiesName!=null && !securitiesName.equals("")){
            sqlWhere.append(" AND securitiesName LIKE  '%"+securitiesName+"%'" );
        }


        HashMap tranMap = new HashMap();
        String transactionData=" (select * from transactionData tr left join securities se on tr.securitiesId=se.securitiesId left join account ac on tr.accountId=ac.accountId left join seate se on tr.seateId=se.seateId left join brokers br on tr.brokersId=br.brokersId left join fund f on tr.fundId = f.fundId) ";
        tranMap.put("p_tableName", transactionData);
        tranMap.put("p_condition",sqlWhere.toString());
        tranMap.put("p_pageSize",limit);
        tranMap.put("p_page",page);
        tranMap.put("p_count",0);
        tranMap.put("p_cursor",null);
        transactionDataMapper.selectTransactionData(tranMap);
        return tranMap;
    }

    @Override
    public int insertTransactionData(TransactionData transactionData) {
        System.out.println(transactionData.getFundId());
        transactionData.setTransactionDataId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.TD));
        int transactionDataMode = transactionData.getTransactionDataMode();
        if(transactionDataMode==1){
            transactionData.setFlag(-1);
        }else if(transactionDataMode==2){
            transactionData.setFlag(1);
        }
        else if(transactionDataMode==3){
            transactionData.setFlag(1);
        } 
        else if(transactionDataMode==4){
            transactionData.setFlag(1);
        }
        return transactionDataMapper.insertTransactionData(transactionData);
    }

    @Override
    public String deleteTransactionData(String transactionDataId , String transactionData) {
        if (transactionDataId!=null&&!transactionDataId.equals("")){
            transactionDataMapper.deleteTransactionData(transactionDataId);
            return "删除成功";
        }else {
            String[] split=new String[0];
            if (transactionData!=null&&!transactionData.equals("")){
                split= transactionData.split(",");
            }
            for(int i=0;i<split.length;i++){
                String[] split1 = split[i].split("-");
                if (split1[1].equals("0")){
                    transactionDataMapper.deleteTransactionData(split1[0]);
                    return "删除成功";
                }else {
                    return "您选中的数据中含有已结算数据";
                }
            }
            return "数据异常";
        }
    }

    @Override
    public int updateTransactionData(TransactionData transactionData) {
        return transactionDataMapper.updateTransactionData(transactionData);
    }
}
