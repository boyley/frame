package com.bogle.frame.sms.domain;

public class Log {
    private Long id;

    private String softwareSerialNo;

    private String target;

    private String invoke;

    private String argus;

    private String code;

    private String errSource;

    private String errMsg;

    private String thing;

    private Long createTime;

    public Log() {
    }

    public Log(String softwareSerialNo, String target, String invoke, String argus, String code, String errSource, String errMsg, String thing) {
        this.softwareSerialNo = softwareSerialNo;
        this.target = target;
        this.invoke = invoke;
        this.argus = argus;
        this.code = code;
        this.errSource = errSource;
        this.errMsg = errMsg;
        this.thing = thing;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getInvoke() {
        return invoke;
    }

    public void setInvoke(String invoke) {
        this.invoke = invoke == null ? null : invoke.trim();
    }

    public String getArgus() {
        return argus;
    }

    public void setArgus(String argus) {
        this.argus = argus == null ? null : argus.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getErrSource() {
        return errSource;
    }

    public void setErrSource(String errSource) {
        this.errSource = errSource == null ? null : errSource.trim();
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg == null ? null : errMsg.trim();
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing == null ? null : thing.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}