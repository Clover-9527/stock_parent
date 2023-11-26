package com.itz.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itz.stock.pojo.SysUser;
import com.itz.stock.service.SysUserService;
import com.itz.stock.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-11-26 17:07:31
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




