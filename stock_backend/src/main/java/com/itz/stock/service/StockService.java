package com.itz.stock.service;

import com.itz.stock.pojo.StockBusiness;

import java.util.List;

public interface StockService {
    List<StockBusiness> findAll();
}
