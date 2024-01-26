package com.itz.stock.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@TableName(value ="sys_user")
@Data
public class SysUser implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String realName;

    private String nickName;

    private String email;

    private Integer status;

    private Integer sex;

    private Integer deleted;

    private Long createId;

    private Long updateId;

    private Integer createWhere;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}