package com.bogle.frame.admin.listener;

import com.bogle.frame.admin.domain.Menu;
import com.bogle.frame.admin.domain.User;
import com.bogle.frame.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2015/8/19.
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent>  {

    @Autowired
    private MenuService menuService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        User user = (User)authentication.getPrincipal();
        List<Menu> menus = this.menuService.selectByUser(user);
        user.setMenus(menus);
    }
}
