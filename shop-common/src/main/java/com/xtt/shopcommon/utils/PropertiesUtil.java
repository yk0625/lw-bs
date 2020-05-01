package com.xtt.shopcommon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @description: 读取property文件
 * @date: 2019-4-12 14:13
 * @author: xtt
 */
public class PropertiesUtil
{

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
    /**
     * 这个properties是专门存放server.properties中的变量的
     */
    private static Properties properties = null;

    public PropertiesUtil()
    {
    }

    public PropertiesUtil(String... resourcesPaths)
    {
        properties = loadProperties(resourcesPaths);
    }

    static {
        // 默认加载服务器ip配置文件
        properties = loadProperties("server.properties");
    }

    /**
     * 打印所有的key-value
     *
     * @param props
     */
    private static void loadAllProperty(Properties props)
    {
        Enumeration en = props.propertyNames();
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            String value = props.getProperty(key);
            logger.info("加载配置文件的值======> " + key + " : " + value);
        }
    }

    /**
     * 加载指定路径配置文件
     * <p>
     * //     * @param filePath
     */
//    public static Properties loadProperties(String filePath)
//    {
//        Properties prop = null;
//        try
//        {
//            prop = PropertiesLoaderUtils.loadAllProperties(filePath);
//            loadAllProperty(prop);
//        } catch (IOException e)
//        {
//            logger.error("读取配置文件失败：" + e.getMessage());
//        }
//        return prop;
//    }
//
    public static String getPropValByKey(String key)
    {
        return properties.getProperty(key);
    }

    /**
     * 载入多个文件, 文件路径使用Spring Resource格式.
     *
     * @param resourcesPaths
     * @return
     */
    private static Properties loadProperties(String... resourcesPaths)
    {
        Properties props = new Properties();
        for (String location : resourcesPaths) {
            InputStream is = null;
            try {
                Resource resource = resourceLoader.getResource(location);
                is = resource.getInputStream();
                props.load(is);
                is.close();
            } catch (IOException ex) {
                logger.warn("Could not load properties from path:" + location + ", " + ex.getMessage());
            }
        }
        return props;
    }
}
