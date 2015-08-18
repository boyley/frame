package com.bogle.frame.weixin.listener;

import com.bogle.frame.weixin.defines.TicketType;
import com.bogle.frame.weixin.defines.TokenType;
import com.bogle.frame.weixin.domain.TemplateMsgData;
import com.bogle.frame.weixin.domain.Ticket;
import com.bogle.frame.weixin.domain.Token;
import com.bogle.frame.weixin.event.WeixinEvent;
import com.bogle.frame.weixin.message.Template;
import com.bogle.frame.weixin.message.template.TemplateMsg;
import com.bogle.frame.weixin.message.ticket.ReqTicket;
import com.bogle.frame.weixin.persistence.TemplateMsgDataMapper;
import com.bogle.frame.weixin.persistence.TemplateMsgMapper;
import com.bogle.frame.weixin.persistence.TicketMapper;
import com.bogle.frame.weixin.persistence.TokenMapper;
import com.bogle.frame.weixin.service.IWexinApiUrl;
import com.bogle.frame.weixin.service.IWxApi;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2015/8/18.
 */
@Component
public class WeixinListener implements ApplicationListener<WeixinEvent>, IWexinApiUrl {

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TemplateMsgMapper templateMsgMapper;

    @Autowired
    private TemplateMsgDataMapper templateMsgDataMapper;

    @Override
    public void onApplicationEvent(WeixinEvent event) {
        Object returnValue = event.getReturnValue();
        Object[] args = event.getArgs();
        String url = args[0].toString();
        String tokenId = null;
        int tokenIndex = url.lastIndexOf(IWxApi.TOKEN_ID) + IWxApi.TOKEN_ID.length();
        if (tokenIndex > 0) {
            tokenId = url.substring(tokenIndex);
        }
        if (returnValue instanceof Token) {
            url = url.substring(0, url.indexOf("?"));
            Token token = (Token) returnValue;
            token.setId(randomUUID());
            token.setCreateTime(System.currentTimeMillis());
            if (BASE_ACCESS_TOKEN_API_URL.startsWith(url)) { //基础access_token
                token.setType(TokenType.BASE_ACCESS_TOKEN);
            } else if (OAUTH2_ACCESS_TOKEN_API_URL.startsWith(url)) {//网页授权access_token
                token.setType(TokenType.OAUTH2_ACCESS_TOKEN);
            }
            tokenMapper.insertSelective((Token) returnValue);
        } else if (returnValue instanceof Ticket) {
            String subUrl = url.substring(0, url.indexOf("?"));
            Ticket ticket = (Ticket) returnValue;
            ticket.setCreateTime(System.currentTimeMillis());
            ticket.setId(randomUUID());
            ticket.setTokenId(tokenId);
            if (url.contains("type=jsapi")) {
                //调用微信JS接口的临时票据
                ticket.setType(TicketType.JSAPI_TICKET);
            } else if (url.contains("type=wx_card")) {
                //调微信卡券接口中使用的签名凭证api_ticket
                ticket.setType(TicketType.JSAPI_CARD_TICKET);
            } else {
                //投放卡券二维码ticket
                ReqTicket reqTicket = (ReqTicket) args[1];
                ticket.setActionName(reqTicket.getActionName());
                if (CARD_TICKET_URL_API_URL.startsWith(subUrl)) {
                    ticket.setCardId(reqTicket.getCardId());
                    ticket.setOpenid(reqTicket.getOpenid());
                    ticket.setUniqueCode(reqTicket.getUniqueCode());
                    ticket.setOuterId(reqTicket.getOuterId());
                    ticket.setType(TicketType.QR_CARD_TICKET);
                } else if(QRCODE_TICKET_URL_API_URL.startsWith(subUrl)) {
                    ticket.setType(TicketType.QR_TICKET);
                    Serializable scene = reqTicket.generateScene();
                    if(scene instanceof String) {
                        ticket.setSceneStr(scene.toString());
                    } else {
                        ticket.setSceneId(Integer.parseInt(scene.toString()));
                    }
                }
            }
            ticketMapper.insertSelective(ticket);
        } else if(returnValue instanceof Template) {
            TemplateMsg templateMsg = (TemplateMsg) args[1];
            com.bogle.frame.weixin.domain.TemplateMsg tplMsg = new com.bogle.frame.weixin.domain.TemplateMsg();
            BeanUtils.copyProperties(templateMsg,tplMsg);
            tplMsg.setCreateTime(System.currentTimeMillis());
            tplMsg.setTokenId(tokenId);
            templateMsgMapper.insertSelective(tplMsg);
            Map<String,com.bogle.frame.weixin.message.template.TemplateMsgData> data = templateMsg.getData();
            List<TemplateMsgData> templateMsgDatas = new ArrayList<>();
            for(Map.Entry<String,com.bogle.frame.weixin.message.template.TemplateMsgData> entry : data.entrySet()) {
                String key = entry.getKey();
                com.bogle.frame.weixin.message.template.TemplateMsgData value = entry.getValue();
                TemplateMsgData templateMsgData = new TemplateMsgData();
                BeanUtils.copyProperties(value,templateMsgData);
                templateMsgData.setKey(key);
                templateMsgData.setTemplateMsgId(tplMsg.getId());
                templateMsgDatas.add(templateMsgData);
            }
            this.templateMsgDataMapper.insertBatch(templateMsgDatas);
        }
    }


    private String randomUUID() {
        return UUID.randomUUID().toString();
    }
}
