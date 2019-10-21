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
 * 
 * @author ispong
 * @date 2019/9/30
 * @version v0.1.0
 */
public class CodeGenerator {

    /**
     * 作者
     */
    public String author = "ispong";

    /**
     * 数据库ip
     */
    private String dataIp = "106.15.189.6";

    /**
     * 数据库端口号
     */
    private String dataPort = "3306";

    /**
     * 数据库名称
     */
    private String dataName = "isxcode";

    /**
     * 数据库用户名
     */
    private String dataUser = "root";

    /**
     * 数据库密码
     */
    private String dataPwd = "isxcode";

    /**
     * 文件夹父类
     */
    private String pkgParent = "com.isxcode.isxcodespring";

    /**
     * baseEntity文件
     */
    private String pkgBaseEntity = "com.ispong.isxcode.common.BaseEntity";

    /**
     * baseController文件
     */
    private String pkgBaseController = "com.ispong.isxcode.common.BaseController";

    /**
     * 控制台输入
     *
     * @author ispong
     * @date 2019/9/30
     */
    private static String scanner() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("please input tableName :");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("please input right params tableName !");
    }

    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        
        // 当前路径
        String projectPath = System.getProperty("user.dir");

        // 配置freemarker引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "\\src\\main\\java");
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setMapperName("%sDao");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        gc.setEntityName("%sEntity");
        gc.setXmlName("%s");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://"+DATABASE_IP+":"+DATABASE_PORT+"/"+DATABASE_NAME+"?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(DATABASE_USER);
        dsc.setPassword(DATABASE_PWD);
        mpg.setDataSource(dsc);

        // 包名配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PKG_PARENT);
        pc.setEntity("moudle.entity");
        pc.setServiceImpl("service.impl");
        pc.setMapper("dao");
        mpg.setPackageInfo(pc);

        // 自定义配置
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

        // 配置自定义模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperEntityClass(PKG_BASE_ENTITY);
        strategy.setSuperControllerClass(PKG_BASE_CONTROLLER);
        strategy.setSuperEntityColumns("id", "createBy", "createDate");
        strategy.setInclude(scanner().split(","));
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        // 执行代码生成
        mpg.execute();
    }

}
