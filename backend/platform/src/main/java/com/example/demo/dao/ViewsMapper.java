package com.example.demo.dao;

import com.example.demo.Model.entity.Views;
import com.example.demo.Model.entity.ViewsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "ViewsMapper")
public interface ViewsMapper {
    int countByExample(ViewsExample example);

    int deleteByExample(ViewsExample example);

    int insert(Views record);

    int insertSelective(Views record);

    List<Views> selectByExample(ViewsExample example);

    int updateByExampleSelective(@Param("record") Views record, @Param("example") ViewsExample example);

    int updateByExample(@Param("record") Views record, @Param("example") ViewsExample example);
}