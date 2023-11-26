package com.itz.stock.service;

import com.itz.stock.dto.LoginDto;
import com.itz.stock.vo.LoginVo;
import com.itz.stock.vo.R;

public interface UserService {
    R<LoginVo> login(LoginDto vo);
}
