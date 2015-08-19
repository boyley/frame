package com.bogle.frame.sms.persistence;

import com.bogle.frame.sms.domain.RegisterInfo;

public interface RegisterInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RegisterInfo record);

    int insertSelective(RegisterInfo record);

    RegisterInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RegisterInfo record);

    int updateByPrimaryKey(RegisterInfo record);
}