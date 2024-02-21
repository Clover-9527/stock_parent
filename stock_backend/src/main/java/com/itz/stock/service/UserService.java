package com.itz.stock.service;

import com.itz.stock.pojo.entity.SysUser;
import com.itz.stock.vo.req.LoginReqVo;
import com.itz.stock.vo.resp.LoginRespVo;
import com.itz.stock.vo.resp.R;

import java.util.Map;

public interface UserService {
    /**
     * 根据用户名查询用户信息
     */
    SysUser getUserByUserName(String userName);

    /**
     * 用户登录
     */
    R<LoginRespVo> login(LoginReqVo reqVo);

    /**
     * 生成验证码
     */
    R<Map> getCaptchaCode();
}
