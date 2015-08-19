package com.bogle.frame.sms.domain;

import java.util.Date;

public class ChargeLog {
    private Long id;

    private String cardno;

    private String cardPass;

    private Long createTime;

    private Long logId;

    public ChargeLog() {
    }

    public ChargeLog(String cardno, String cardPass, Long logId) {
        this.cardno = cardno;
        this.cardPass = cardPass;
        this.logId = logId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public String getCardPass() {
        return cardPass;
    }

    public void setCardPass(String cardPass) {
        this.cardPass = cardPass == null ? null : cardPass.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }
}