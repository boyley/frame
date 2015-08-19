package com.bogle.frame.admin.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

/**
 * 用户账号信息
 * Created by Administrator on 2015/6/11.
 */
public class User implements UserDetails {

    public static final int MIN_LEN_USERNAME = 2;//密码最短长度
    public static final int MAX_LEN_USERNAME = 12;//密码最大长度

    public static final int MIN_LEN_PASSWORD = 6;//密码最短长度
    public static final int MAX_LEN_PASSWORD = 12;//密码最大长度

    private Long id;

    private List<Role> authorities;//用户权限信息

    @NotNull(message = "用户名不能为空")
    @Size(min = MIN_LEN_USERNAME, max = MAX_LEN_USERNAME, message = "用户名长度必须在{min}和{max}之间")
    private String username;//用户名

    @NotNull(message = "密码不能为空")
    @Size(min = MIN_LEN_PASSWORD, max = MAX_LEN_PASSWORD, message = "密码长度必须在{min}和{max}之间")
    private String password;//密码

    private Boolean accountNonExpired = false;//账号是否未过期

    private Boolean accountNonLocked = false;//账号是否未被锁定

    private Boolean credentialsNonExpire = false;//凭证是否为过期

    private Boolean enabled = false;//账号是否可用

    private Long registerTime;

    private List<Menu> menus;

    /**
     * 获取改用户的权限信息
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * 用户密码
     *
     * @return
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * 用户名
     *
     * @return
     */

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 账号是否未过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    /**
     * 是否已经被锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    /**
     * 凭证是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpire;
    }

    /**
     * 是否可用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpire() {
        return credentialsNonExpire;
    }

    public void setCredentialsNonExpire(boolean credentialsNonExpire) {
        this.credentialsNonExpire = credentialsNonExpire;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
