package com.itz.stock.mapper;

import com.itz.stock.pojo.StockBlockRtInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Administrator
* @description 针对表【stock_block_rt_info(股票板块详情信息表)】的数据库操作Mapper
* @createDate 2023-11-26 17:07:31
* @Entity com.itz.stock.pojo.StockBlockRtInfo
*/
public interface StockBlockRtInfoMapper extends BaseMapper<StockBlockRtInfo> {
    /**
     * 沪深两市板块分时行情数据查询，以交易时间和交易总金额降序查询，取前10条数据
     */
    List<StockBlockRtInfo> sectorAllLimit();
}




