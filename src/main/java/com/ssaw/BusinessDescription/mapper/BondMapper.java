package com.ssaw.BusinessDescription.mapper;
/**
 * 债券信息设置dao层
 * @type:BondMapper interface
 * @version:v1.0
 * @authod:洪彬峰
 * @date:2020-09-01
 */
import com.ssaw.BusinessDescription.entity.Bond;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper

public interface BondMapper {
    //查询
   void selectBond(Map map);
    //增加
    public int insertBond(Bond bond);
    //删除
    public int  deleteBond(List securitiesId);
    //修改
    public int updateBond(Bond bond);
}
