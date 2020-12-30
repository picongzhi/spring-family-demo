package com.pcz.spring.family.mybatis.pagehelper.mapper;

import com.github.pagehelper.PageInfo;
import com.pcz.spring.family.mybatis.pagehelper.ApplicationTest;
import com.pcz.spring.family.mybatis.pagehelper.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class CoffeeMapperTest extends ApplicationTest {
    @Autowired
    private CoffeeMapper coffeeMapper;

    @Test
    public void findAllWithRowBoundsTest() {
        List<Coffee> coffees = coffeeMapper.findAllWithRowBounds(new RowBounds(1, 3));
        log.info("size: {}", coffees.size());
        log.info("coffees: {}", coffees);

        coffees = coffeeMapper.findAllWithRowBounds(new RowBounds(2, 3));
        log.info("size: {}", coffees.size());
        log.info("coffees: {}", coffees);

        PageInfo pageInfo = new PageInfo<>(coffees);
        log.info("pageNum: {}, pageSize: {}, pages: {}, total: {}",
                pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
        log.info("items: {}", pageInfo.getList());
    }

    @Test
    public void findAllWithParamTest() {
        List<Coffee> coffees = coffeeMapper.findAllWithParam(2, 3);
        PageInfo<Coffee> pageInfo = new PageInfo<>(coffees);
        log.info("pageInfo: {}", pageInfo);
    }
}
