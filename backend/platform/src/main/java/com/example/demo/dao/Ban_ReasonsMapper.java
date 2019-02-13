package com.example.demo.dao;

import com.example.demo.Model.entity.Ban_Reasons;
import com.example.demo.Model.entity.Ban_ReasonsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Mapper
@Component(value = "Ban_ReasonsMapper")
public interface Ban_ReasonsMapper {
    int countByExample(Ban_ReasonsExample example);

    int deleteByExample(Ban_ReasonsExample example);

    int insert(Ban_Reasons record);

    int insertSelective(Ban_Reasons record);

    List<Ban_Reasons> selectByExample(Ban_ReasonsExample example);

    int updateByExampleSelective(@Param("record") Ban_Reasons record, @Param("example") Ban_ReasonsExample example);

    int updateByExample(@Param("record") Ban_Reasons record, @Param("example") Ban_ReasonsExample example);
}