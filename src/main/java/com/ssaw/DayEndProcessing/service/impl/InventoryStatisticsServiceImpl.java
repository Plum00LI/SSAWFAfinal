package com.ssaw.DayEndProcessing.service.impl;

import com.ssaw.DayEndProcessing.entity.*;
import com.ssaw.DayEndProcessing.mapper.InventoryStatisticsMapper;
import com.ssaw.DayEndProcessing.service.InventoryStatisticsService;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import com.ssaw.InventoryManagement.entity.*;
import com.ssaw.InventoryManagement.mapper.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:    InventoryStatisticsServiceImpl
 * Package:    com.ssaw.DayEndProcessing.service.impl
 * Description:     库存统计
 * Version:
 * Datetime:    2020/9/9   17:18
 * Author:   SYT
 */
@Service
@Transactional
public class InventoryStatisticsServiceImpl implements InventoryStatisticsService {
    //注入库存统计Mapper方法
    @Resource
    InventoryStatisticsMapper inventoryStatisticsMapper;
    //证券库存
    @Resource
    SecuritiesInventoryMapper securitiesInventoryMapper;
    //TA库存
    @Resource
    TaInventoryMapper taInventoryMapper;
    //现金库存
    @Resource
    CashInventoryMapper cashInventoryMapper;
    //现金应收应付库存
    @Resource
    CashClosedPayInventoryMapper cashClosedPayInventoryMapper;
    //证券应收应付库存
    @Resource
    SecuritiesClosedPayInventoryMapper securitiesClosedPayInventoryMapper;



    @Resource
    DbUtil dbUtil;

