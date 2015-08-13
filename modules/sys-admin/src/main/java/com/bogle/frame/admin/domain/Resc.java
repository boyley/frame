package com.bogle.frame.admin.domain;

public class Resc {
    private Long id;

    private String url;

    private String rescType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id  ;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getRescType() {
        return rescType;
    }

    public void setRescType(String resType) {
        this.rescType = resType == null ? null : resType.trim();
    }
}