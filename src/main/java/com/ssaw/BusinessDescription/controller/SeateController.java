package com.ssaw.BusinessDescription.controller;

import com.ssaw.BusinessDescription.entity.Seate;
import com.ssaw.BusinessDescription.service.SeateService;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**

 * @data 2020/9/5 am
 *席位信息表控制层
 */

@RestController
public class SeateController {
    @Autowired
    private SeateService seateService;

    /**
     * 增加
     * @param seate
     * @return
     */
    @OperLog(message = "增加交易席位",operation = OperationType.ADD)
    @RequestMapping("/seateInsert")
    public int seateInsert(Seate seate){
        System.out.println("--------------------------");
        System.out.println("增加成功");
        return seateService.seateInsert(seate);
    }


    /**
     * 删除
     * @param seateId
     * @return
     */
    @OperLog(message = "删除交易席位",operation = OperationType.DELETE)
    @RequestMapping("/seateDelete")
    public int delete(String seateId){
        System.out.println(seateId);
        return seateService.seateDelete(seateId);
    }


    /**
     * 修改
     * @param seate
     * @return
     */
    @OperLog(message = "修改交易席位",operation = OperationType.UPDATE)
    @RequestMapping("/seateUpdate")
    public int seateUpdate(Seate seate){
        System.out.println("BrokersId"+seate.getBrokersId());
        return seateService.seateUpdate(seate);
    }


    /**
     * 查询
     * @return
     */
    @OperLog(message = "查询交易席位",operation = OperationType.QUERY)
    @RequestMapping("/seateSelect")
    public HashMap seateSelect(String page, String limit, String seateName,String brokersId,String modules) {
        System.out.println("进来了");
        System.out.println(page+","+limit+","+seateName);
        System.out.println("modules"+modules);
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        HashMap hashMap = seateService.seateSelect(page1,limit1,seateName,brokersId,modules);
        int count = (int) hashMap.get("p_count");
        List<Seate> seateList = (List<Seate>) hashMap.get("p_cursor");
        HashMap seateMap = new HashMap();
        seateMap.put("count",count);
        seateMap.put("code", 0);
        seateMap.put("msg", "");
        seateMap.put("data", seateList);
        return seateMap;
    }
}
