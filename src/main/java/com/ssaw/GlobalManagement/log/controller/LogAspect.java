package com.ssaw.GlobalManagement.log.controller;

import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.log.entity.SysLog;
import com.ssaw.GlobalManagement.log.service.LogService;
import com.ssaw.GlobalManagement.util.IntellectualPropertyUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LogAspect {
    @Resource
    LogService logService;

    private static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.ssaw.GlobalManagement.log.OperLog)")
    public void logPoinCut(){}

    @Around("logPoinCut()")
    public Object logAround(ProceedingJoinPoint pjp){
        System.out.println("[Around Notice Start Running]");
        //创建日志对象
        SysLog log = new SysLog();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        Method method = signature.getMethod();

        //获取操作
        OperLog myLog = method.getAnnotation(OperLog.class);
        if (myLog != null) {
            String value = myLog.message();
            log.setLogByRunName(value);//保存获取的操作
        }else {
            log.setLogByRunName("未知请求");
        }

        String className = pjp.getTarget().getClass().getName();

        String methodName = method.getName();

        log.setLogByClass(className+"."+methodName);

        log.setLogByTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        //获取用户名
        //获取用户ip地址
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        LOG.info("URL : " + request.getRequestURL().toString());
        LOG.info("HTTP_METHOD : " + request.getMethod());
        LOG.info("IP : " + request.getRemoteAddr());

        HttpSession session = request.getSession(false);
        if (session==null||session.getAttribute("userName")==null){
            log.setLogByUser("publicUser");
        }else {
            log.setLogByUser((String) session.getAttribute("userName"));
        }

        log.setLogByUrl(request.getRequestURI());
        log.setLogByIp(IntellectualPropertyUtil.getIpAddress(request));

        //开始调用时间
        // 计时并调用目标函数
        long start = System.currentTimeMillis();

        //调用service保存SysLog实体类到数据库
        //sysLogService.save(sysLog);
        Object result = null;
        try {
            result = pjp.proceed();
            System.out.println("    "+result.toString());
            long time = System.currentTimeMillis() - start;
            log.setLogByRunTime(time+"ms");
            System.out.println("[Around Notice Run End]");
            logService.insertLog(log);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
