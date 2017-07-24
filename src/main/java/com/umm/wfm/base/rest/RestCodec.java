package com.umm.wfm.base.rest;

/**
 * codec 接口
 * Created by zhangff on 2016/8/15.
 */
public interface RestCodec {

    byte[] encode(Object input);

    Object decode(byte[] input);

    String mediaType();

}
