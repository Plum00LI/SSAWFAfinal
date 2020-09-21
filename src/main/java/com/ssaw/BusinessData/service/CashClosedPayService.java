package com.ssaw.BusinessData.service;

import com.ssaw.BusinessData.entity.CashClosedPay;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName CashClosedPayService
 * @Description: TODO
 * @Author 阙魁
 * @Date create in 22:51 2020/9/7
 * @Version 1.0
 **/
@Service

public interface CashClosedPayService {
    int insertCashClosedPay(CashClosedPay cashClosePay);
    int deleteCashClosedPay(String cashClosedPayId);
    int updateCashClosedPay(CashClosedPay cashClosePay);
    Map<String,Object> selectCashClosedPay(String pageSize, String page,String dateTime,String serviceType);

    //创建一个根据实体类来删除
    void deleteNew(CashClosedPay cashClosePay);
    //创建一个根据实体类来查询
    List selectNew(CashClosedPay cashClosePay);

    //收益计提
    int deleteNew2(CashClosedPay cashClosedPay);
}
