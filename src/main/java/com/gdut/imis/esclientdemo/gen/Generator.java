package com.gdut.imis.esclientdemo.gen;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lulu
 * @Date 2019/6/9 23:25
 */
@Component
public class Generator {




    public static void main(String args[]){
        String packge="com.gdut.imis.esclientdemo";
        AutoGenerator generator=new AutoGenerator();
        GlobalConfig gc=new GlobalConfig();
        String projectPath=System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java/");
        gc.setAuthor("lele");
        gc.setOpen(false);
        gc.setMapperName("%sDao");
        gc.setXmlName("%sDao");
        generator.setGlobalConfig(gc);
        DataSourceConfig dataSourceConfig=new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mptest?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setUsername("root");
        generator.setDataSource(dataSourceConfig);
        PackageConfig packageConfig=new PackageConfig();
        packageConfig.setParent(packge);
        packageConfig.setController("web");
        packageConfig.setMapper("dao");
        packageConfig.setXml("dao.mapper");
        generator.setPackageInfo(packageConfig);
        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setExclude("role-user");
        strategyConfig.setSkipView(true);
generator.setStrategy(strategyConfig);
generator.execute();
    }
}
