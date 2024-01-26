package com.itz.stock.mapper;

import com.itz.stock.pojo.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser getUserByUserName(@Param("userName") String userName);
}




