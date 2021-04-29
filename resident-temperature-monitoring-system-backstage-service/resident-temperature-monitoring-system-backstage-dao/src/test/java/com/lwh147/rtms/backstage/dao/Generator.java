package com.lwh147.rtms.backstage.dao;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: mybatis逆向
 * @author: lwh
 * @create: 2021/4/29 11:15
 * @version: v1.0
 **/
@Slf4j
public class Generator {
    /**
     * 当生成的代码重复时，覆盖原代码
     **/
    public static final boolean OVERWRITE = true;
    /**
     * MBG 配置文件路径
     **/
    public static final String PATH = "mybatis/generatorConfig.xml";

    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        log.info("开始执行mybaits逆向");
        // MBG 执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        // 读取我们的 MBG 配置文件
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(getResourceAsStream(PATH));
        DefaultShellCallback callback = new DefaultShellCallback(OVERWRITE);
        // 创建MGB
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        // 执行生成代码
        myBatisGenerator.generate(null);
        // 输出警告信息
        for (String warning : warnings) {
            log.warn(warning);
        }
        log.info("mybaits逆向执行结束");
    }

    /**
     * 以输入流的方式加载配置文件
     *
     * @param path
     * @return java.io.InputStream
     **/
    public static InputStream getResourceAsStream(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }
}
