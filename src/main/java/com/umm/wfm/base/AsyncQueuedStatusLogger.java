package com.umm.wfm.base;

import com.umm.ats.core.asyncexecutor.AbstractAsyncQueuedHandler;
import com.umm.ats.core.asyncexecutor.AsyncQueuedStatusListener;
import com.umm.ats.core.constants.SysEx;
import com.umm.wfm.intf.exception.BizzException;
import com.umm.wfm.security.log.LogUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 后台异步执行队列服务， 队列满或超过阈值后的处理类
 *
 * Created by zhangff on 2016/2/14.
 */
@Service
public class AsyncQueuedStatusLogger implements AsyncQueuedStatusListener {

    private static final Logger LOGGER = LogUtil.getLogger(AsyncQueuedStatusLogger.class);

    @Override
    public void onFull(AbstractAsyncQueuedHandler handler, String queueName, int currentQueueSize) {
        String alertMessage = "[asyncQueueExecutor] 队列已满! 队列名:" + queueName +
            ", 处理Handler:" + handler.getClass().getName() +
            ", 当前队列大小：" + currentQueueSize;
        LOGGER.warn(alertMessage);
        emailAlert(handler, alertMessage);

        if (handler.isExceptionWhenQueueFull()) {
            throw new BizzException(SysEx.ASYNC_QUEUE_EXECUTOR_FULL, "[asyncQueueExecutor] 队列已满! 队列名:" + queueName +
                    ", 处理Handler:" + handler.getClass().getName() +
                    ", 当前队列大小：" + currentQueueSize);
        }
    }

    @Override
    public void onAlert(AbstractAsyncQueuedHandler handler, String queueName, int currentQueueSize) {
        String alertMessage = "[asyncQueueExecutor] 队列深度超过阈值! 队列名:" + queueName +
            ", 处理Handler:" + handler.getClass().getName() +
            ", 当前队列大小：" + currentQueueSize +
            ", 阈值：" + handler.getAlertQueueThreshold();
        LOGGER.warn(alertMessage);
        emailAlert(handler, alertMessage);
    }

    private void emailAlert(AbstractAsyncQueuedHandler handler, String message) {
//        boolean isEmailAlert = handler.isEmailAlert();
//        if (isEmailAlert) {
//            sendEmail.sendEmail("asyncQueueExecutor队列溢出警告", message, handler.getEmailTo(), null);
//        }
    }
}
