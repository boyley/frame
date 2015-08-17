package com.bogle.frame.weixin.config;

import com.bogle.frame.config.annotation.PropMap;
import com.bogle.frame.config.domain.Config;
import com.bogle.frame.config.persistence.ConfigMapper;
import com.bogle.frame.config.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class WeixinConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    private static final String TYPE = "WEIXIN";

    @Autowired
    private ConfigMapper configMapper;//配置信息

    @PropMap("WX_APP_ID")
    @NotNull(message = "应用ID不能为空")
    private String appId;//应用ID

    @PropMap("WX_APP_SECRET")
    @NotNull(message = "应用密钥不能为空")
    private String appSecret;//应用密钥

    @PropMap("WX_TOKEN_KEY")
    @NotNull(message = "微信token不能为空")
    private String tokenKey;//微信token

    @PropMap("WX_NAME")
    @NotNull(message = "微信二维码名称不能为空")
    private String name = "微信二维码";//

    @PropMap("WX_SUFFIX")
    @NotNull(message = "微信二维码后缀名不能为空")
    private String suffix = ".jpg";

    public ConfigMapper getConfigMapper() {
        return configMapper;
    }

    public void setConfigMapper(ConfigMapper configMapper) {
        this.configMapper = configMapper;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //防止重复执行。
        if (event.getApplicationContext().getParent() == null) {
            List<Config> configs = this.configMapper.selectByType(TYPE);
            //获取数据库配置的常量值，初始化配置
            Utils.setMapValue(configs, this);
        }
    }
}
