package com.bogle.frame.weixin.persistence;


import com.bogle.frame.weixin.defines.TokenType;
import com.bogle.frame.weixin.domain.Token;

public interface TokenMapper {

    int insertSelective(Token record);

    Token selectLast(TokenType tokenType);
}