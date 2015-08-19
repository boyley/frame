package com.bogle.frame.sms.persistence;

import com.bogle.frame.sms.domain.Sent;

public interface SentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sent record);

    int insertSelective(Sent record);

    Sent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sent record);

    int updateByPrimaryKey(Sent record);
}