package com.itz.stock.service.impl;

import com.itz.stock.mapper.StockBusinessMapper;
import com.itz.stock.pojo.StockBusiness;
import com.itz.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Resource
    private StockBusinessMapper stockBusinessMapper;

    @Override
    public List<StockBusiness> findAll() {

        return null;
    }
}
