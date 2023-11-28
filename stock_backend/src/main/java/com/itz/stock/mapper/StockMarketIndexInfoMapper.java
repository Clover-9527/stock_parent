package com.itz.stock.mapper;

import com.itz.stock.common.domain.InnerMarketDomain;
import com.itz.stock.pojo.StockMarketIndexInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【stock_market_index_info(股票大盘数据详情表)】的数据库操作Mapper
* @createDate 2023-11-26 17:07:31
* @Entity com.itz.stock.pojo.StockMarketIndexInfo
*/
public interface StockMarketIndexInfoMapper extends BaseMapper<StockMarketIndexInfo> {

    /** 根据大盘id和时间查询大盘信息 */
    List<InnerMarketDomain> selectByIdsAndDate(@Param("marketsIds") List<String> marketsIds,@Param("timePoint") Date timePoint);
}




