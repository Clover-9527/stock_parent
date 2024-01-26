package com.itz.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class TestPasswordEncoder {
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 测试加密
     */
    @Test
    public void testPasswordEncoder() {
        //明文密码
        String pwd="123456";
        for (int i = 0; i < 10; i++) {
            String encodePwd = passwordEncoder.encode(pwd);
            System.out.println(encodePwd);
        }
    }

    /**
     * @desc 测试匹配
     * 底层原理：
     * 从密文中获取盐值（随件码，参与密文生成的运算）后，利用盐值与明文密码进行加密得到密文，
     * 这个密文与输入的密文等值匹配
     */
    @Test
    public void testDecode() {
        String encodePwd="$2a$10$kpngKp7J3q0vb1xfmzPYFOQzcWkU8YUrqNF6XpjimM7zG4l2ra9pi";
        String pwd="123456";
        boolean isSuccess = passwordEncoder.matches(pwd, encodePwd);
        System.out.println(isSuccess?"匹配成功":"匹配失败");
    }
}
