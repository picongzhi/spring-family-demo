package com.pcz.spring.family.mybatis.pagehelper.mapper;

import com.pcz.spring.family.mybatis.pagehelper.model.Coffee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author picongzhi
 */
public interface CoffeeMapper {
    @Select("SELECT * FROM T_COFFEE ORDER BY id")
    List<Coffee> findAllWithRowBounds(RowBounds rowBounds);

    @Select("SELECT * FROM T_COFFEE ORDER BY id")
    List<Coffee> findAllWithParam(@Param("pageNum") int pageNum,
                                  @Param("pageSize") int pageSize);
}
