package com.example.sbsourcelearning.service;

import com.example.sbsourcelearning.bean.Demo;
import com.example.sbsourcelearning.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;

    public Demo getDemoById(Long id) {
        return Optional.ofNullable(demoMapper.selectByPrimaryKey(id))
            .orElse(null);
    }
}
