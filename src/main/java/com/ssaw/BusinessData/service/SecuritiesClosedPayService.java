package com.ssaw.BusinessData.service;

import com.ssaw.BusinessData.entity.SecuritiesClosedPay;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: TescComment
 * @Description:证券库存Bie类
 * @author: 瞿平
 * @version:1.0
 * @create: 2020-09-01
 */
@Service
public interface SecuritiesClosedPayService {
    /**
     * 分页查询
     * @param
     * @return
     */
    public Map<String,Object> selectSecuritiesClosedPay(String pageSize, String page,String securitiesName,String dateTime);

    /**
     * 增加
     * @return 返回一个int类型的参数
     */
    public int insertSecuritiesClosedPay(SecuritiesClosedPay securitiesClosedPay);

    /**
     * 修改
     * @return 返回一个int类型的参数
     */
    public int updateSecuritiesClosedPay(SecuritiesClosedPay securitiesClosedPay);

    /**
     * 批量删除
     * @param securitiesClosedPayId
     * @return
     */
    int deleteSecuritiesClosedPay(String securitiesClosedPayId);

    //-------------------收益支付统计----------------
    //时间、类型、流入流出、基金Id删除信息
    void deleteSecuritiesNew(SecuritiesClosedPay securitiesClosedPay);
    //根据时间、类型、流入流出、基金Id查询信息
    List selectSecuritiesNew(SecuritiesClosedPay securitiesClosedPay);


    //傅赛赢
    public int deleteSecuritiesClosedPayByPojo(SecuritiesClosedPay securitiesClosedPay);
}
