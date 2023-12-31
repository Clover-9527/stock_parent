package com.itz.stock.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.itz.stock.common.domain.StockExcelDomain;
import com.itz.stock.common.domain.StockUpdownDomain;
import com.itz.stock.config.StockInfoConfig;
import com.itz.stock.common.domain.InnerMarketDomain;
import com.itz.stock.mapper.StockBlockRtInfoMapper;
import com.itz.stock.mapper.StockBusinessMapper;
import com.itz.stock.mapper.StockMarketIndexInfoMapper;
import com.itz.stock.mapper.StockRtInfoMapper;
import com.itz.stock.pojo.StockBlockRtInfo;
import com.itz.stock.pojo.StockBusiness;
import com.itz.stock.service.StockService;
import com.itz.stock.utils.DateTimeUtil;
import com.itz.stock.vo.PageResult;
import com.itz.stock.vo.R;
import com.itz.stock.vo.ResponseCode;
import com.sun.deploy.net.URLEncoder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl extends ServiceImpl<StockBusinessMapper, StockBusiness> implements StockService {
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

    /**
     * 获取最新的A股大盘信息
     */
    @Override
    public R<List<InnerMarketDomain>> innerIndexAll() {
        //1.获取国内大盘的id集合
        List<String> innerIds = stockInfoConfig.getInner();
        //2.获取最近最新的股票有效交易日
        Date lDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //mock数据
        String mockDate = "20231126105600";   //TODO后续大盘数据实时拉去，将该行注释掉 传入的日期秒必须为0
        lDate = DateTime.parse(mockDate, DateTimeFormat.forPattern("yyyyMMddHHmmss")).toDate();
        //3.调用mapper查询指定日期下对应的国内大盘数据
        List<InnerMarketDomain> maps = stockMarketIndexInfoMapper.selectByIdsAndDate(innerIds, lDate);
        //组装响应的额数据
        if (CollectionUtils.isEmpty(maps)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }
        return R.ok(maps);
    }

    /**
     * 查获取最新的A股板块数据，以交易总金额降序查询，取前10条数据
     */
    @Override
    public R<List<StockBlockRtInfo>> sectorAllLimit() {
        //1.调用mapper接口获取数据
        List<StockBlockRtInfo> infos = stockBlockRtInfoMapper.sectorAllLimit();
        //2.组装数据
        if (CollectionUtils.isEmpty(infos)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }
        return R.ok(infos);
    }

    /**
     * 降序查询最新的个股涨幅排数据，取前10条数据
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

    /**
     * 统计T日（最近一次股票交易日）涨停和跌停分时统计
     */
    @Override
    public R<Map> getStockUpDownCount() {
        //获取开盘时间
        Date openDateTime = DateTimeUtil.getOpenDate(DateTime.now()).toDate();
        //获取收盘时间
        Date closeDateTime = DateTimeUtil.getCloseDate(DateTime.now()).toDate();
        //TODO mock data
        openDateTime = DateTime.parse("20231203093000", DateTimeFormat.forPattern("yyyyMMddHHmmss")).toDate();
        closeDateTime = DateTime.parse("20231203143000", DateTimeFormat.forPattern("yyyyMMddHHmmss")).toDate();
        // 用map 集合将数据以键值对形式保存,并封装到list集合中
        List<Map> upCount = stockRtInfoMapper.getStockUpDownCount(openDateTime, closeDateTime, 1);
        List<Map> downCount = stockRtInfoMapper.getStockUpDownCount(openDateTime, closeDateTime, 0);
        //4.组装数据到map
        HashMap<String, List<Map>> map = new HashMap<>();
        //封装到 map中
        map.put("upList", upCount);
        map.put("downList", downCount);
        //5.响应
        return R.ok(map);
    }

    /**
     * 涨幅信息导出Excel
     */
    @Override
    public void exportStockInfo(HttpServletResponse response, int page, int pageSize) throws IOException {
        //1.设置响应数据类型
        response.setContentType("application/vnd.ms-excel");
        //2.设置响应数据的编码格式
        response.setCharacterEncoding("utf-8");
        //3.设置默认的文件名称
        // URLEncoder.encode防止中文乱码
        String fileName = URLEncoder.encode("stockRt", "UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xlsx");

        //4.分页查询股票数据
        PageHelper.startPage(page, pageSize);
        List<StockUpdownDomain> infos = stockRtInfoMapper.getStocksByPage();
        // 判断info是否为空
        if (CollectionUtils.isEmpty(infos)) {
            R<String> error = R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
            // 将错误信息转换成json字符串响应前端
            Gson gson = new Gson();
            String errorToJason = gson.toJson(error);
            response.getWriter().write(errorToJason);
            return;
        }
        //将List<StockUpdownDomain> 转换为 List<StockExcelDomain>
        List<StockExcelDomain> domains = infos.stream().map(info -> {
            StockExcelDomain domain=new StockExcelDomain();
            BeanUtils.copyProperties(info,domain);
            return domain;
        }).collect(Collectors.toList());
        //5.数据导出
        EasyExcel.write(response.getOutputStream(), StockExcelDomain.class)
                 .sheet("股票数据")
                 .doWrite(domains);
    }

    /**
     * 统计国内A股大盘 T日和 T-1日成交量对比
     *   如果当前时间不在有效的股票交易日下，则将距离最近的一个时间点作为T日时间查询
     */
    @Override
    public R<Map> getStockTradeVol() {
        //1. 获取最近的股票交易日时间，精确到分钟
        DateTime curTime = DateTimeUtil.getLastDate4Stock(DateTime.now());
        Date curDayTime = curTime.toDate();
        //2. 获取开盘时间点
        DateTime openDate = DateTimeUtil.getOpenDate(curTime);
        Date openDayTime = openDate.toDate();
        //3.获取前一天的时间点
        DateTime preCurTime = DateTimeUtil.getPreviousTradingDay(curTime);
        Date preTradingDayCurTime = preCurTime.toDate();
        //4. 获取上一个有效日期开盘时间点
        DateTime preOpenDate = DateTimeUtil.getOpenDate(preCurTime);
        Date preTradingDayOpenTime = preOpenDate.toDate();

        //TODO  MOCK DATA
        String tDateStr = "20231212143000";
        curDayTime = DateTime.parse(tDateStr, DateTimeFormat.forPattern("yyyyMMddHHmmss")).toDate();
        String openDateStr = "20231212093000";
        openDayTime = DateTime.parse(openDateStr, DateTimeFormat.forPattern("yyyyMMddHHmmss")).toDate();

        String preTStr = "20231211143000";
        preTradingDayCurTime = DateTime.parse(preTStr, DateTimeFormat.forPattern("yyyyMMddHHmmss")).toDate();
        String openDateStr2 = "20231211093000";
        preTradingDayOpenTime = DateTime.parse(openDateStr2, DateTimeFormat.forPattern("yyyyMMddHHmmss")).toDate();

        //5. 分别查询两个交易日的交易量
        List<Map> currentTradeVol = stockMarketIndexInfoMapper.stockTradeVolCount(stockInfoConfig.getInner(),openDayTime, curDayTime);
        if (CollectionUtils.isEmpty(currentTradeVol)) {
            currentTradeVol = new ArrayList<>();
        }
        List<Map> preTradeVol = stockMarketIndexInfoMapper.stockTradeVolCount(stockInfoConfig.getInner(),preTradingDayOpenTime, preTradingDayCurTime);
        if (CollectionUtils.isEmpty(preTradeVol)) {
            preTradeVol = new ArrayList<>();
        }

        //6. 封装结果并返回
        Map<String, List<Map>> map = new HashMap<>();
        map.put("volList", currentTradeVol);
        map.put("yesVolList", preTradeVol);

        return R.ok(map);
    }


}
