package com.example.perms.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author linmr
 * @description: mybatis-plus自动生成工具
 * 1.生成的路径为 chain_parent/chain_base/src/main/java/com/chain/base/autoGen
 * 2.生成文件后自行调整文件位置
 * 3.调整文件位置后需注意修改mapper.xml的namespace为对应Mapper.java路径
 * @date 2020/12/17
 */
public class CodeGenerator {


    public static void main(String[] args) {

        //FIXME 请修改成要生成的表名
        String[] tables = new String[]{"sys_dept"};
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://cdb-nyg9m8iq.cd.tencentcdb.com:10178/chain_test?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("133##pgg");
        mpg.setDataSource(dsc);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //FIXME 这里可以修改生成路径 默认:/chain_base
        gc.setOutputDir(projectPath + "/chain_base"+"/src/main/java");
        gc.setAuthor("autoGen");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);



        // 包配置
        PackageConfig pc = new PackageConfig();
        //FIXME 这里可以修改生成包名 默认:autoGen
        pc.setModuleName("autoGen");
        pc.setParent("com.chain.base");
        // FIXME 这里可以指定生成包路径
//        pc.setController("controller");
//        pc.setService("service");
//        pc.setServiceImpl("service.impl");
//        pc.setEntity("bean.po");
//        pc.setMapper("main.mapper");
//        pc.setXml("../resources/mapping");
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        strategy.setInclude(tables);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.execute();
    }

}
