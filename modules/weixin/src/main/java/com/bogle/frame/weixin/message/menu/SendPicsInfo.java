package com.bogle.frame.weixin.message.menu;

import com.bogle.frame.weixin.message.Item;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * 发送的图片信息
 * Created by Administrator on 2015/7/24.
 */
public class SendPicsInfo {

    @XStreamAlias("Count")
    private Integer Count;//发送的图片数量

    @XStreamAlias("PicList")
    private List<Item> picList;

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public List<Item> getPicList() {
        return picList;
    }

    public void setPicList(List<Item> picList) {
        this.picList = picList;
    }
}
