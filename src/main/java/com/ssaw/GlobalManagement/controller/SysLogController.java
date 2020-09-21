package com.ssaw.GlobalManagement.controller;

import com.ssaw.GlobalManagement.log.entity.SysLog;
import com.ssaw.GlobalManagement.log.service.LogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sysLog")
public class SysLogController {

    @Resource
    LogService logService;

    @RequestMapping("selectSysLog")
    public Map<String, Object> selectSysLog(String limit,String page){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String, Object> map = logService.selectLog(limit, page);
        resultMap.put("code",1);
        if (map.get("logInfos")!=null) resultMap.put("code",0);
        resultMap.put("msg","");
        resultMap.put("count",(int)map.get("count"));
        resultMap.put("data",(List<SysLog>) map.get("logInfos"));
        return resultMap;
    }
}
