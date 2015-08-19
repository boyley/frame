package com.bogle.frame.sms.persistence;

import com.bogle.frame.sms.domain.ChargeLog;

public interface ChargeLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChargeLog record);

    int insertSelective(ChargeLog record);

    ChargeLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChargeLog record);

    int updateByPrimaryKey(ChargeLog record);
}