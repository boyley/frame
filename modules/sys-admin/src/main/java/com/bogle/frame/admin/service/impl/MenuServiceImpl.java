package com.bogle.frame.admin.service.impl;

import com.bogle.frame.admin.domain.Menu;
import com.bogle.frame.admin.domain.Resc;
import com.bogle.frame.admin.domain.Role;
import com.bogle.frame.admin.domain.User;
import com.bogle.frame.admin.persistence.MenuMapper;
import com.bogle.frame.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2015/7/28.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据资源查询菜单，并递归的找到顶级菜单
     *
     * @param rescs
     * @return
     */
    @Override
    public List<Menu> selectByRescs(List<Resc> rescs) {
        Assert.notEmpty(rescs, "权限资源集合不能为空");
        List<Menu> menus = this.menuMapper.selectByRescs(rescs);
        return parse(menus);
    }

    /**
     * 根据查询的用户获取菜单信息
     * @param user
     * @return
     */
    @Override
    public List<Menu> selectByUser(User user) {
        Collection<? extends GrantedAuthority> roleList = user.getAuthorities();
        List<Resc> rescs = new ArrayList<>();
        for (GrantedAuthority authority : roleList) {
            Role role = (Role) authority;
            for (Resc resc : role.getRescs()) {
                rescs.add(resc);
            }
        }
        if(!rescs.isEmpty()) {
            return parse(this.selectByRescs(rescs));
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 菜单封装
     * 1. 获取顶级菜单
     * 2. 递归菜单
     *
     * @param menus
     * @return
     */
    private List<Menu> parse(List<Menu> menus) {
        List<Menu> topMenus = new ArrayList<>();
        for (Menu menu : menus) {
            topMenus.add(parse(menu));
        }
        List<Menu> results = new ArrayList<>();
        for (Menu menu : topMenus) {
            parse(menu, results);
        }
        return results;
    }

    /**
     * 将菜单组织成树形结构递归算法，浪费了我两天的时间研究
     *
     * @param curMenu
     * @param menus
     */
    private void parse(Menu curMenu, List<Menu> menus) {
        if (curMenu.getParentMenu() == null && !menus.contains(curMenu)) {
            menus.add(curMenu);
            return;
        } else if (curMenu.getParentMenu() != null && !menus.contains(curMenu)) {
            Menu parent = menus.get(0).getParentMenu();
            curMenu.setParentMenu(parent);//将父菜单设置成同一个父级menu
            menus.add(curMenu);
            return;
        }
        for (Menu menu : menus) {
            if (menu.getId() == curMenu.getId()) {
                List<Menu> curSubMenus = curMenu.getSubMenus();
                for (Menu curSubMenu : curSubMenus) {
                    parse(curSubMenu, menu.getSubMenus());
                }
            }
        }
    }


    /**
     * 递归获取顶级菜单
     *
     * @param menu
     * @return
     */
    private Menu parse(Menu menu) {
        if (menu.getParentMenu() == null) {
            return menu;
        }
        Menu subMenu = menu.getParentMenu();
        subMenu.addSubMenus(menu);
        return parse(subMenu);
    }
}
