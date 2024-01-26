package com.itz.stock.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@TableName(value ="stock_outer_market_index_info")
@Data
public class StockOuterMarketIndexInfo implements Serializable {
    private Long id;

    private String marketCode;

    private String marketName;

    private BigDecimal curPoint;

    private BigDecimal updown;

    private BigDecimal rose;

    private Date curTime;

    private static final long serialVersionUID = 1L;
}