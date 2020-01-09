package com.example.learnmall.mbg;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author liaozhenglong
 * @Date 2019/11/19 15:44
 * 用于生成MBG的代码
 **/
public class Generator {
    public static void main(String[] args) throws Exception{
        //mbg执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        //当生成的代码重复时覆盖源代码
        boolean overwrite = true;
        //读取我们的MBG的配置文件
        InputStream is = Generator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        //创建MBG
        MyBatisGenerator generator = new MyBatisGenerator(config,callback,warnings);
        //执行生成代码
        generator.generate(null);
        //输出警告信息
        for (String warning:warnings){
            System.out.println(warning);
        }
    }
}
