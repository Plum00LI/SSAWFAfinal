package com.ssaw.BusinessDescription.mapper;


import com.ssaw.BusinessDescription.entity.Seate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @data 2020/9/4 am
 *券商信息表数据库访问接口类
 */

@Mapper
public interface SeateMapper {
    /**
     * 增加
     * @param seate
     * @return
     */
    @Insert("insert into seate(seateId, seateName, seateType, seateRate, brokersId, seateAddress, seateDesc) values(#{seateId}, #{seateName}, #{seateType}, #{seateRate}, #{brokersId}, #{seateAddress}, #{seateDesc})")
    int seateInsert(Seate seate);

    /**
     * 删除0
     * @param seateIdList
     * @return
     */
    public int seateDelete(List seateIdList);

    /**
     * 修改
     * @param seate
     * @return
     */
    @Update("update seate set seateName=#{seateName},seateType=#{seateType}, seateRate=#{seateRate}, brokersId=#{brokersId}, seateAddress=#{seateAddress}, seateDesc=#{seateDesc} where seateId=#{seateId}")
    int SeateUpdate(Seate seate);


    /**
     * 查询
     * @return
     */
    public HashMap seatSelect(HashMap hashMap);
}
