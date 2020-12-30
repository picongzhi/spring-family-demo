package com.pcz.spring.family.mybatis.mapper;

import com.pcz.spring.family.mybatis.model.Coffee;
import org.apache.ibatis.annotations.*;

/**
 * @author picongzhi
 */
public interface CoffeeMapper {
    @Insert("INSERT INTO T_COFFEE (name, price, create_time, update_time) VALUES (#{name}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true)
    int save(Coffee coffee);

    @Select("SELECT * FROM T_COFFEE WHERE id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
    })
    Coffee findById(@Param("id") Long id);
}
