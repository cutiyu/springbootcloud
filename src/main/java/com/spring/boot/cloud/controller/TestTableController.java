package com.spring.boot.cloud.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.spring.boot.cloud.dao.entity.TestTable;
import com.spring.boot.cloud.service.ITestTableService;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tangfeng
 * @since 2019-02-26
 */
@Controller
@RequestMapping("/testTable")
public class TestTableController {

    @Autowired
    private ITestTableService testTableService;

    @ResponseBody
    @RequestMapping("/test")
    public TestTable testTable(){



        TestTable testTable = new TestTable();
        testTable.setName("tf");
        testTable.setAge(11);
        testTable.setCreateTime(new Date());
        testTable.setUpdateTime(new Date());

        //插入操作
        boolean save = testTableService.save(testTable);
        System.out.println("对象主键："+testTable.getId());

        //插入后查询操作
        QueryWrapper<TestTable> wrapper = new QueryWrapper();
        TestTable tb = new TestTable();
        tb.setId(testTable.getId());
        wrapper.setEntity(tb);
        TestTable one = testTableService.getOne(wrapper);
        System.out.println("插入后查询"+JSON.toJSONString(one));

        //更新操作
        UpdateWrapper<TestTable> uwp = new UpdateWrapper<>();
        TestTable up = new TestTable();
        up.setId(testTable.getId());
        uwp.setEntity(up);

        TestTable utb = new TestTable();
        utb.setName("ttff");
        testTableService.update(utb,uwp);

        //更新后查询操作
        TestTable upone = testTableService.getOne(wrapper);
        System.out.println("更新后查询"+JSON.toJSONString(one));

        //删除操作-test-git



        return upone;
    }

}

