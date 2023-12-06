package com.itz.stock.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestEasyExcel {

    private List<User> users;

    @BeforeEach
    public void initUser(){
        users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User build = User.builder().userName("zhangsan" + i).address("sh" + i).age(30 + i).birthday(new Date()).build();
            users.add(build);
        }
    }

    @Test
    public void baseExport(){
        EasyExcel.write("D:\\test\\test.xlsx",User.class).sheet("userInfo").doWrite(users);
    }

    @Test
    public void baseImport(){
        List<User> users = new ArrayList<>();

        EasyExcel.read("D:\\test\\test.xlsx", User.class, new AnalysisEventListener<User>() {
            @Override
            public void invoke(User o, AnalysisContext analysisContext) {
                users.add(o);
            }
            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("读取完成");
            }
        }).sheet().doRead();
        System.out.println(users);
    }

}
