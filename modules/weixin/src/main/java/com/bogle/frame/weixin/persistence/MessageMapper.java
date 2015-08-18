package com.bogle.frame.weixin.persistence;

import com.bogle.frame.weixin.domain.Message;

public interface MessageMapper {

    int insertSelective(Message record);

    int updateByPrimaryKey(Message record);
}