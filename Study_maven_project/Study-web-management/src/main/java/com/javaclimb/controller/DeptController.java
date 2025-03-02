package com.javaclimb.controller;

import com.javaclimb.anno.Log;
import com.javaclimb.pojo.Dept;
import com.javaclimb.pojo.Result;
import com.javaclimb.service.DeptService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList=deptService.list();
        return Result.success(deptList);
    }
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){//@PathVariable根据动态url来实现参数绑定
        log.info("根据id删除部门:｛｝",id);
        deptService.delete(id);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result into(@RequestBody Dept dept){//@RequestBody是封装json格式
        log.info("添加部门数据:{}",dept);
        deptService.add(dept);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result search(@PathVariable Integer id){
        log.info("获取部门id:{}",id);
        Dept deptsearch= deptService.search(id);
        //使用Dept对象来接收是因为search(id) 方法的预期行为是返回一个单一的部门对象（Dept），而不是一个部门列表。
        return Result.success(deptsearch);
    }
    @Log
    @PutMapping
    public Result upDate(@RequestBody Dept dept){
        log.info("修改部门数据｛｝",dept);
        deptService.update(dept);
        return Result.success();
    }



}
