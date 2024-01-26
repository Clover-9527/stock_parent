package com.itz.stock.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@TableName(value ="sys_user_role")
@Data
public class SysUserRole implements Serializable {
    private Long id;

    private Long userId;

    private Long roleId;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}