package com.itz.stock.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {
    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message="密码不能为空")
    private String password;
    /**
     * 验证码
     */
    @NotBlank(message="验证码不能为空")
    private String code;
    /**
     * 前端发送的sessionId
     */
    @NotBlank(message="rkey不能为空")
    private String rkey;
}
