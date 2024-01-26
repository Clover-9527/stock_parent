package com.itz.stock.service.impl;

import com.itz.stock.mapper.SysUserMapper;
import com.itz.stock.pojo.entity.SysUser;
import com.itz.stock.service.UserService;
import com.itz.stock.vo.req.LoginReqVo;
import com.itz.stock.vo.resp.LoginRespVo;
import com.itz.stock.vo.resp.R;
import com.itz.stock.vo.resp.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户名查询用户信息
     */
    @Override
    public SysUser getUserByUserName(String userName) {
        return sysUserMapper.getUserByUserName(userName);
    }

    /**
     * 用户登录
     */
    @Override
    public R<LoginRespVo> login(LoginReqVo reqVo) {
        //判断输入参数的合法性
        if (reqVo==null || StringUtils.isBlank(reqVo.getUsername()) || StringUtils.isBlank(reqVo.getPassword())) {
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        //根据用户名查询用户信息
        SysUser dbUser = sysUserMapper.getUserByUserName(reqVo.getUsername());
        //判断用户是否存在
        if (dbUser==null || ! passwordEncoder.matches(reqVo.getPassword(),dbUser.getPassword())) {
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        //构建响应相对
        LoginRespVo respVo = new LoginRespVo();
        BeanUtils.copyProperties(dbUser,respVo);
        return R.ok(respVo);
    }


}
