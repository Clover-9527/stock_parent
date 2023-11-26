package com.itz.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itz.stock.pojo.StockBusiness;

import java.util.List;

public interface StockService extends IService<StockBusiness> {
    List<StockBusiness> findAll();
}
