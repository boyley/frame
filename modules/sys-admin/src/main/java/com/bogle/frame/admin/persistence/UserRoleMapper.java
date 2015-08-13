package com.bogle.frame.admin.persistence;

import com.bogle.frame.admin.domain.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}