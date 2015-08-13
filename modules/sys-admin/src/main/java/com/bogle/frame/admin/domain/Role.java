package com.bogle.frame.admin.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class Role implements GrantedAuthority {
    private Long id;

    private String name;

    private String authority;

    private List<Resc> rescs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority == null ? null : authority.trim();
    }

    public List<Resc> getRescs() {
        return rescs;
    }

    public void setRescs(List<Resc> rescs) {
        this.rescs = rescs;
    }
}