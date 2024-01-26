package com.itz.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itz.stock.pojo.entity.StockBusiness;
import com.itz.stock.service.StockBusinessService;
import com.itz.stock.mapper.StockBusinessMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【stock_business(主营业务表)】的数据库操作Service实现
* @createDate 2024-01-21 14:19:22
*/
@Service
public class StockBusinessServiceImpl extends ServiceImpl<StockBusinessMapper, StockBusiness>
    implements StockBusinessService{

}




