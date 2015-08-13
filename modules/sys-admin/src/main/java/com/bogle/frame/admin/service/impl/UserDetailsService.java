package com.bogle.frame.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.bogle.frame.admin.domain.Regex;
import com.bogle.frame.admin.domain.Role;
import com.bogle.frame.admin.domain.User;
import com.bogle.frame.admin.domain.UserRole;
import com.bogle.frame.admin.error.UserException;
import com.bogle.frame.admin.persistence.RegexMapper;
import com.bogle.frame.admin.persistence.UserMapper;
import com.bogle.frame.admin.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2015/6/11.
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final static Logger log = LoggerFactory.getLogger(UserDetailsService.class);
    @Resource
    private UserMapper userMapper;

    @Autowired
    private RegexMapper regexMapper;

    @Autowired
    private UserRoleService userRoleService;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (log.isInfoEnabled()) {
            log.info("登陆用户:{}", username);
        }
        final User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }

    //    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
//            rollbackFor = {java.lang.Exception.class, java.lang.RuntimeException.class})
    public int insertSelective(User user) {
        if (log.isInfoEnabled()) {
            log.info("注册用户信息:{}", JSON.toJSONString(user));
        }
        UserDetails userDetails = userMapper.findByUsername(user.getUsername());
        if (userDetails == null) {
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpire(true);
            user.setEnabled(true);
            this.userMapper.insertSelective(user);
            Long userId = user.getId();
            Long roleId = 1L;
            UserRole userRole = new UserRole(userId, roleId);
            return userRoleService.insertSelective(userRole);
        }
        throw new UserException("用户名已经被注册过了");
    }

    /**
     * 获取登录用户的用户信息
     *
     * @return
     */
    public UserDetails getLoginUserDetials() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Assert.notNull(principal, "未登录");
        return (UserDetails) principal;
    }

//    public List<Regex> requestMap() {
//        Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = new HashMap<>();
//        List<Regex> regexes = regexMapper.findAll();
//        return regexes;
//    }

    public Map<RequestMatcher, Collection<ConfigAttribute>> requestMap() {
        Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = new HashMap<>();
        List<Regex> regexes = regexMapper.findAll();
        if (log.isInfoEnabled()) {
            log.info("权限拦截表达式信息:{}", JSON.toJSONString(regexes));
        }
        for (Regex regex : regexes) {
            String url = regex.getRegex();
            RequestMatcher matcher = new AntPathRequestMatcher(url);
            List<String> authoritys = new ArrayList<>();
            for (Role role : regex.getRoles()) {
                String authority = role.getAuthority();
                authoritys.add(authority);
            }
            Collection<ConfigAttribute> configAttributes = SecurityConfig.createList(authoritys.toArray(new String[]{}));
            requestMap.put(matcher, configAttributes);
        }
        return requestMap;
    }
}
