package com.ssaw.BusinessDescription.service;

import com.ssaw.BusinessDescription.entity.Bond;
import org.springframework.stereotype.Service;

import java.util.Map;

/**债券信息设置service
 * @program:TescComment
 * @version:v1.0
 * @Description:实体类
 * @authod:洪彬峰
 * @date:2020-09-01
 */
@Service

public interface BondService {
    //查询
        Map<String,Object>selectBond(String pageSize, String page,String securitiesId,String drawStartDate);
    //增加
    int insertBond(Bond bond);
    //删除
     int  deleteBond(String securitiesId);
    //修改
     int updateBond(Bond bond);
}
