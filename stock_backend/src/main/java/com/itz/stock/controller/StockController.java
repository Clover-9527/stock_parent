package com.itz.stock.controller;

import com.itz.stock.pojo.StockBusiness;
import com.itz.stock.service.StockService;
import org.springframework.web.bind.annotation .GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/quot")
public class StockController {
    @Resource
    private StockService stockService;

    @GetMapping("/stock/business/all")
    public List<StockBusiness> findAllBusinessInfo(){
        return stockService.findAll();
    }
}

