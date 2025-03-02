package com.javaclimb.mapper;

import com.javaclimb.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 查询总记录数
     * @return
     */
/*    @Select("select count(*) from emp")
    public Long count();*/

/*    *//**
     * 进行分页查询，获取数据的方法
     * @param start
     * @param pageSize
     * @return
     */
/*
    @Select("select * from emp limit #{start},#{pageSize}")
    public List<Emp> page(@Param("start") Integer start, @Param("pageSize")Integer pageSize);
*/

//    @Select("select * from emp")

    /**
     * 分页查询结果
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    public List<Emp> list(@Param("name") String name,
                          @Param("gender") Short gender,
                          @Param("begin") LocalDate begin,
                          @Param("end") LocalDate end);

    /**
     * 根据id来删除员工
     * @param ids
     */
    void delete(@Param("ids")List<Integer> ids);

    void insert(Emp emp);

    /**
     * 根据id来查询员工
     * @param id
     * @return
     */
    Emp get(@Param("id")Integer id);

    void update(Emp emp);

    /**
     * 登录请求
     * @param emp
     * @return
     */
    Emp getByusernameandpassword(Emp emp);
    @Delete("delete from emp where id = #{id}")
    void deleteByid(Integer id);
}
