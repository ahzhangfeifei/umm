package com.umm.wfm.utils;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * SimpleFramework Xml parse tools
 * <p>
 * Created by zhangff on 2016/4/29.
 */
public class SimpleXmlMapper {

    /**
     * 解析对象至字符串
     *
     * @param obj 待解析对象
     * @return xml文本
     */
    public static String toXmlString(Object obj) {
        Serializer serializer = new Persister();
        ByteArrayOutputStream op = new ByteArrayOutputStream();
        try {
            serializer.write(obj, op);
            return op.toString("utf-8");
        } catch (Exception e) {
            throw new XmlParseException(e.getMessage(), e);
        } finally {
            try {
                op.flush();
            } catch (IOException e) {
                // ignore
            }
            try {
                op.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    /**
     * 此方法描述的是：xml反序列化
     */
    public static <T> T toBean(Class<T> c, String xmlStr, String format) {
        Format f = new Format(format);
        Serializer serializer = new Persister(f);
        try {
            return serializer.read(c, xmlStr);
        } catch (Exception e) {
            throw new XmlParseException(e.getMessage(), e);
        }
    }

    public static <T> T toBean(Class<T> c, String xmlStr) {
        Serializer serializer = new Persister();
        try {
            return serializer.read(c, xmlStr);
        } catch (Exception e) {
            throw new XmlParseException(e.getMessage(), e);
        }
    }

    public static <T> T toBean(Class<T> c, String xmlStr, boolean strict) {
        Serializer serializer = new Persister();
        try {
            return serializer.read(c, xmlStr, strict);
        } catch (Exception e) {
            throw new XmlParseException(e.getMessage(), e);
        }
    }
}
