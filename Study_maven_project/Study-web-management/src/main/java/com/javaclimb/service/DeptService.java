package com.javaclimb.service;

import com.javaclimb.pojo.Dept;


import java.util.List;

public interface DeptService {
    /**
     * 查询部门
     * @return
     */
    List<Dept> list();

    /**
     * 删除部门
     * @param id
     */
    void delete(Integer id);

    /**
     * 添加部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 根据id查询部门
     * @return
     */
    Dept search(Integer id);

    /**
     * 修改部门数据
     * @param dept
     */
    void update(Dept dept);
}
