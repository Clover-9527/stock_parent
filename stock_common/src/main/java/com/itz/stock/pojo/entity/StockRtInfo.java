package com.itz.stock.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@TableName(value ="stock_rt_info")
@Data
public class StockRtInfo implements Serializable {
    private Long id;

    private String stockCode;

    private String stockName;

    private BigDecimal preClosePrice;

    private BigDecimal openPrice;

    private BigDecimal curPrice;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private Long tradeAmount;

    private BigDecimal tradeVolume;

    private Date curTime;

    private static final long serialVersionUID = 1L;
}