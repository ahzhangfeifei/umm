/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.umm.wfm.base.redis;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.umm.ats.core.redis.JedisTemplate;
import com.umm.wfm.intf.constants.BizzEx;
import com.umm.wfm.intf.exception.BizzException;
import redis.clients.jedis.JedisPool;

import java.io.UnsupportedEncodingException;

/**
 * 用protobuf进行序列化、反序列化的jedisTemplate
 */
public class ProtobufJedisTemplate extends JedisTemplate {

    public ProtobufJedisTemplate(JedisPool jedisPool) {
        super(jedisPool);
    }

    public <T> void setexPro(final String key, final T object, final Class<T> type, final int seconds) {
        execute((JedisActionNoResult) jedis -> jedis.setex(proKeyEncode(key), seconds, proEncode(object, type)));
    }

    public <T> T getPro(final String key, final Class<T> type) {
        return execute((JedisAction<T>) jedis -> proDecode(jedis.get(proKeyEncode(key)), type));
    }

    public byte[] proKeyEncode(String key) {
        try {
            return key.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("key序列化失败");
        }
    }

    public <T> byte[] proEncode(T input, Class<T> type) {
        if (input == null) {
            return null;
        }
        Codec<T> codec = ProtobufProxy.create(type);
        try {
            return codec.encode(input);
        } catch (Throwable ex) {
            throw new BizzException(BizzEx.PROTOBUF_EX, "【protobuf】序列化对象失败。类：" + type, ex);
        }
    }

    public <T> T proDecode(byte[] input, Class<T> clazz) {
        if (input == null || input.length == 0) {
            return null;
        }
        Codec<T> codec = ProtobufProxy.create(clazz);
        try {
            return codec.decode(input);
        } catch (Throwable ex) {
            throw new BizzException(BizzEx.PROTOBUF_EX, "【protobuf】反序列化对象失败。类：" + clazz, ex);
        }
    }
}
