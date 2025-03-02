package com.javaclimb.mapper;

import com.javaclimb.pojo.Dept;

import org.apache.ibatis.annotations.*;


import java.util.List;


@Mapper
public interface DeptMapper {
    /**
     * 查询部门数据
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 删除部门
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteByid(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select * from dept where id = #{id}")
    Dept search(Integer id);
    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

}
