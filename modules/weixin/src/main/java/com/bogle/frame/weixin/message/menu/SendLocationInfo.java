package com.bogle.frame.weixin.message.menu;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 发送的位置信息
 * Created by Administrator on 2015/7/24.
 */
public class SendLocationInfo {

    @XStreamAlias("Location_X")
    private String locationX;//	地理位置维度
    @XStreamAlias("Location_Y")
    private String locationY;//	地理位置经度
    @XStreamAlias("Scale")
    private String scale;//地图缩放大小
    @XStreamAlias("Label")
    private String label;//	地理位置信息
    @XStreamAlias("Poiname")
    private String poiname;//	朋友圈POI的名字，可能为空

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPoiname() {
        return poiname;
    }

    public void setPoiname(String poiname) {
        this.poiname = poiname;
    }
}
