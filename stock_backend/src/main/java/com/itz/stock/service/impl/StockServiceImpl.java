package com.itz.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itz.stock.mapper.StockBusinessMapper;
import com.itz.stock.pojo.StockBusiness;
import com.itz.stock.service.StockService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockServiceImpl extends ServiceImpl<StockBusinessMapper,StockBusiness> implements StockService {

    @Override
    public List<StockBusiness> findAll() {
        List<StockBusiness> list = this.list();
        return list;
    }
}
