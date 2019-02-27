package com.spring.boot.cloud.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.List;
import java.util.Map;

import org.thymeleaf.util.MapUtils;


/**
 * @Describe: 基于mybatis-plus 插件生产代码的工具，代码简洁，推荐使用
 * @Created by tangfeng02 2018/7/4
 */
public class MybatisPlusGenerator {

    //固定配置 - JDBC
    //static String dbUrl = "jdbc:mysql://localhost:3306/hotel_mint_t?useUnicode=true&amp;characterEncoding=UTF8&amp;connectTimeout=5000&amp;socketTimeout=500000&amp;allowMultiQueries=true";
    static String dbUrl = "jdbc:mysql://localhost:3306/TestDB";
    static String driverName = "com.mysql.jdbc.Driver";
    static String un = "root";
    static String pd = "root";

    //固定配置 - package
    static String parentPackageName = "com.spring.boot.cloud";

    //固定配置 - 自动生产文件的路劲
    static String outPutDir = "pre/mybatis-plus";

    //固定配置 - user -> UserService, 设置成true: user -> IUserService
    static boolean serviceNameStartWithI = true;

    //可变配置
    static String author = "tangfeng";


    //可变配置包
    //mybatis 配置
    static String domainPackage = "dao.entity";
    static String mapperJavaPackage = "dao.mapper";
    static String servicePackage = "service";
    static String serviceImplPackage = "service.impl";
    static String mapperXmlPackage = "resources.mappers.mybatis";

    //kylin 配置

    /*static String domainPackage = "dal.entity.kylin";
    static String mapperJavaPackage = "dal.mappers.kylin";
    static String servicePackage = "service.kylin";
    static String serviceImplPackage = "service.impl.kylin";
    static String mapperXmlPackage = "resources.mappers.kylin";*/


    //static List<String> tableNameList = Lists.newArrayList("mint_expressions_table_origin");
    static List<String> tableNameList = Lists.newArrayList(
            //"mint_budget_el_table",
            "test_table"
            /*,
            "mint_budget_el_condition",
            "mint_budget_el_constant_kpi",
            "mint_budget_el_selector_condition",
            "mint_budget_el_formula_kpi",
            "mint_budget_el_formula_template",
            "mint_budget_el_template_condition"*/
    );




    public static void main(String[] args) {
        executor(author, outPutDir, null, (String[]) tableNameList.stream().toArray(String[]::new));
    }

    public static void executor(String author, String outPutDir, Map<String, String> packageMap, String... tableNames) {

        if (!MapUtils.isEmpty(packageMap)) {
            domainPackage = packageMap.get("domainPackage");
            mapperJavaPackage = packageMap.get("mapperJavaPackage");
            servicePackage = packageMap.get("servicePackage");
            serviceImplPackage = packageMap.get("serviceImplPackage");
            serviceImplPackage = packageMap.get("serviceImplPackage");
            mapperXmlPackage = packageMap.get("mapperXmlPackage");
        }


        System.out.println("开始生产代码。。。");
        DataSourceConfig dataSourceConfig = getDataSourceConfit();
        StrategyConfig strategyConfig = getStrategyConfig(tableNames);
        GlobalConfig globalConfig = getGlobalConfig(author, outPutDir, serviceNameStartWithI);

        new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(parentPackageName)
                                .setEntity(domainPackage)
                                .setMapper(mapperJavaPackage)
                                .setService(servicePackage)
                                .setServiceImpl(serviceImplPackage)
                                .setXml(mapperXmlPackage)
                                .setController("controller")
                )
                .execute();
        System.out.println("生产代码结束。。。");
    }


    /**
     * 数据源配置
     *
     * @return
     */
    private static DataSourceConfig getDataSourceConfit() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername(un)
                .setPassword(pd)
                .setDriverName(driverName);
        return dataSourceConfig;
    }


    /**
     * 配置生成的类和属性的命名配置
     *
     * @param tableNames
     * @return
     */
    private static StrategyConfig getStrategyConfig(String... tableNames) {
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                //是否使用lombokModel
                .setEntityLombokModel(true)
                //false: 实体属性注解bind带下划线的db字段，true:不注解实体属性
//                .setDbColumnUnderline(true)
                //命名策略：nochange：创建类和属性和数据库表保持一致（类名和属性名包含下划线）；underline_to_camel：正常的驼峰命名方式
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        return strategyConfig;
    }

    private static GlobalConfig getGlobalConfig(String author, String outPutDir, boolean serviceNameStartWithI) {
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)
                .setAuthor(author)
                .setOutputDir(outPutDir)
                //生成代码后是否打开目录
                .setOpen(true)
                .setEnableCache(false)
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setFileOverride(true);

        //user -> UserService, 设置成true: user -> IUserService
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }

        return config;
    }

}
