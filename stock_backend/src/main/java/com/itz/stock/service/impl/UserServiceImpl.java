package com.itz.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itz.stock.dto.LoginDto;
import com.itz.stock.pojo.SysUser;
import com.itz.stock.service.SysUserService;
import com.itz.stock.service.UserService;
import com.itz.stock.utils.IdWorker;
import com.itz.stock.vo.LoginVo;
import com.itz.stock.vo.R;
import com.itz.stock.vo.ResponseCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private IdWorker idWorker;


    @Override
    public R<LoginVo> login(LoginDto vo) {
        //获取redis中rkey对应的code验证码
        String rCode = (String) redisTemplate.opsForValue().get(vo.getRkey());
        //校验
        if (StringUtils.isBlank(rCode) || !rCode.equals(vo.getCode())) {
            return R.error(ResponseCode.CAPTCHA_ERROR.getMessage());
        }
        //redis清除key
        redisTemplate.delete(vo.getRkey());
        //根据用户名用户是否存在
        LambdaQueryWrapper<SysUser> qw = new LambdaQueryWrapper<>();
        qw.eq(SysUser::getUsername,vo.getUsername());
        SysUser userInfo = sysUserService.getOne(qw);
        if (userInfo==null) {
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }
        //判断密码是否匹配
        if (!passwordEncoder.matches(vo.getPassword(),userInfo.getPassword())) {
            return R.error(ResponseCode.SYSTEM_PASSWORD_ERROR.getMessage());
        }
        //属性赋值
        LoginVo respVo = new LoginVo();
        BeanUtils.copyProperties(userInfo,respVo);

        return R.ok(respVo);
    }

    @Override
    public R<Map> generateCaptcha() {
        //1.生成4位数字验证码
        String checkCode = RandomStringUtils.randomNumeric(4);
        //2.获取全局唯一id
        String rkey=String.valueOf(idWorker.nextId());
        //验证码存入redis中，并设置有效期1分钟
        redisTemplate.opsForValue().set(rkey,checkCode,60, TimeUnit.SECONDS);
        //3.组装数据
        HashMap<String, String> map = new HashMap<>();
        map.put("rkey",rkey);
        map.put("code",checkCode);
        return R.ok(map);
    }

}
