package com.bogle.frame.admin.persistence;

import com.bogle.frame.admin.domain.Role;

import java.util.List;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    List<Role> findAll();
}