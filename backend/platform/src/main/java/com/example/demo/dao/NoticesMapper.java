package com.example.demo.dao;

import com.example.demo.Model.entity.Notices;
import com.example.demo.Model.entity.NoticesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticesMapper {
    int countByExample(NoticesExample example);

    int deleteByExample(NoticesExample example);

    int insert(Notices record);

    int insertSelective(Notices record);

    List<Notices> selectByExample(NoticesExample example);

    int updateByExampleSelective(@Param("record") Notices record, @Param("example") NoticesExample example);

    int updateByExample(@Param("record") Notices record, @Param("example") NoticesExample example);
}