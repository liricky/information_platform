package com.example.demo.dao;

import com.example.demo.model.entity.Tag_Users;
import com.example.demo.model.entity.Tag_UsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tag_UsersMapper {
    int countByExample(Tag_UsersExample example);

    int deleteByExample(Tag_UsersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tag_Users record);

    int insertSelective(Tag_Users record);

    List<Tag_Users> selectByExample(Tag_UsersExample example);

    Tag_Users selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tag_Users record, @Param("example") Tag_UsersExample example);

    int updateByExample(@Param("record") Tag_Users record, @Param("example") Tag_UsersExample example);

    int updateByPrimaryKeySelective(Tag_Users record);

    int updateByPrimaryKey(Tag_Users record);
}