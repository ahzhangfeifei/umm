package com.umm.wfm.security.log;

import ch.qos.logback.classic.Level;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.helpers.MessageFormatter;


/**
 * Created with IntelliJ IDEA
 * User: lizhimu
 * Date: 2017/6/19
 * Time: 11:25
 * Desc:
 */
public class LoggerComponent implements Logger {


    private String name;

    private Logger logger;

    public LoggerComponent(String name) {
        this.name = name;
        this.logger = LoggerFactory.getLogger(name);
    }

    /***
     * 异步过滤日志生产者
     * @param marker marker
     * @param message  message
     * @param params  params
     * @param msgLevel   msgLevel
     * @param throwable  throwable
     */
    private void logMessage(Marker marker, String message, Object[] params, Level msgLevel, Throwable throwable) {
        try {
            LogUtil.getAsynLogQueue().offer(new LogVar(logger, marker, message, params, msgLevel, throwable));
        } catch (Exception e) {
            log(new LogVar(logger, marker, message, params, msgLevel, throwable));
        }
    }

    /***
     * 同步过滤输出日志
     * @param logVar 日志相关参数结构
     */
    private void log(LogVar logVar) {
        String formatAndFilterMsg = SensitiveMsgFliterUtil.filterSensitiveMsg(
                MessageFormatter.arrayFormat(logVar.getMessage(), logVar.getParams()).getMessage());
        if (logVar.getLevel() == Level.TRACE) {
            logger.trace(logVar.getMarker(), formatAndFilterMsg, logVar.getThrowable());
        } else if (logVar.getLevel() == Level.DEBUG) {
            logger.debug(logVar.getMarker(), formatAndFilterMsg, logVar.getThrowable());
        } else if (logVar.getLevel() == Level.INFO) {
            logger.info(logVar.getMarker(), formatAndFilterMsg, logVar.getThrowable());
        } else if (logVar.getLevel() == Level.WARN) {
            logger.warn(logVar.getMarker(), formatAndFilterMsg, logVar.getThrowable());
        } else if (logVar.getLevel() == Level.ERROR) {
            logger.error(logVar.getMarker(), formatAndFilterMsg, logVar.getThrowable());
        }
    }

    @Data
    public static class LogVar {
        private Logger logger;
        private Marker marker;
        private String message;
        private Object[] params;
        private Level level;
        private Throwable throwable;

