package com.example.demo.dao;

import com.example.demo.Model.entity.Lostlist;
import com.example.demo.Model.entity.LostlistExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "LostlistMapper")
public interface LostlistMapper {
    int countByExample(LostlistExample example);

    int deleteByExample(LostlistExample example);

    int insert(Lostlist record);

    int insertSelective(Lostlist record);

    List<Lostlist> selectByExampleWithBLOBs(LostlistExample example);

    List<Lostlist> selectByExample(LostlistExample example);

    int updateByExampleSelective(@Param("record") Lostlist record, @Param("example") LostlistExample example);

    int updateByExampleWithBLOBs(@Param("record") Lostlist record, @Param("example") LostlistExample example);

    int updateByExample(@Param("record") Lostlist record, @Param("example") LostlistExample example);
}