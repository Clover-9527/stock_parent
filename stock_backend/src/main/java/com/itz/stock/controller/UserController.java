package com.itz.stock.controller;

import com.itz.stock.pojo.entity.SysUser;
import com.itz.stock.service.UserService;
import com.itz.stock.vo.req.LoginReqVo;
import com.itz.stock.vo.resp.LoginRespVo;
import com.itz.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/user/{userName}")
    public SysUser getUserByUserName(@PathVariable("userName") String name) {
        return userService.getUserByUserName(name);
    }

    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody LoginReqVo vo){
        R<LoginRespVo> r= this.userService.login(vo);
        return r;
    }

}