        public LogVar(Logger logger, Marker marker, String message, Object[] params, Level level, Throwable throwable) {
            this.logger = logger;
            this.marker = marker;
            this.message = message;
            this.params = params;
            this.throwable = throwable;
            this.level = level;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void trace(String s) {
        this.logMessage(null, s, null, Level.TRACE, null);
    }

    @Override
    public void trace(String s, Object o) {
        this.logMessage(null, s, new Object[]{o}, Level.TRACE, null);
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        this.logMessage(null, s, new Object[]{o}, Level.TRACE, null);
    }

    @Override
    public void trace(String s, Object... objects) {
        this.logMessage(null, s, objects, Level.TRACE, null);
    }

    @Override
    public void trace(String s, Throwable throwable) {
        this.logMessage(null, s, null, Level.TRACE, throwable);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return true;
    }

    @Override
    public void trace(Marker marker, String s) {
        this.logMessage(marker, s, null, Level.TRACE, null);
    }

    @Override
    public void trace(Marker marker, String s, Object o) {
        this.logMessage(marker, s, new Object[]{o}, Level.TRACE, null);
    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o1) {
        this.logMessage(marker, s, new Object[]{o, o1}, Level.TRACE, null);
    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {
        this.logMessage(marker, s, objects, Level.TRACE, null);
    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {
        this.logMessage(marker, s, null, Level.TRACE, throwable);
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void debug(String s) {
        this.logMessage(null, s, null, Level.DEBUG, null);
    }

    @Override
    public void debug(String s, Object o) {
        this.logMessage(null, s, new Object[]{o}, Level.DEBUG, null);
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        this.logMessage(null, s, new Object[]{o, o1}, Level.DEBUG, null);
    }

    @Override
    public void debug(String s, Object... objects) {
        this.logMessage(null, s, objects, Level.DEBUG, null);
    }

    @Override
    public void debug(String s, Throwable throwable) {
        this.logMessage(null, s, null, Level.DEBUG, throwable);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return true;
    }

    @Override
    public void debug(Marker marker, String s) {
        this.logMessage(marker, s, null, Level.DEBUG, null);
    }

    @Override
    public void debug(Marker marker, String s, Object o) {
        this.logMessage(marker, s, new Object[]{o}, Level.DEBUG, null);
    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o1) {
        this.logMessage(marker, s, new Object[]{o, o1}, Level.DEBUG, null);
    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {
        this.logMessage(marker, s, objects, Level.DEBUG, null);
    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {
        this.logMessage(marker, s, null, Level.DEBUG, throwable);
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public void info(String s) {
        this.logMessage(null, s, null, Level.INFO, null);
    }

    @Override
    public void info(String s, Object o) {
        this.logMessage(null, s, new Object[]{o}, Level.INFO, null);
    }

    @Override
    public void info(String s, Object o, Object o1) {
        this.logMessage(null, s, new Object[]{o}, Level.INFO, null);
    }

    @Override
    public void info(String s, Object... objects) {
        this.logMessage(null, s, objects, Level.INFO, null);
    }

    @Override
    public void info(String s, Throwable throwable) {
        this.logMessage(null, s, null, Level.INFO, throwable);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return true;
    }

    @Override
    public void info(Marker marker, String s) {
        this.logMessage(marker, s, null, Level.INFO, null);
    }

    @Override
    public void info(Marker marker, String s, Object o) {
        this.logMessage(marker, s, new Object[]{o}, Level.INFO, null);
    }

    @Override
    public void info(Marker marker, String s, Object o, Object o1) {
        this.logMessage(marker, s, new Object[]{o, o1}, Level.INFO, null);
    }

    @Override
    public void info(Marker marker, String s, Object... objects) {
        this.logMessage(marker, s, objects, Level.INFO, null);
    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {
        this.logMessage(marker, s, null, Level.INFO, throwable);
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public void warn(String s) {
        this.logMessage(null, s, null, Level.WARN, null);
    }

    @Override
    public void warn(String s, Object o) {
        this.logMessage(null, s, new Object[]{o}, Level.WARN, null);
    }

    @Override
    public void warn(String s, Object... objects) {
        this.logMessage(null, s, objects, Level.WARN, null);
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        this.logMessage(null, s, new Object[]{o, o1}, Level.WARN, null);
    }

    @Override
    public void warn(String s, Throwable throwable) {
        this.logMessage(null, s, null, Level.WARN, throwable);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return true;
    }

    @Override
    public void warn(Marker marker, String s) {
        this.logMessage(marker, s, null, Level.WARN, null);
    }

    @Override
    public void warn(Marker marker, String s, Object o) {
        this.logMessage(marker, s, new Object[]{o}, Level.WARN, null);
    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o1) {
        this.logMessage(marker, s, new Object[]{o, o1}, Level.WARN, null);
    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {
        this.logMessage(marker, s, objects, Level.WARN, null);
    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {
        this.logMessage(marker, s, null, Level.WARN, throwable);
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void error(String s) {
        this.logMessage(null, s, null, Level.ERROR, null);
    }

    @Override
    public void error(String s, Object o) {
        this.logMessage(null, s, new Object[]{o}, Level.ERROR, null);
    }

    @Override
    public void error(String s, Object o, Object o1) {
        this.logMessage(null, s, new Object[]{o, o1}, Level.ERROR, null);
    }

    @Override
    public void error(String s, Object... objects) {
        this.logMessage(null, s, objects, Level.ERROR, null);
    }

    @Override
    public void error(String s, Throwable throwable) {
        this.logMessage(null, s, null, Level.ERROR, throwable);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return true;
    }

    @Override
    public void error(Marker marker, String s) {
        this.logMessage(marker, s, null, Level.ERROR, null);
    }

    @Override
    public void error(Marker marker, String s, Object o) {
        this.logMessage(marker, s, new Object[]{o}, Level.ERROR, null);
    }

    @Override
    public void error(Marker marker, String s, Object o, Object o1) {
        this.logMessage(marker, s, new Object[]{o, o1}, Level.ERROR, null);
    }

    @Override
    public void error(Marker marker, String s, Object... objects) {
        this.logMessage(marker, s, objects, Level.ERROR, null);
    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {
        this.logMessage(marker, s, null, Level.ERROR, throwable);
    }
}
