package com.bogle.frame.weixin.message.menu;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 扫描信息
 * Created by Administrator on 2015/7/24.
 */
public class ScanCodeInfo {

    @XStreamAlias("ScanType")
    private String scanType;//扫描类型，一般是qrcode

    @XStreamAlias("ScanResult")
    private String scanResult;//扫描类型，一般是qrcode

    public String getScanType() {
        return scanType;
    }

    public void setScanType(String scanType) {
        this.scanType = scanType;
    }

    public String getScanResult() {
        return scanResult;
    }

    public void setScanResult(String scanResult) {
        this.scanResult = scanResult;
    }
}
