package com.umm.wfm.web.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.umm.ats.core.tsp.client.rest.RestClient;
import com.umm.wfm.security.log.LogUtil;
import org.slf4j.Logger;

/**
 * Created by makun on 2017/6/23.
 */
public class TestCommand extends HystrixCommand<String> {
    private static final Logger logger = LogUtil.getLogger(TestCommand.class);

    private final String name;

    public TestCommand(String name) {
//        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TestCommandGroup"))
//                /* 配置依赖超时时间,500毫秒*/
//                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(500)));
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TestCommandGroup")));
        this.name = name;
    }

    @Override
    protected String getFallback() {
        return "exeucute Falled";
    }

    @Override
    protected String run() throws Exception {
        //sleep 1 秒,调用会超时
//        TimeUnit.MILLISECONDS.sleep(1000);

//        return "Hello " + name + " thread:" + Thread.currentThread().getName();
        RestClient client = new RestClient("http://www.google.com");
        try {
            return client.execute().toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
