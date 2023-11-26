package com.itz.stock.controller;

import com.itz.stock.dto.LoginDto;
import com.itz.stock.service.UserService;
import com.itz.stock.vo.LoginVo;
import com.itz.stock.vo.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private UserService userService;

    /** 登录 */
    @PostMapping("/login")
    public R<LoginVo> login(@RequestBody @Valid LoginDto vo){
        return userService.login(vo);
    }

    /**
     * 生成验证码
     */
    @GetMapping("/captcha")
    public R<Map> generateCaptcha(){
        return userService.generateCaptcha();
    }

}
