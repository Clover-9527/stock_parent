package com.itz.stock.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@TableName(value ="stock_market_index_info")
@Data
public class StockMarketIndexInfo implements Serializable {
    private Long id;

    private String marketCode;

    private String marketName;

    private BigDecimal preClosePoint;

    private BigDecimal openPoint;

    private BigDecimal curPoint;

    private BigDecimal minPoint;

    private BigDecimal maxPoint;

    private Long tradeAmount;

    private BigDecimal tradeVolume;

    private Date curTime;

    private static final long serialVersionUID = 1L;
}