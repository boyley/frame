package com.bogle.frame.admin.domain;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Long id;

    private String name;

    private Long parentId;

    private Menu parentMenu;

    private Long rescId;

    private Resc resc;

    private String icon;

    private List<Menu> subMenus;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getRescId() {
        return rescId;
    }

    public void setRescId(Long rescId) {
        this.rescId = rescId;
    }

    public Resc getResc() {
        return resc;
    }

    public void setResc(Resc resc) {
        this.resc = resc;
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void addSubMenus(Menu menu) {
        if(this.subMenus == null) {
            this.subMenus = new ArrayList<>();
        }
        this.subMenus.add(menu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        return id.equals(menu.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}