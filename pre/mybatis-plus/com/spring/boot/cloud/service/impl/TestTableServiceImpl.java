package com.spring.boot.cloud.service.impl;

import com.spring.boot.cloud.dao.entity.TestTable;
import com.spring.boot.cloud.dao.mapper.TestTableMapper;
import com.spring.boot.cloud.service.ITestTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangfeng
 * @since 2019-02-26
 */
@Service
public class TestTableServiceImpl extends ServiceImpl<TestTableMapper, TestTable> implements ITestTableService {

}
