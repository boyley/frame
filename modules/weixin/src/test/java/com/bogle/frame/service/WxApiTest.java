package com.bogle.frame.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bogle.frame.Application;
import com.bogle.frame.weixin.defines.ActionName;
import com.bogle.frame.weixin.defines.OauthDefines;
import com.bogle.frame.weixin.defines.TicketType;
import com.bogle.frame.weixin.domain.Qrcode;
import com.bogle.frame.weixin.domain.Ticket;
import com.bogle.frame.weixin.exception.WeixinException;
import com.bogle.frame.weixin.message.Template;
import com.bogle.frame.weixin.message.Token;
import com.bogle.frame.weixin.message.template.TemplateMsg;
import com.bogle.frame.weixin.message.template.TemplateMsgData;
import com.bogle.frame.weixin.message.ticket.ReqTicket;
import com.bogle.frame.weixin.service.impl.WxApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class WxApiTest {

    private final static Logger log = LoggerFactory.getLogger(WxApiTest.class);

    @Autowired
    private WxApi wxApi;

    /**
     * 基础token
     */
    @Test
    public void getToken() throws WeixinException {
        Token token = this.wxApi.getToken();
        println(token);
    }

    /**
     * 網頁授權token
     */
    @Test
    public void getOauth2Token() throws WeixinException {
        Token token = this.wxApi.getToken("abc");
        println(token);
    }

    /**
     * jsapi ticket
     */
    @Test
    public void getJSAPITicketTest() throws WeixinException {
        Ticket ticket = this.wxApi.getTicket(TicketType.JSAPI_TICKET);
        println(ticket);
    }

    /**
     * 投放卡券二维码ticket
     */
    @Test
    public void getCardTicketTest() throws WeixinException {
        Ticket ticket = this.wxApi.getTicket(TicketType.JSAPI_CARD_TICKET);
        println(ticket);
    }

    /**
     * 永久二维码场景值为int类型的二维码ticket
     */
    @Test
    public void getQrTicketByQrLimitSceneTest() throws WeixinException {
        ReqTicket reqTicket = new ReqTicket(ActionName.QR_LIMIT_SCENE,1);
        Ticket ticket = this.wxApi.getTicket(reqTicket);
        println(ticket);
    }


    /**
     * 永久二维码场景值为int类型的二维码byte[]数据
     */
    @Test
    public void getQrTicketByQrLimitSceneBytesTest() throws WeixinException {
        ReqTicket reqTicket = new ReqTicket(ActionName.QR_LIMIT_SCENE,1);
        Qrcode qrcode = this.wxApi.getQrcode(reqTicket);
        pringln(qrcode.getBytes());
    }





    /**
     * 永久二维码场景值为String类型的二维码ticket
     */
    @Test
    public void getQrTicketByQrLimitStrSceneTest() throws WeixinException {
        ReqTicket reqTicket = new ReqTicket(ActionName.QR_LIMIT_STR_SCENE,"1");
        Ticket ticket = this.wxApi.getTicket(reqTicket);
        println(ticket);
    }

    /**
     * 永久二维码场景值为String类型的二维码
     */
    @Test
    public void getQrTicketByQrLimitStrSceneBytesTest() throws WeixinException {
        ReqTicket reqTicket = new ReqTicket(ActionName.QR_LIMIT_STR_SCENE,"1");
        Qrcode qrcode = this.wxApi.getQrcode(reqTicket);
        pringln(qrcode.getBytes());
    }

    /**
     * 临时二维码ticket
     */
    @Test
    public void getQrTicketByQrSceneTest() throws WeixinException {
        ReqTicket reqTicket = new ReqTicket(ActionName.QR_SCENE,1);
        Ticket ticket = this.wxApi.getTicket(reqTicket);
        println(ticket);
    }

    /**
     * 投放卡券二维码ticket
     */
    @Test
    public void getQrTicketByQrCardTest() throws WeixinException {
        ReqTicket reqTicket = new ReqTicket("p5D9Tswj4l2wkuWj3fzbJtChd1ig");
        Ticket ticket = this.wxApi.getTicket(reqTicket);
        println(ticket);
    }

    @Test
    public void oauth2Test() throws WeixinException {
        OauthDefines oauthDefines = this.wxApi.oauth2("abc");
        println(oauthDefines);
    }

    @Test
    public void sendTest() throws WeixinException {
        TemplateMsg templateMsg = new TemplateMsg("o5D9Ts8qEfQy73VwwTOeUbG34Sfw","tYapghmX2RVZHHYKi_O952Nj16DpSVR8r6HoZCbYwrI","www.baidu.com","#FF0000");
        Map<String,TemplateMsgData> data = new HashMap<>();

        data.put("first",new TemplateMsgData("恭喜你购买成功！","#173177"));
        data.put("type",new TemplateMsgData("线上消费赠返积分","#173177"));
        data.put("creditChange",new TemplateMsgData("到账","#173177"));
        data.put("number",new TemplateMsgData("10000账","#173177"));
        data.put("creditName",new TemplateMsgData("账户积分","#173177"));
        data.put("amount",new TemplateMsgData("20000","#173177"));
        data.put("remark",new TemplateMsgData("您可以点击下方菜单-我的账户，随时查询账户余额","#173177"));
        data.put("time",new TemplateMsgData(new Date().toLocaleString(),"#173177"));


        templateMsg.setData(data);
        Template template = this.wxApi.send(templateMsg);
    }

    private void println(Object target) {
        log.info(JSON.toJSONString(target, SerializerFeature.PrettyFormat));
    }

    private void pringln(byte[] bytes) {
        File file = new File("D:\\二维码" + System.currentTimeMillis() + ".jpg");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
