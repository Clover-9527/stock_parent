package com.itz.stock.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@TableName(value ="stock_block_rt_info")
@Data
public class StockBlockRtInfo implements Serializable {
    private Long id;

    private String label;

    private String blockName;

    private Integer companyNum;

    private BigDecimal avgPrice;

    private BigDecimal updownRate;

    private Long tradeAmount;

    private BigDecimal tradeVolume;

    private Date curTime;

    private static final long serialVersionUID = 1L;
}