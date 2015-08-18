package com.bogle.frame.weixin.persistence;

import com.bogle.frame.weixin.domain.Qrcode;

public interface QrcodeMapper {

    int insertSelective(Qrcode record);

    int updateByPrimaryKeySelective(Qrcode record);
}