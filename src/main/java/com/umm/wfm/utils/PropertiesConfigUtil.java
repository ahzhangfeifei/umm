package com.umm.wfm.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * Created by makun on 2017/6/16.
 * 读取 *.properties 配置文件，获取配置参数
 */
public class PropertiesConfigUtil {
    private static Properties properties;

    static {
        try {
            properties.putAll(PropertiesLoaderUtils.loadAllProperties("tspclient-refference.properties"));
        } catch (IOException e) {
            throw new RuntimeException("load properties files error", e);
        }
    }

    public static String getProperty(String key) {
        return getProperty(key, StringUtils.EMPTY);
    }

    private static String getProperty(String key, String defaultValue) {
        if (StringUtils.isNotBlank(key) && properties != null) {
            String value = properties.getProperty(key);
            if (StringUtils.isBlank(value)) {
                return defaultValue;
            }
            if (value.indexOf("[") != -1 && value.indexOf("]") != -1) {
                String tempKey = value.substring(value.indexOf("[") + 1, value.indexOf("]"));
                return value.replace("[" + tempKey + "]", properties.getProperty(tempKey));
            }
            return value;
        }
        return StringUtils.EMPTY;
    }

    public static Integer getIntegerProperty(String key) {
        return getIntegerProperty(key, null);
    }

    private static Integer getIntegerProperty(String key, Integer defaultValue) {
        String value = getProperty(key);
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static Boolean getBooleanProperty(String key) {
        return getBooleanProperty(key, null);
    }

    private static Boolean getBooleanProperty(String key, Boolean defaultValue) {
        String value = getProperty(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return Boolean.valueOf(value);
    }
}
