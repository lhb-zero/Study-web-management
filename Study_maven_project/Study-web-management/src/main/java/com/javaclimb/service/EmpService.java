package com.javaclimb.service;

import com.javaclimb.pojo.Emp;
import com.javaclimb.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分页结果
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 根据id来删除员工
     * @param ids
     */
    void delete(List<Integer> ids);



    /**
     * 添加员工信息
     * @param emp
     * @return
     */
    void save(Emp emp);

    /**
     * 根据员工id来查询信息
     * @return
     */
    Emp get(Integer id);

    /**
     * 修改员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 登录请求
     * @param emp
     */
    Emp login(Emp emp);
}
