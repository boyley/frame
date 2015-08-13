package com.bogle.frame.admin.persistence;

import com.bogle.frame.admin.domain.Resc;

public interface RescMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resc record);

    int insertSelective(Resc record);

    Resc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resc record);

    int updateByPrimaryKey(Resc record);
}