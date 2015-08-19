package com.bogle.frame.sms.persistence;

import com.bogle.frame.sms.domain.Sign;

public interface SignMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sign record);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);

    Sign selectSelective(Sign sign);
}