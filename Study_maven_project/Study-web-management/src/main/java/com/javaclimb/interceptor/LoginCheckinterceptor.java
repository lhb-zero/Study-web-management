package com.javaclimb.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.pojo.Result;
import com.javaclimb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
@Slf4j
public class LoginCheckinterceptor implements HandlerInterceptor {
    @Override//目标资源方法前运行，返回值为true时放行，否则拦截
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        //1.获取到url地址
        String url = req.getRequestURL().toString();
        log.info("获取到url地址:｛｝",url);
        //2.判断是否为登录请求
        if (url.contains("/login")) {
            log.info("登陆操作：放行｛｝",url);
            return true;
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
            return false;
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
            return false;
        }
        //6.解析成功，令牌也正确，放行
        log.info("jwt令牌正确，放行");
        return true;
    }

    @Override//目标资源方法后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//视图渲染时运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
