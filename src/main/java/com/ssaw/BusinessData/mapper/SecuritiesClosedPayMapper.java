package com.ssaw.BusinessData.mapper;

import com.ssaw.BusinessData.entity.SecuritiesClosedPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @program: TescComment
 * @Description:证券应收应付Dao类
 * @author: 瞿平
 * @version:1.0
 * @create: 2020-09-09
 */
@Mapper
public interface SecuritiesClosedPayMapper {
    /**
     * 分页查询
     * @param map 结果集
     * @return
     */
    public Map<String,Object> selectSecuritiesClosedPay(Map map);

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
    int deleteSecuritiesClosedPay(List securitiesClosedPayId);


    //-------------------收益支付统计----------------
    /**
     * 根据时间、类型、流入流出、基金Id删除信息
     * @param securitiesClosedPay
     */
    void deleteSecuritiesNew(SecuritiesClosedPay securitiesClosedPay);

    /**
     * 根据时间、类型、流入流出、基金Id查询
     * @param securitiesClosedPay
     * @return
     */
    List<SecuritiesClosedPay> selectSecuritiesNew(SecuritiesClosedPay securitiesClosedPay);

    //傅赛赢
    /**
     * 根据证券应收应付实体对象来删除
     * @param securitiesClosedPay
     * @return
     */
    public int deleteSecuritiesClosedPayByPojo(SecuritiesClosedPay securitiesClosedPay);

}
