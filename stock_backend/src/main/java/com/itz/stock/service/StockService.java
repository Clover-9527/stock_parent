package com.itz.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itz.stock.domain.InnerMarketDomain;
import com.itz.stock.pojo.StockBlockRtInfo;
import com.itz.stock.pojo.StockBusiness;
import com.itz.stock.vo.R;

import java.util.List;

public interface StockService extends IService<StockBusiness> {
    List<StockBusiness> findAll();

    R<List<InnerMarketDomain>> innerIndexAll();

    R<List<StockBlockRtInfo>> sectorAllLimit();
}
