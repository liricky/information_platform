package com.example.demo.dao;

import com.example.demo.Model.entity.Private_Charts;
import com.example.demo.Model.entity.Private_ChartsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Private_ChartsMapper {
    int countByExample(Private_ChartsExample example);

    int deleteByExample(Private_ChartsExample example);

    int insert(Private_Charts record);

    int insertSelective(Private_Charts record);

    List<Private_Charts> selectByExample(Private_ChartsExample example);

    int updateByExampleSelective(@Param("record") Private_Charts record, @Param("example") Private_ChartsExample example);

    int updateByExample(@Param("record") Private_Charts record, @Param("example") Private_ChartsExample example);
}