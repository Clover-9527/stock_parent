package com.itz.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itz.stock.dto.LoginDto;
import com.itz.stock.mapper.SysUserMapper;
import com.itz.stock.pojo.SysUser;
import com.itz.stock.service.SysUserService;
import com.itz.stock.service.UserService;
import com.itz.stock.vo.LoginVo;
import com.itz.stock.vo.R;
import com.itz.stock.vo.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public R<LoginVo> login(LoginDto vo) {
        //1.判断vo是否存在 或者 用户名是否存在 或者 密码是否存在
        if (vo==null || StringUtils.isBlank(vo.getUsername()) || StringUtils.isBlank(vo.getPassword())) {
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }
        //2.根据用户名用户是否存在
        LambdaQueryWrapper<SysUser> qw = new LambdaQueryWrapper<>();
        qw.eq(SysUser::getUsername,vo.getUsername());
        SysUser userInfo = sysUserService.getOne(qw);
        if (userInfo==null) {
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }
        //3.判断密码是否匹配
        if (!passwordEncoder.matches(vo.getPassword(),userInfo.getPassword())) {
            return R.error(ResponseCode.SYSTEM_PASSWORD_ERROR.getMessage());
        }
        //4.属性赋值
        LoginVo respVo = new LoginVo();
        BeanUtils.copyProperties(userInfo,respVo);

        return R.ok(respVo);
    }

}
