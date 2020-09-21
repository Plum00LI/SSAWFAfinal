package com.ssaw.BusinessDescription.service.impl;

import com.ssaw.BusinessDescription.entity.VarietiesRate;
import com.ssaw.BusinessDescription.mapper.VarietiesRateMapper;
import com.ssaw.BusinessDescription.service.VarietiesRateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易所品种费率 的impl层
 *
 * @version v1.0
 * @type:VarietiesRateServiceImpl
 * @author:阙魁
 * @create:2020-09-01
 */
@Service
@Transactional
public class VarietiesRateServiceImpl implements VarietiesRateService {

    @Resource
    VarietiesRateMapper varietiesRateMapper;

    //查询
    @Override
    public Map<String, Object> selectVarietiesRate(String pageSize, String page,String exchangeName,String rateType) {
        //创建一个结果集Map用于存放两个结果变量
        Map<String, Object> resultMap = new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize = 0;
        //判断传入的pageSize是否为null/空
        if (pageSize != null && !pageSize.equals("")) {
            //通过Integer包装类将String类型转换成int基础数据类型
            v_pageSize = Integer.parseInt(pageSize);
        }
        //定义一个分页页码变量
        int v_page = 0;
        //判断传入的page是否为null/空
        if (page != null && !page.equals("")) {
            //通过Integer包装类将String类型转换成int基础数据类型
            v_page = Integer.parseInt(page);
        }
        StringBuffer sqlWhere=new StringBuffer();

        if (exchangeName!=null&&!exchangeName.equals("")){
            int v_exchangeName=Integer.parseInt(exchangeName);
            sqlWhere.append(" and exchangeName="+v_exchangeName+" ");
        }
        if(rateType!=null && !rateType.equals("")){
            int v_rateType=Integer.parseInt(rateType);
            sqlWhere.append(" and rateType="+v_rateType+" ");
        }


        //创建一个Map，用于存储过程的调用传值
        Map<String, Object> map = new HashMap<>();
        //传入存储过程需要查询的表名
        map.put("p_tableName", "varietiesRate");
        //传入查询条件
        map.put("p_condition", sqlWhere.toString());
        //传入分页显示条数
        map.put("p_pageSize", v_pageSize);
        //传入分页页码
        map.put("p_page", v_page);
        //创建out参数，返回数据总条数
        map.put("p_count", 0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor", null);
        //调用Mapper执行查询
        varietiesRateMapper.selectVarietiesRate(map);
        //接收返回数据
        List<VarietiesRate> varietiesRates = (List<VarietiesRate>) map.get("p_cursor");
        //接收返回总条数
        int v_count = (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("varietiesRates", varietiesRates);
        resultMap.put("count", v_count);
        //返回结果集Map
        return resultMap;
    }


    //刪除
    @Override
    public void deleteVarietiesRate(int exchangeName, int rateType) {
        varietiesRateMapper.deleteVarietiesRate(exchangeName, rateType);
    }

    // 批量删除
    @Override
    public void deleteVarietiesRate2(String exchangeName, String rateType) {
        //定义exchangeName合rateType的数组
        System.out.println("地址："+exchangeName+"\n类型："+rateType);
        String[] exchangeNameList = new String[0];
        String[] rateTypeList = new String[0];
        //进行判断若不为空则进行split切割

        if (exchangeName != null && !exchangeName.equals("")) {
            //split切割获取exchangeName数组
            exchangeNameList = exchangeName.split(",");
        }

        if (rateType != null && !rateType.equals("")) {
            //split切割获取rateType数组
            rateTypeList = rateType.split(",");
        }
        //运用for来获取数据进行mapper方法
        for (int i = 0, j = 0; i < exchangeNameList.length && j < rateTypeList.length; i++, j++) {
            System.out.println(exchangeNameList[i]);
            //把String类型强转成int类型
            int exchangeNameid = Integer.parseInt(exchangeNameList[i]);
            int rateTypeid = Integer.parseInt(rateTypeList[j]);

            System.out.println(rateTypeList[j]);
            //运用varietiesRateMapper中的方法进行操作
            varietiesRateMapper.deleteVarietiesRate(exchangeNameid, rateTypeid);
        }
    }

    //增加
    @Override
    public int insertVarietiesRate(VarietiesRate varietiesRate) {
        int i = varietiesRateMapper.insertVarietiesRate(varietiesRate);
        return i;
    }

    //修改
    @Override
    public int updateVarietiesRate(VarietiesRate varietiesRate) {
        int i = varietiesRateMapper.updateVarietiesRate(varietiesRate);
        return i;
    }
    //按条件查询
    @Override
    public int selectVarietiesRate2(int exchangeName, int rateType) {
        //
     List<VarietiesRate> varietiesRateList=   varietiesRateMapper.selectVarietiesRate2(exchangeName, rateType);

        if (varietiesRateList!=null&&varietiesRateList.size()>0) {
            System.out.println("getExchangeName="+varietiesRateList.get(0).getExchangeName());
            System.out.println("getRateType="+varietiesRateList.get(0).getRateType());
            if(exchangeName==varietiesRateList.get(0).getExchangeName()&&rateType==varietiesRateList.get(0).getRateType()){
                return 0;
            }else {
                return 1;
            }
        }else {
            return 1;
        }

    }
}
