package com.ssaw.DayEndProcessing.mapper;

import com.ssaw.DayEndProcessing.entity.SecuritiesValueStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 证券净值统计查询持久层
 * create time: 2020/9/11 16:53
 * version number 1.0
  * @Param: null
 * @return
 */
@Mapper
public interface SecuritiesValueStatisticsMapper {

    /**
     * 证券查询方法
     * @param dateTime 当日时间
     * @param fundID   基金ID
     * @param dateTimeTwo 当为周六周日时，需要传入的时间条件
     * @return
     */
    public List<SecuritiesValueStatistics> selectSecuritiesValueStatistics(String dateTime, String fundID, String dateTimeTwo);

    /**
     * 债券信息方法
     * @param dateTime 查询日期
     * @param fundID   基金ID
     * @param type     类型
     * @return
     */

    public List<SecuritiesValueStatistics> selectDebentureInterestValueStatistics(String dateTime, String fundID, int type);

    /**
     *
     * @param dateTime 查询日期
     * @param fundID   基金ID
     * @param type     类型
     * @param flag     流入流出
     * @return
     */
    public List<SecuritiesValueStatistics> selectSecuritiesClearingValueStatistics(String dateTime, String fundID, int type, int flag);
}
