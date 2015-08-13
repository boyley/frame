package com.bogle.frame.admin.service.impl;

import com.bogle.frame.admin.domain.Role;
import com.bogle.frame.admin.persistence.RoleMapper;
import com.bogle.frame.admin.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/6/11.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }
}
