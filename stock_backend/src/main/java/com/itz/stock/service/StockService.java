package com.itz.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itz.stock.common.domain.InnerMarketDomain;
import com.itz.stock.common.domain.StockUpdownDomain;
import com.itz.stock.pojo.StockBlockRtInfo;
import com.itz.stock.pojo.StockBusiness;
import com.itz.stock.vo.PageResult;
import com.itz.stock.vo.R;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface StockService extends IService<StockBusiness> {

    /**
     *  查询全部数据
     */
    List<StockBusiness> findAll();

    /**
     *  获取最新的A股大盘信息
     */
    R<List<InnerMarketDomain>> innerIndexAll();

    /**
     *  获取最新的A股板块最新数据，以交易总金额降序查询，取前10条数据
     */
    R<List<StockBlockRtInfo>> sectorAllLimit();

    /**
     *  降序查询最新的个股涨幅排数据，取前10条数据
     */
    R<List<StockUpdownDomain>> getLastUpDownStock();

    /**
     * 分页查询股票数据
     */
    R<PageResult<StockUpdownDomain>> getStocksByPage(Integer page, Integer pageSize);

    /**
     * 统计T日（最近一次股票交易日）涨停和跌停分时统计
     */
    R<Map> getStockUpDownCount();

    /**
     * 涨幅信息导出Excel
     */
    void exportStockInfo(HttpServletResponse response, int page, int pageSize) throws IOException;

    /**
     * 统计国内A股大盘 T日和 T-1日成交量对比
     *   如果当前时间不在有效的股票交易日下，则将距离最近的一个时间点作为T日时间查询
     */
    R<Map> getStockTradeVol();
}
