package com.example.sbsourcelearning.mapper;

import java.util.List;

import com.example.sbsourcelearning.bean.Demo;
import com.example.sbsourcelearning.bean.DemoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoMapper {
    long countByExample(DemoExample example);

    int deleteByExample(DemoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Demo record);

    int insertSelective(Demo record);

    List<Demo> selectByExample(DemoExample example);

    Demo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Demo record, @Param("example") DemoExample example);

    int updateByExample(@Param("record") Demo record, @Param("example") DemoExample example);

    int updateByPrimaryKeySelective(Demo record);

    int updateByPrimaryKey(Demo record);
}