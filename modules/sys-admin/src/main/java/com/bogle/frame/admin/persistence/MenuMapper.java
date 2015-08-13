package com.bogle.frame.admin.persistence;

import com.bogle.frame.admin.domain.Menu;
import com.bogle.frame.admin.domain.Resc;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectByRescs(List<Resc> rescs);
}