package com.itz.stock.common.domain;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class StockUpdownDomain {
    /**
     * 股票编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 前收盘价
     */
   private BigDecimal preClosePrice;

    /**
     * 当前价格
     */
    private BigDecimal tradePrice;

    /**
     * 涨跌
     */
   private BigDecimal increase;

    /**
     * 涨幅
     */
   private BigDecimal upDown;

    /**
     * 振幅
     */
    private BigDecimal amplitude;

    /**
     * 交易量
     */
    private Long tradeAmt;

    /**
     * 交易金额
     */
    private BigDecimal tradeVol;

    /**
     * 日期
     */
    private String curDate;


}
