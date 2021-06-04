package com.scofield.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Scofield
 * @description:
 * @date: 2021/4/16
 * @email: 543196660@qq.com
 * @time: 16:24
 */
public class CodeGenerator {

    private static String author = "Scofield";//作者名称
    private static String projectPath = System.getProperty("user.dir");
    private static String driver = "com.mysql.cj.jdbc.Driver";//驱动，注意版本
    //连接路径,注意修改数据库名称
    private static String url = "jdbc:mysql://127.0.0.1:3306/scofield?useUnicode=true&characterEncoding=utf8";
    private static String username = "root";//数据库用户名
    private static String password = "root";//数据库密码

    public static void main(String[] args) {
        String[] nameList = new String[]{"sys_user"};
        for (int i = 0; i < nameList.length; i++) {
            codeGenerator(nameList[i]);
        }
    }


    public static void codeGenerator(String tableName) {

        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java")
                .setAuthor(author)
                .setOpen(false)  // 是否打开输出目录
                .setIdType(IdType.ASSIGN_ID)
                .setFileOverride(true)   // 是否覆盖已有文件
                .setActiveRecord(false)   // 是否开启 ActiveRecord 模式
                /**
                 * ActiveRecord模式：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
                 *
                 * 即直接使用实体类 CRUD操作
                 */
                .setEnableCache(false)   // 是否在xml中添加二级缓存配置
                .setBaseResultMap(true)  // 是否开启 BaseResultMap
                .setBaseColumnList(true)  // XML columList
                .setDateType(DateType.ONLY_DATE)  // 时间格式
                .setControllerName("%sController")   // controller 命名方式
                .setServiceName("%sService")         // service 命名方式
                .setServiceImplName("%sServiceImpl") // serviceImpl 命名方式
              //  .setEntityName("%sEntity" )
                .setMapperName("%sMapper")           // mapper 命名方式
                .setXmlName("%sMapper.xml");          // xml 命名方式

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL)
                .setTypeConvert(new MySqlTypeConvert())
                .setDriverName(driver)
                .setUsername(username)
                .setPassword(password)
                .setUrl(url);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        // 设置创建时间和更新时间自动填充策略
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(new TableFill("create_by", FieldFill.INSERT));
        tableFills.add(new TableFill("update_by", FieldFill.INSERT_UPDATE));
        tableFills.add(new TableFill("status", FieldFill.INSERT));
        tableFills.add(new TableFill("create_time", FieldFill.INSERT));
        tableFills.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
        /** 此处可以修改为您的表前缀，如果没有，注释掉即可*/
        strategy.setTablePrefix(new String[]{"sys_"})
                /** 表名生成策略*/
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                /** 需要生成的表*/
                .setInclude(tableName)
                .setEntityLombokModel(true)
                .setTableFillList(tableFills)
        ;


        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.scofield");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("entity");
        pc.setMapper("mapper");


        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();

        // 调整 xml 生成目录
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });


        cfg.setFileOutConfigList(focList);


        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/code/controller.java.vm")
                .setService("/templates/code/service.java.vm")
                .setServiceImpl("/templates/code/serviceImpl.java.vm");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。

        mpg.setTemplate(tc)
                .setCfg(cfg)
                .setPackageInfo(pc)
                .setStrategy(strategy)
                .setGlobalConfig(gc)
                .setDataSource(dsc);

        // 执行生成
        mpg.execute();
    }


}