package com.javaclimb.controller;

import com.javaclimb.pojo.Emp;
import com.javaclimb.pojo.Result;
import com.javaclimb.service.EmpService;
import com.javaclimb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("发送登录请求{}",emp);
        Emp e =empService.login(emp);
        //等登录成功后生成JWT令牌
        if(e!=null){
            Map<String, Object> claims =new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());
            String s = JwtUtils.generateJwt(claims);
            return Result.success(s);
        }
        return Result.error("账号或密码错误");
    }
}
