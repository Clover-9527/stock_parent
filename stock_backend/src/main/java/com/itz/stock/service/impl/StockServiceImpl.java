package com.itz.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itz.stock.config.StockInfoConfig;
import com.itz.stock.domain.InnerMarketDomain;
import com.itz.stock.mapper.StockBlockRtInfoMapper;
import com.itz.stock.mapper.StockBusinessMapper;
import com.itz.stock.mapper.StockMarketIndexInfoMapper;
import com.itz.stock.pojo.StockBlockRtInfo;
import com.itz.stock.pojo.StockBusiness;
import com.itz.stock.service.StockService;
import com.itz.stock.utils.DateTimeUtil;
import com.itz.stock.vo.R;
import com.itz.stock.vo.ResponseCode;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class StockServiceImpl extends ServiceImpl<StockBusinessMapper,StockBusiness> implements StockService {
    @Resource
    private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;
    @Resource
    private StockInfoConfig stockInfoConfig;
    @Resource
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;

    @Override
    public List<StockBusiness> findAll() {
        List<StockBusiness> list = this.list();
        return list;
    }

    /** 获取最新的A股大盘信息 */
    @Override
    public R<List<InnerMarketDomain>> innerIndexAll() {
        //1.获取国内大盘的id集合
        List<String> innerIds = stockInfoConfig.getInner();
        //2.获取最近最新的股票有效交易日
        Date lDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //mock数据
        String mockDate="20231126105600";   //TODO后续大盘数据实时拉去，将该行注释掉 传入的日期秒必须为0
        lDate = DateTime.parse(mockDate, DateTimeFormat.forPattern("yyyyMMddHHmmss")).toDate();
        //3.调用mapper查询指定日期下对应的国内大盘数据
        List<InnerMarketDomain> maps=stockMarketIndexInfoMapper.selectByIdsAndDate(innerIds,lDate);
        //组装响应的额数据
        if (CollectionUtils.isEmpty(maps)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }
        return R.ok(maps);
    }

    /**
     *  查获取最新的A股板块数据，以交易总金额降序查询，取前10条数据
     */
    @Override
    public R<List<StockBlockRtInfo>> sectorAllLimit() {
        //1.调用mapper接口获取数据
        List<StockBlockRtInfo> infos=stockBlockRtInfoMapper.sectorAllLimit();
        //2.组装数据
        if (CollectionUtils.isEmpty(infos)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }
        return R.ok(infos);
    }


}
