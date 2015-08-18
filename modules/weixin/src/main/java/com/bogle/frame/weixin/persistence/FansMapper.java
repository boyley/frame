package com.bogle.frame.weixin.persistence;

import com.bogle.frame.weixin.domain.Fans;

public interface FansMapper {

    int insertSelective(Fans record);

    int updateByPrimaryKeySelective(Fans record);

}