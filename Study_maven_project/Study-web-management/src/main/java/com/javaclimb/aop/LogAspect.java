package com.javaclimb.aop;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.mapper.OperateLogMapper;
import com.javaclimb.pojo.OperateLog;
import com.javaclimb.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private HttpServletRequest req;
    //这里不是http请求和响应的对象保存在ioc容器。
    // 拦截器的请求和响应对象是由Servlet容器（例如Tomcat）提供的，它们是原生的Java EE API对象。

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.javaclimb.anno.Log))")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //操作人ID
        String jwt = req.getHeader("token");//获取名为token的内容
        Claims claims = JwtUtils.parseJWT(jwt);//获取到令牌
        Integer id = (Integer) claims.get("id");//在令牌中获取id
        //操作时间
        LocalDateTime now = LocalDateTime.now();
        //操作类名
        String name = proceedingJoinPoint.getTarget().getClass().getName();//获取到类名
        //操作方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        //操作方法参数
        Object[] args = proceedingJoinPoint.getArgs();//参数是以数组保存的
        String argsString = Arrays.toString(args);//用Array方法中的toString来把数组转换为字符串
        //记录当前时间
        long begin = System.currentTimeMillis();
        log.info("开始时间:{}",begin);
        //使用原始方法执行
        Object proceed = proceedingJoinPoint.proceed();
        //方法返回值
        String resultValue = JSONObject.toJSONString(proceed);
        //操作耗时
        long end = System.currentTimeMillis();
        log.info("结束时间:{}",end);
        long time = end-begin;
        log.info(proceedingJoinPoint.getSignature()+"总耗时{}ms",end-begin);

        //记录日志
        OperateLog operateLog = new OperateLog(null,id,now,name,methodName,argsString,resultValue,time);
        operateLogMapper.insert(operateLog);
        log.info("AOP记录对象:{}",operateLog);
        return proceed;
    }
}
