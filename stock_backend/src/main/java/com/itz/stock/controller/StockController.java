package com.itz.stock.controller;

import com.itz.stock.common.domain.InnerMarketDomain;
import com.itz.stock.common.domain.StockUpdownDomain;
import com.itz.stock.pojo.StockBlockRtInfo;
import com.itz.stock.pojo.StockBusiness;
import com.itz.stock.service.StockService;
import com.itz.stock.vo.PageResult;
import com.itz.stock.vo.R;
import org.springframework.web.bind.annotation .GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/quot")
public class StockController {
    @Resource
    private StockService stockService;

    @GetMapping("/stock/business/all")
    public List<StockBusiness> findAllBusinessInfo(){
        return stockService.findAll();
    }

    /** 获取最新的A股大盘信息 */
    @GetMapping("/index/all")
    public R<List<InnerMarketDomain>> innerIndexAll(){
        return stockService.innerIndexAll();
    }

    /**
     *  获取最新的A股板块最新数据，以交易总金额降序查询，取前10条数据
     */
    @GetMapping("/sector/all")
    public R<List<StockBlockRtInfo>> sectorAll(){
        return stockService.sectorAllLimit();
    }

    /**
     *  降序查询最新的个股涨幅排数据，取前10条数据
     */
    @GetMapping("/stock/increase")
    public R<List<StockUpdownDomain>> getLastUpDownStocks() {
        return stockService.getLastUpDownStock();
    }

    /**
     * 分页查询股票数据
     */
    @GetMapping("stock/all")
    public R<PageResult<StockUpdownDomain>> getStocksByPage(Integer page, Integer pageSize) {
        return stockService.getStocksByPage(page, pageSize);
    }


}

