package com.itz.stock.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@TableName(value ="stock_business")
@Data
public class StockBusiness implements Serializable {
    private String stockCode;

    private String stockName;

    private String blockLabel;

    private String blockName;

    private String business;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}