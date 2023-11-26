package com.itz.stock.service;

import com.itz.stock.dto.LoginDto;
import com.itz.stock.vo.LoginVo;
import com.itz.stock.vo.R;

import java.util.Map;

public interface UserService {
    R<LoginVo> login(LoginDto vo);

    R<Map> generateCaptcha();
}
