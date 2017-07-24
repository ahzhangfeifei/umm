package com.umm.wfm.utils;

/**
 * Created by zhangff on 2016/4/29.
 */
public class XmlParseException extends RuntimeException {

    private static final long serialVersionUID = -7366768913025277805L;

    /**
     *
     */
    public XmlParseException() {
        super();
    }

    /**
     * @param message String入参
     */
    public XmlParseException(String message) {
        super(message);
    }

    /**
     * @param message String 入参
     * @param cause   Throwable 入参
     */
    public XmlParseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause Throwable入参
     */
    public XmlParseException(Throwable cause) {
        super(cause);
    }

}
