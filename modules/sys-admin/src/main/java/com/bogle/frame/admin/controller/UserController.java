package com.bogle.frame.admin.controller;

import com.bogle.frame.admin.domain.User;
import com.bogle.frame.admin.service.impl.UserDetailsService;
import com.codahale.metrics.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/6/19.
 */
@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;


    @RequestMapping(value = "/account",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<User> getAccount() {
        return new ResponseEntity(new User(), HttpStatus.OK);
    }
}
