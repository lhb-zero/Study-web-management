package com.javaclimb.filter;


import com.alibaba.fastjson.JSONObject;
import com.javaclimb.pojo.Result;
import com.javaclimb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        //1.获取到url地址
        String url = req.getRequestURL().toString();
        log.info("获取到url地址:｛｝",url);
        //2.判断是否为登录请求
        if (url.contains("/login")) {
            log.info("登陆操作：放行｛｝",url);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //3.获取请求头中的令牌
        String jwt = req.getHeader("token");
        log.info("token:{}",jwt);
        //4.判断令牌是否存在，不存在返回错误结果
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            res.getWriter().write(jsonString);
        }
        //5.解析JWT令牌，如果没出错则说明正常
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("jwt令牌解析错误，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            res.getWriter().write(jsonString);
        }
        //6.解析成功，令牌也正确，放行
        log.info("jwt令牌正确，放行");
        filterChain.doFilter(servletRequest,servletResponse);

    }

}
