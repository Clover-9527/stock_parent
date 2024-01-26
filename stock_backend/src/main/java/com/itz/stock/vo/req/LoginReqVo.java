package com.itz.stock.vo.req;

import lombok.Data;

@Data
public class LoginReqVo {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
//    private String code;
//    private String rkey;
}
