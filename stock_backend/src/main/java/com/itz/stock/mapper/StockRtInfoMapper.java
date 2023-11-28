package com.itz.stock.mapper;

import com.itz.stock.common.domain.StockUpdownDomain;
import com.itz.stock.pojo.StockRtInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【stock_rt_info(个股详情信息表)】的数据库操作Mapper
* @createDate 2023-11-26 17:07:31
* @Entity com.itz.stock.pojo.StockRtInfo
*/
public interface StockRtInfoMapper extends BaseMapper<StockRtInfo> {

    List<StockUpdownDomain> getLastUpDownStock(@Param("timePoint") Date timePintlastDate);

    List<StockUpdownDomain> getStocksByPage();
}




