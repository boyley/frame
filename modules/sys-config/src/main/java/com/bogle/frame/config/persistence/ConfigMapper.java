package com.bogle.frame.config.persistence;


import com.bogle.frame.config.domain.Config;

import java.util.List;

public interface ConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

    List<Config> findAll();

    List<Config> selectByType(String type);

    Config selectByKey(String sceneZhaopin);
}