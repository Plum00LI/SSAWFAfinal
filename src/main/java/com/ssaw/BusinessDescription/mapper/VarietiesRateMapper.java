package com.ssaw.BusinessDescription.mapper;

import com.ssaw.BusinessDescription.entity.VarietiesRate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 交易所品种费率 的dao层
 * @type:VarietiesRateMapper
 * @version v1.0
 * @author:阙魁
 * @create:2020-09-01
 */
@Mapper
public interface VarietiesRateMapper {
    //查询与分页查询
    public void selectVarietiesRate(Map map);
    //删除
    public void deleteVarietiesRate(int exchangeName,int rateType);
    //增加
    public int insertVarietiesRate(VarietiesRate varietiesRate);
    //修改
    public int updateVarietiesRate(VarietiesRate varietiesRate);
    //按条件查询
    public List<VarietiesRate> selectVarietiesRate2(int exchangeName,int rateType);
}
