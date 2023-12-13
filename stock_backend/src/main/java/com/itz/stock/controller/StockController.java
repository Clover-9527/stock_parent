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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quot")
public class StockController {
    @Resource
    private StockService stockService;

    /**
     *  查询全部数据
     */
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

    /**
     * 统计T日（最近一次股票交易日）涨停和跌停分时统计
     */
    @GetMapping("/stock/updown/count")
    public R<Map> getStockUpDownCount() {
        return stockService.getStockUpDownCount();
    }

    /**
     * 涨幅信息导出Excel
     */
    @GetMapping("/stock/export")
    public void exportStockInfo(HttpServletResponse response, int page, int pageSize) {
        try {
            stockService.exportStockInfo(response, page, pageSize);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 统计国内A股大盘 T日和 T-1日成交量对比
     *   如果当前时间不在有效的股票交易日下，则将距离最近的一个时间点作为T日时间查询
     */
    @GetMapping("/stock/tradevol")
    public R<Map> stockTradeVol4InnerMarket() {
        return stockService.getStockTradeVol();
    }

}

