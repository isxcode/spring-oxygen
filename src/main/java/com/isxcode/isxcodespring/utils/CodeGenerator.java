package com.isxcode.isxcodespring.utils;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成器
 * java jvm params add -Dfile.encoding=utf-8
 *
 * @author ispong
 * @date 2019/9/30
 * @version v0.1.0
 */
public class CodeGenerator {

    /**
     * 控制台输入
     *
     * @author ispong
     * @date 2019/9/30
     */
    private static String scanner() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("please input tableName:");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("please input right params tableName !");
    }

    public static void main(String[] args) {

        String pkgParent = "com.isxcode.isxcodespring";

        AutoGenerator mpg = new AutoGenerator();
        String projectPath = System.getProperty("user.dir");

        // 设置模板引擎为freemarker
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // config file name
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "\\src\\main\\java");
        gc.setAuthor("ispong");
        gc.setOpen(false);
        gc.setMapperName("%sDao");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        gc.setEntityName("%sEntity");
        gc.setXmlName("%s");
        mpg.setGlobalConfig(gc);

        // config database info
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://106.15.189.6:3306/isxcode?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("isxcode");
        mpg.setDataSource(dsc);

        // config file go to which package
        PackageConfig pc = new PackageConfig();
        pc.setParent(pkgParent);
        pc.setEntity("model.entity");
        pc.setServiceImpl("service.impl");
        pc.setMapper("dao");
        mpg.setPackageInfo(pc);

        // user custom file template
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        String templatePath = "/templates/mapper.xml.ftl";
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getXmlName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // config template
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // other config
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperEntityClass(pkgParent+".common.BaseEntity");
        strategy.setSuperControllerClass(pkgParent+".common.BaseController");
        String[] baseEntityColumns = {"id", "create_by", "create_date"};
        strategy.setSuperEntityColumns(baseEntityColumns);
        strategy.setInclude(scanner().split(","));
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        // execute progress
        mpg.execute();
    }

}
