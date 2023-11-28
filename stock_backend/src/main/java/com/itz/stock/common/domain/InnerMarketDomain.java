package com.itz.stock.common.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 *  封装国内大盘数据的实体类
 */
@Data
public class InnerMarketDomain {
    /*
      jdbc:bigint--->java:long
     */
    private Long tradeAmt;
    /*
        jdbc:decimal --->java:BigDecimal
     */
    private BigDecimal preClosePrice;
    private String code;
    private String name;
    private String curDate;
    private BigDecimal openPrice;
    private Long tradeVol;
    private BigDecimal upDown;
    private BigDecimal tradePrice;
}