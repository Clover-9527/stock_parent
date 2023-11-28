package com.itz.stock.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itz.stock.common.domain.StockUpdownDomain;
import com.itz.stock.config.StockInfoConfig;
import com.itz.stock.common.domain.InnerMarketDomain;
import com.itz.stock.mapper.StockBlockRtInfoMapper;
import com.itz.stock.mapper.StockBusinessMapper;
import com.itz.stock.mapper.StockMarketIndexInfoMapper;
import com.itz.stock.mapper.StockRtInfoMapper;
import com.itz.stock.pojo.StockBlockRtInfo;
import com.itz.stock.pojo.StockBusiness;
import com.itz.stock.pojo.StockRtInfo;
import com.itz.stock.service.StockService;
import com.itz.stock.utils.DateTimeUtil;
import com.itz.stock.vo.PageResult;
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
    @Resource
    private StockRtInfoMapper stockRtInfoMapper;

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

    /**
     *  降序查询最新的个股涨幅排数据，取前10条数据
     */
    @Override
    public R<List<StockUpdownDomain>> getLastUpDownStock() {
        //获取最新有效的股票交易时间点精确到分钟）,并转换成java data格式
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO mock data
        lastDate = DateTime.parse("2023-11-30 23:20:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm")).toDate();
        List<StockUpdownDomain> list = stockRtInfoMapper.getLastUpDownStock(lastDate);
        if (CollectionUtils.isEmpty(list)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }
        return R.ok(list);
    }

    /**
     * 分页查询股票数据
     */
    @Override
    public R<PageResult<StockUpdownDomain>> getStocksByPage(Integer page, Integer pageSize) {
        //设置分页参数
        PageHelper.startPage(page, pageSize);
        //查询
        List<StockUpdownDomain> list = stockRtInfoMapper.getStocksByPage();
        if (CollectionUtils.isEmpty(list)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }
        //组装pageInfo对象
        PageInfo<StockUpdownDomain> pageResult = new PageInfo<>(list);

        //通过上一步创建的pageInfo,来用有参构造创建PageResult对象
        PageResult<StockUpdownDomain> pagreResult = new PageResult<>(pageResult);
        return R.ok(pagreResult);
    }


}
