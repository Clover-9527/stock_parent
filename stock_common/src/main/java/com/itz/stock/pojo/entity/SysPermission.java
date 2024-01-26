package com.itz.stock.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@TableName(value ="sys_permission")
@Data
public class SysPermission implements Serializable {
    private Long id;

    private String code;

    private String title;

    private String icon;

    private String perms;

    private String url;

    private String method;

    private String name;

    private Long pid;

    private Integer orderNum;

    private Integer type;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private static final long serialVersionUID = 1L;
}