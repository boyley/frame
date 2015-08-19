package com.bogle.frame.sms.domain;

public class Sent {
    private Long id;

    private Long smsTaskId;

    private Long sendTime;

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

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Long getSmsLogId() {
        return smsLogId;
    }

    public void setSmsLogId(Long smsLogId) {
        this.smsLogId = smsLogId;
    }
}