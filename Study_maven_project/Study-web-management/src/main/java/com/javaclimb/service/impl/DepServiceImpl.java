/**
 * 实现类接口
 */
package com.javaclimb.service.impl;

import com.javaclimb.mapper.DeptMapper;
import com.javaclimb.mapper.EmpMapper;
import com.javaclimb.pojo.Dept;
import com.javaclimb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepServiceImpl implements DeptService {
    //要调用数据库的方法，要调用mapper接口中的方法，需要利用自动注入ioc容器来实现
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteByid(id);

        empMapper.deleteByid(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept search(Integer id) {
        Dept dept = deptMapper.search(id);
        return dept;
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

}
