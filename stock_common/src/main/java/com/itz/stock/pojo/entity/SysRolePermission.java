package com.itz.stock.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@TableName(value ="sys_role_permission")
@Data
public class SysRolePermission implements Serializable {
    private Long id;

    private Long roleId;

    private Long permissionId;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}