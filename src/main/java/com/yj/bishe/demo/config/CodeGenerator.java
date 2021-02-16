package com.yj.bishe.demo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;


public class CodeGenerator {

	/**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
	
    public static void main(String[] args) {
    	
    	// 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        
     // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java/");
        gc.setAuthor("demo");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);
        
     // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/bishe?useUnicode=true&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("yjbb666");
        mpg.setDataSource(dsc);
        
     // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("");
        packageConfig.setParent("com.yj.bishe.demo"); // 总包名
        packageConfig.setEntity("entity");     // 实体包名
        packageConfig.setController("web");  //控制器包名
        packageConfig.setService("service");    //业务包名
        packageConfig.setMapper("dao");      //Mapper包名
        packageConfig.setXml("mapper");         //mapper.xml文件
        mpg.setPackageInfo(packageConfig);
        
     // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        
     // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
         
      // 自定义输出配置
         List<FileOutConfig> focList = new ArrayList<>();
         // 自定义配置会被优先输出
         focList.add(new FileOutConfig(templatePath) {
             @Override
             public String outputFile(TableInfo tableInfo) {
                 // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                 return projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName()
                         + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
             }
         });
         cfg.setFileOutConfigList(focList);
         mpg.setCfg(cfg);
         
      // 配置模板
         TemplateConfig templateConfig = new TemplateConfig();
         templateConfig.setXml(null);
         mpg.setTemplate(templateConfig);
         
      // 策略配置
         StrategyConfig strategy = new StrategyConfig();
//         strategy.setNaming(NamingStrategy.underline_to_camel);
//         strategy.setColumnNaming(NamingStrategy.underline_to_camel);
////         strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
//         strategy.setEntityLombokModel(true);
//         strategy.setRestControllerStyle(true);
         // 公共父类
//         strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
         // 写于父类中的公共字段
         strategy.setSuperEntityColumns("id");
         strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
         strategy.setControllerMappingHyphenStyle(true);
         strategy.setTablePrefix(packageConfig.getModuleName() + "_");
         mpg.setStrategy(strategy);

    	
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
    
}
