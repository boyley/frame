package com.bogle.frame.weixin.persistence;

import com.bogle.frame.weixin.domain.TemplateMsg;

public interface TemplateMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TemplateMsg record);

    int insertSelective(TemplateMsg record);

    TemplateMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TemplateMsg record);

    int updateByPrimaryKey(TemplateMsg record);
}