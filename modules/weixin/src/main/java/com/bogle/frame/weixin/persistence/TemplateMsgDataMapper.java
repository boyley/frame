package com.bogle.frame.weixin.persistence;

import com.bogle.frame.weixin.domain.TemplateMsgData;

import java.util.List;

public interface TemplateMsgDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TemplateMsgData record);

    int insertSelective(TemplateMsgData record);

    TemplateMsgData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TemplateMsgData record);

    int updateByPrimaryKey(TemplateMsgData record);

    void insertBatch(List<TemplateMsgData> templateMsgDatas);
}