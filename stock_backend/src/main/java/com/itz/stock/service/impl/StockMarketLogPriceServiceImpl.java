package com.itz.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itz.stock.pojo.StockMarketLogPrice;
import com.itz.stock.service.StockMarketLogPriceService;
import com.itz.stock.mapper.StockMarketLogPriceMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【stock_market_log_price(股票大盘 开盘价与前收盘价流水表)】的数据库操作Service实现
* @createDate 2023-11-26 17:07:31
*/
@Service
public class StockMarketLogPriceServiceImpl extends ServiceImpl<StockMarketLogPriceMapper, StockMarketLogPrice>
    implements StockMarketLogPriceService{

}




