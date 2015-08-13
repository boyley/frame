package com.bogle.frame.admin.persistence;

import com.bogle.frame.admin.domain.Regex;

import java.util.List;

public interface RegexMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Regex record);

    int insertSelective(Regex record);

    Regex selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Regex record);

    int updateByPrimaryKey(Regex record);

    List<Regex> findAll();
}