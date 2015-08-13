package com.bogle.frame.admin.service;

import com.bogle.frame.admin.domain.Menu;
import com.bogle.frame.admin.domain.Resc;

import java.util.List;

/**
 * Created by Administrator on 2015/7/28.
 */
public interface MenuService {

    /**
     * 根据资源查询菜单
     * @param rescs
     * @return
     */
    List<Menu> selectByRescs(List<Resc> rescs);
}
