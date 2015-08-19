package com.bogle.frame.sms.domain;

import java.util.Date;

public class Sent {
    private Long id;

    private Long smsTaskId;

    private Date sendTime;

    private Long smsLogId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSmsTaskId() {
        return smsTaskId;
    }

    public void setSmsTaskId(Long smsTaskId) {
        this.smsTaskId = smsTaskId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Long getSmsLogId() {
        return smsLogId;
    }

    public void setSmsLogId(Long smsLogId) {
        this.smsLogId = smsLogId;
    }
}