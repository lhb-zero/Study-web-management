package com.javaclimb.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.javaclimb.mapper.EmpMapper;
import com.javaclimb.pojo.Emp;
import com.javaclimb.pojo.PageBean;
import com.javaclimb.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
/*
    传统分页操作：之后用分页插件来使用
    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1.获取总记录数
        Long count = empMapper.count();
        //2.获取分页数据
        Integer start =(page-1)*pageSize;
        //此时已经获取到分页数据，需要用集合封装起来
        List<Emp> empList = empMapper.page(start, pageSize);
        //3.封装数据
        PageBean pageBean = new PageBean(count, empList);
        return pageBean;}*/

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page, pageSize);
        //2.获取分页数据
        //用集合封装分页查询后的结果
        List<Emp> empListlist = empMapper.list(name, gender, begin, end);
        //强转为Page类型
        Page<Emp> p = (Page<Emp>) empListlist;
        //3.封装数据
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.insert(emp);
    }

    @Override
    public Emp get(Integer id) {
        Emp emp = empMapper.get(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {

        return empMapper.getByusernameandpassword(emp);
    }

}


