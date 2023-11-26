package com.itz.stock.controller;

import com.itz.stock.dto.LoginDto;
import com.itz.stock.service.UserService;
import com.itz.stock.vo.LoginVo;
import com.itz.stock.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R<LoginVo> login(@RequestBody LoginDto vo){
        return userService.login(vo);
    }


}
