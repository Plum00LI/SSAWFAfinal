package com.ssaw.BusinessDescription.service.impl;

import com.ssaw.BusinessDescription.entity.Bond;
import com.ssaw.BusinessDescription.mapper.BondMapper;
import com.ssaw.BusinessDescription.service.BondService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**债券信息设置实现类
 * @program:TescComment
 * @version:v1.0
 * @Description:实体类
 * @authod:洪彬峰
 * @adte:2020-09-01
 */
@Service
@Transactional
public class BondServiceImpl implements BondService {
    @Resource
    BondMapper bondMapper;

    @Override
    public Map<String, Object> selectBond(String pageSize, String page,String securitiesId,String drawStartDate) {
        Map<String, Object> resultMap = new HashMap<>();
        //定义分页
        int v_pageSize = 0;
        //判断传入的pageSize是否为null/空
        if (pageSize!=null &&!pageSize.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_pageSize=Integer.parseInt(pageSize);
        }
        //定义一个分页页码变量
        int v_page = 0;
        //判断传入的page是否为null/空
        if (page!=null&&!page.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_page=Integer.parseInt(page);
        }
        StringBuffer sqlWhere=new StringBuffer();
        if (securitiesId!=null && !securitiesId.equals("")){
            sqlWhere.append(" AND securitiesId LIKE  '%"+securitiesId+"%'" );
        }
       if (drawStartDate!=null&&!drawStartDate.equals("")){
           sqlWhere.append(" AND drawStartDate LIKE  '%"+drawStartDate+"%'" );

       }
        //创建一个Map，用于存储过程的调用传值
        Map<String,Object> map=new HashMap<>();
        //传入存储过程需要查询的表名
        map.put("p_tableName","bond");
        //传入查询条件
        map.put("p_condition",sqlWhere.toString());
        //传入分页显示条数
        map.put("p_pageSize",v_pageSize);
        //传入分页页码
        map.put("p_page",v_page);
        //创建out参数，返回数据总条数
        map.put("p_count",0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor",null);
        //调用Mapper执行查询
        bondMapper.selectBond(map);
        //接收返回数据
        List<Bond> bondList= (List<Bond>) map.get("p_cursor");
        //接收返回总条数
        int v_count = (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("bondList",bondList);
        resultMap.put("count",v_count);
        String p_condition = (String) map.get("p_condition");
        System.out.println(p_condition);
        //返回结果集Map
        System.out.println(v_count);
        System.out.println(bondList);
        System.out.println(sqlWhere.toString());
        return resultMap;
    }

    @Override
    public int insertBond(Bond bond) {
        int msg = bondMapper.insertBond(bond);
        return msg;
    }

    @Override
    public int   deleteBond(String securitiesId) {

        String[] split=securitiesId.split(",");
        ArrayList<Object> arrayList=new ArrayList<>();
        for (String id :split){
            arrayList.add(id);
        }
        return bondMapper.deleteBond(arrayList);
    }



    @Override
    public int updateBond(Bond bond) {
        int i = bondMapper.updateBond(bond);
        return i;
    }
}
