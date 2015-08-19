package com.bogle.frame.sms.domain;

public class RegisterInfo {
    private Long id;

    private String softwareSerialNo;

    private String name;

    private String linkMan;

    private String phoneNum;

    private String mobile;

    private String email;

    private String fax;

    private String address;

    private String postcode;

    private Long registerTime;

    private Long logId;

    public RegisterInfo(String softwareSerialNo, String name, String linkMan, String phoneNum, String mobile, String email, String fax, String address, String postcode, Long logId) {
        this.softwareSerialNo = softwareSerialNo;
        this.name = name;
        this.linkMan = linkMan;
        this.phoneNum = phoneNum;
        this.mobile = mobile;
        this.email = email;
        this.fax = fax;
        this.address = address;
        this.postcode = postcode;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan == null ? null : linkMan.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }
}