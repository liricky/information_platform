package com.example.demo.dao;

import com.example.demo.Model.entity.Blacklist;
import com.example.demo.Model.entity.BlacklistExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlacklistMapper {
    int countByExample(BlacklistExample example);

    int deleteByExample(BlacklistExample example);

    int insert(Blacklist record);

    int insertSelective(Blacklist record);

    List<Blacklist> selectByExample(BlacklistExample example);

    int updateByExampleSelective(@Param("record") Blacklist record, @Param("example") BlacklistExample example);

    int updateByExample(@Param("record") Blacklist record, @Param("example") BlacklistExample example);
}