    @Override
    public List<InventoryStatistics> selectInventoryStatistics(String fundId, String dateTime, String invId) {
        System.out.println("fundId="+fundId);
        //创建List保持库存统计信息
        List<InventoryStatistics>  list=new ArrayList<InventoryStatistics>();
        //库存信息
        InventoryStatistics cashInventory=new InventoryStatistics(1,"现金库存",fundId,"admin","",0,"暂无");
        InventoryStatistics securitiesInventory=new InventoryStatistics(2,"证券库存",fundId,"admin","",0,"暂无");
        InventoryStatistics taInventory=new InventoryStatistics(3,"TA库存",fundId,"admin","",0,"暂无");
        InventoryStatistics securitiesClosedPayInventory=new InventoryStatistics(4,"证券应收应付库存",fundId,"admin","",0,"暂无");
        InventoryStatistics cashClosedPayInventory=new InventoryStatistics(5,"现金应收应付库存",fundId,"admin","",0,"暂无");
        //判断统计的库存
        if (invId !=null && !invId.equals("")){
            String[] invIdList=null;
            invIdList=invId.split(",");

            for (String strInvId : invIdList) {
                switch (strInvId){
                    case "1":
                        //现金库存统计
                        List<CashInventoryData> cashInventoryDataList=inventoryStatisticsMapper.selectCashInventory(dateTime,fundId);
                        cashInventory=new InventoryStatistics(1,"现金库存",fundId,"admain",dateTime,cashInventoryDataList.size(),"已统计");
                        if(cashInventoryDataList.size()!=0 && cashInventoryDataList.get(0)!=null) {
                            for (CashInventoryData cashInventoryData : cashInventoryDataList) {
                                System.out.println("现金库存" + cashInventoryData);
                                //删除原现金库存信息
                                cashInventoryMapper.deleteCashInventoryDate(cashInventoryData.getAccountId(),dateTime, fundId);
                                //增加新数据
                                CashInventory cashInventory1 = new CashInventory();
                                //现金库存Id
                                cashInventory1.setCashInventoryId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.CI));
                                //基金Id
                                cashInventory1.setFundId(fundId);
                                //现金余额
                                cashInventory1.setCashBlance(cashInventoryData.getCashTotal());
                                //现金账户ID
                                cashInventory1.setAccountId(cashInventoryData.getAccountId());
                                //统计日期
                                cashInventory1.setDateTime(dateTime);
                                //证券数量 ,固定为0
                                cashInventory1.setSecuritiesNum(0);
                                //备注
                                cashInventory1.setCashInventoryDesc("统计现金库存");
                                //现金库存
                                System.out.println("现金库存实体类" + cashInventory1);
                                //调用增加方法
                                cashInventoryMapper.insertCashInventory(cashInventory1);
                            }
                        }
                        break;
                    case "2":
                        //统计证券库存
                        List<SecuritiesInventoryData> securitiesInventoryList=inventoryStatisticsMapper.selectSecuritiesInventory(dateTime,fundId);
                        securitiesInventory = new InventoryStatistics(2,"证券库存",fundId,"admain",dateTime,securitiesInventoryList.size(),"已统计");
                        //遍历
                        if(securitiesInventoryList.size()!=0 && securitiesInventoryList.get(0)!=null) {
                            for (SecuritiesInventoryData securitiesInventoryData : securitiesInventoryList) {
                                System.out.println("我是证券库存统计 我查询到的结果为：" + securitiesInventoryData);
                                //删除证券库存信息
                                securitiesInventoryMapper.deleteSecuritiesInventoryDate(securitiesInventoryData.getSecuritiesId(),dateTime, fundId);
                                //增加证券库存数据
                                SecuritiesInventory securitiesInventory1 = new SecuritiesInventory();
                                //证券库存ID
                                securitiesInventory1.setSecuritiesInventoryId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.SI));
                                //证券库存日期
                                securitiesInventory1.setDateTime(dateTime);
                                //证券信息表ID
                                securitiesInventory1.setSecuritiesId(securitiesInventoryData.getSecuritiesId());
                                //基金表ID
                                securitiesInventory1.setFundId(fundId);
                                //是否导入其他系统数据
                                securitiesInventory1.setSecurityPeriodFlag(0);
                                //证券的数量
                                securitiesInventory1.setSecuritiesNum(securitiesInventoryData.getTodayNum());
                                //单位成本
                                securitiesInventory1.setPrice(securitiesInventoryData.getUnitPrice());
                                //总金额
                                securitiesInventory1.setTotal(securitiesInventoryData.getTodayTotal());
                                //备注
                                securitiesInventory1.setSecuritiesInventoryDesc("证券库存统计");
                                System.out.println("证券库存实体类" + securitiesInventory1);
                                //调用增加方法
                                securitiesInventoryMapper.insertSecuritiesInventory(securitiesInventory1);
                            }
                        }
                        break;
                    case "3":
                        //TA库存统计
                        List<TaInventoryData> taInventoryDataList=inventoryStatisticsMapper.selectTaInventory(dateTime,fundId);
                        taInventory=new InventoryStatistics(3,"TA库存",fundId,"admin",dateTime,taInventoryDataList.size(),"已统计");
                        if(taInventoryDataList.size()!=0 && taInventoryDataList.get(0)!=null) {
                            for (TaInventoryData taInventoryData : taInventoryDataList) {
                                System.out.println("ta数据" + taInventoryData);
                                //根据日期删除原TA库存信息
                                taInventoryMapper.deleteTaInventoryDate(dateTime, fundId);
                                //新建TA库存
                                TaInventory taInventory1 = new TaInventory();
                                //TA库存ID
                                taInventory1.setTaInventoryId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.TI));
                                //基金ID
                                taInventory1.setFundId(fundId);
                                //Ta数量
                                taInventory1.setTaNum(taInventoryData.getTaNum());
                                //现金余额
                                taInventory1.setTaTotal(taInventoryData.getTaTotal());
                                //统计日期
                                taInventory1.setDateTime(dateTime);
                                //是否从其他系统导入的期初数据  0：不是  1：是
                                taInventory1.setSecurityPeriodFlag(0);
                                //备注
                                taInventory1.setTaInventoryDesc("统计Ta库存");
                                System.out.println("ta库存实体类" + taInventory1);
                                //新增
                                taInventoryMapper.insertTaInventory(taInventory1);
                            }
                        }
                        break;
                    case "4":
                        //证券应收应付库存
                        List<SecuritiesClosedPayInventoryData> securitiesClosedPayInventoryDataList=inventoryStatisticsMapper.selectSecuritiesClosedPayInventory(dateTime,fundId);
                        securitiesClosedPayInventory=new InventoryStatistics(4,"证券应收应付库存",fundId,"admin",dateTime,securitiesClosedPayInventoryDataList.size(),"已统计");
                        if(securitiesClosedPayInventoryDataList.size()!=0 && securitiesClosedPayInventoryDataList.get(0)!=null) {
                            for (SecuritiesClosedPayInventoryData securitiesClosedPayInventoryData : securitiesClosedPayInventoryDataList) {
                                //根据日期删除
                                securitiesClosedPayInventoryMapper.deleteSecuritiesClosedPayInventoryDate(securitiesClosedPayInventoryData.getSecuritiesId(),dateTime, fundId);
                                //定义新证券应收应付库存对象
                                SecuritiesClosedPayInventory securitiesClosedPayInventory1 = new SecuritiesClosedPayInventory();
                                //证券应收应付ID
                                securitiesClosedPayInventory1.setSecuritiesClosedPayInventoryId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.SCPI));
                                //业务时间
                                securitiesClosedPayInventory1.setDateTime(dateTime);
                                //基金Id
                                securitiesClosedPayInventory1.setFundId(fundId);
                                //证券ID
                                securitiesClosedPayInventory1.setSecuritiesId(securitiesClosedPayInventoryData.getSecuritiesId());
                                //证券应收应付类型 1=估值款 2=证券清算款 3=债券利息
                                securitiesClosedPayInventory1.setSecuritiesType(3);
                                //业务状态 1流入，-1流出
                                securitiesClosedPayInventory1.setFlag(securitiesClosedPayInventoryData.getFlag());
                                //总金额
                                securitiesClosedPayInventory1.setTotalPrice(securitiesClosedPayInventoryData.getTotal());
                                //期初标志 是否从其他系统导入得期初数据 0：不是 1：是
                                securitiesClosedPayInventory1.setSecurityPeriodFlag(0);
                                //备注
                                securitiesClosedPayInventory1.setSecuritiesClosedPayDesc("证券应收应付库存统计");
                                //增加
                                securitiesClosedPayInventoryMapper.insertSecuritiesClosedPayInventory(securitiesClosedPayInventory1);
                            }
                        }
                        break;
                    case "5":
                        //现金应收应付库存
                        List<CashClosedPayInventoryData> cashClosedPayInventoryDataList=inventoryStatisticsMapper.selectCashClosedPayInventory(dateTime,fundId);
                        cashClosedPayInventory=new InventoryStatistics(5,"现金应收应付库存",fundId,"admin",dateTime,cashClosedPayInventoryDataList.size(),"已统计");
                        if(cashClosedPayInventoryDataList.size()!=0 && cashClosedPayInventoryDataList.get(0)!=null) {
                            for (CashClosedPayInventoryData cashClosedPayInventoryData : cashClosedPayInventoryDataList) {
                                //根据日期删除
                                cashClosedPayInventoryMapper.deleteCashClosedPayInventoryDate(cashClosedPayInventoryData.getAccountId(),dateTime, fundId);
                                //定义新现金应收应付库存对象
                                CashClosedPayInventory cashClosedPayInventory1 = new CashClosedPayInventory();
                                //现金应收应付库存Id
                                cashClosedPayInventory1.setCashClosedPayInventoryId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCPI));
                                //业务日期
                                cashClosedPayInventory1.setBusinessDate(dateTime);
                                //现金账户ID
                                cashClosedPayInventory1.setAccountId(cashClosedPayInventoryData.getAccountId());
                                //基金ID
                                cashClosedPayInventory1.setFundId(fundId);
                                //业务类型  1.管理费  2.托管费  3.存款利息  4.申购赎回费
                                cashClosedPayInventory1.setBusinessType(cashClosedPayInventoryData.getBusinessType());
                                //业务状态   1.流入  -1.流出
                                cashClosedPayInventory1.setBusinessStatus(cashClosedPayInventoryData.getFlag());
                                //期初标志  1.是   0.否
                                cashClosedPayInventory1.setInitialSigns(0);
                                //总金额
                                cashClosedPayInventory1.setTotalMoney(cashClosedPayInventoryData.getCasheTotal());
                                //增加
                                cashClosedPayInventoryMapper.insertCashClosedPayInventory(cashClosedPayInventory1);
                            }
                        }
                        break;
                }
            }
        }
        //库存信息添加到list
        list.add(cashInventory);
        list.add(securitiesInventory);
        list.add(taInventory);
        list.add(securitiesClosedPayInventory);
        list.add(cashClosedPayInventory);
        return list;
    }

}
