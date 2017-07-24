package com.umm.wfm.security.log;

import ch.qos.logback.classic.Level;
import com.umm.wfm.security.log.LoggerComponent.LogVar;
import org.slf4j.Logger;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 * User: lizhimu
 * Date: 2017/6/19
 * Time: 10:43
 * Desc:
 */

@Component
public class LogUtil implements InitializingBean {

    private static BlockingQueue<LoggerComponent.LogVar> asynLogQueue = new LinkedBlockingDeque<>();

    @Resource(name = "asynLogFilterExecutor")
    private ThreadPoolTaskExecutor asynLogFilterExecutor;

    /***
     * 通过字符串获取日志logger对象
     * @param name 类名字符串
     * @return 打印logger对象
     */
    public static Logger getLogger(String name) {
        return new LoggerComponent(name);
    }

    public static BlockingQueue<LoggerComponent.LogVar> getAsynLogQueue() {
        return asynLogQueue;
    }

    /***
     * 通过类 获取打印日志类
     * @param clazz class 类
     * @return 打印logger对象
     */
    public static Logger getLogger(Class clazz) {
        return getLogger(clazz.getName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        asynLogFilterExecutor.submit(new AsynLogMonitor(asynLogQueue, asynLogFilterExecutor));
    }

    /***
     * 生产者 消费者模型监听器
     */
    class AsynLogMonitor implements Runnable {

        private BlockingQueue<LoggerComponent.LogVar> asynLogQueue;

        private ThreadPoolTaskExecutor threadPoolTaskExecutor;

        public AsynLogMonitor(BlockingQueue<LoggerComponent.LogVar> asynLogQueue,
                              ThreadPoolTaskExecutor threadPoolTaskExecutor) {
            this.asynLogQueue = asynLogQueue;
            this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    LoggerComponent.LogVar logVar = asynLogQueue.poll(1, TimeUnit.SECONDS);
                    threadPoolTaskExecutor.submit(new AsynLogConsumer(logVar));
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }

    /***
     * 消费者线程
     */
    class AsynLogConsumer implements Runnable {

        private LoggerComponent.LogVar logVar;

        public AsynLogConsumer(LogVar logVar) {
            this.logVar = logVar;
        }

        @Override
        public void run() {
            String formatAndFilterMsg = SensitiveMsgFliterUtil.filterSensitiveMsg(
                    MessageFormatter.arrayFormat(logVar.getMessage(), logVar.getParams()).getMessage());
            if (logVar.getLevel() == Level.TRACE) {
                logVar.getLogger().trace(logVar.getMarker(), formatAndFilterMsg, logVar.getThrowable());
            } else if (logVar.getLevel() == Level.DEBUG) {
                logVar.getLogger().debug(logVar.getMarker(), formatAndFilterMsg, logVar.getThrowable());
            } else if (logVar.getLevel() == Level.INFO) {
                logVar.getLogger().info(logVar.getMarker(), formatAndFilterMsg, logVar.getThrowable());
            } else if (logVar.getLevel() == Level.WARN) {
                logVar.getLogger().warn(logVar.getMarker(), formatAndFilterMsg, logVar.getThrowable());
            } else if (logVar.getLevel() == Level.ERROR) {
                logVar.getLogger().error(logVar.getMarker(), formatAndFilterMsg, logVar.getThrowable());
            }
        }
    }
}
