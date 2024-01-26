package com.itz.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itz.stock.pojo.entity.SysPermission;
import com.itz.stock.service.SysPermissionService;
import com.itz.stock.mapper.SysPermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【sys_permission(权限表（菜单）)】的数据库操作Service实现
* @createDate 2024-01-21 14:19:22
*/
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission>
    implements SysPermissionService{

}




