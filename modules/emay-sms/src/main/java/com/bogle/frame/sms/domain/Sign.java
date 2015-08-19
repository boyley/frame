package com.bogle.frame.sms.domain;

import java.util.Date;

public class Sign {
    private Long id;

    private String softwareSerialNo;

    private String pwd;

    private String specialNo;

    private String key;

    private Date createTime;

    private Long logId;

    private Log log;

    public Sign() {
    }

    public Sign( String softwareSerialNo,String pwd, String key) {
        this.softwareSerialNo = softwareSerialNo;
        this.pwd = pwd;
        this.key = key;
    }

    public Sign(String softwareSerialNo, String pwd, String specialNo, String key, Long logId) {
        this.softwareSerialNo = softwareSerialNo;
        this.pwd = pwd;
        this.specialNo = specialNo;
        this.key = key;
        this.logId = logId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoftwareSerialNo() {
        return softwareSerialNo;
    }

    public void setSoftwareSerialNo(String softwareSerialNo) {
        this.softwareSerialNo = softwareSerialNo == null ? null : softwareSerialNo.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getSpecialNo() {
        return specialNo;
    }

    public void setSpecialNo(String specialNo) {
        this.specialNo = specialNo == null ? null : specialNo.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }
}