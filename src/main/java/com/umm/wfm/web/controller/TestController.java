package com.umm.wfm.web.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.umm.ats.core.tsp.client.rest.RestClient;
import com.umm.ats.core.tsp4server.TspBody;
import com.umm.ats.core.tsp4server.TspService;
import com.umm.operation.platform.tsg.base.core.utils.ResponseVo;
import com.umm.wfm.security.log.LogUtil;
import com.umm.wfm.utils.JsonUtil;
import org.slf4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zhangff on 2016/7/27.
 */

@Controller
@RequestMapping(value = "/test")
@Profile({"local", "cie", "sit"})
public class TestController {
    private static final Logger logger = LogUtil.getLogger(TestController.class);

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @TspService
    public ResponseVo testAdd(@TspBody("a") Integer a, @TspBody("b") Integer b) {
        ResponseVo vo = new ResponseVo();
        vo.setData(a + b);
        return vo;
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    @TspService
    public Object testJson() {
        ResponseVo vo = new ResponseVo();
        Map<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        vo.setData(JsonUtil.toString(map));
        return JsonUtil.toString(map);
    }

    @RequestMapping(value = "/c1", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackForConsumer", groupKey = "TestCommandGroup",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            })
    @TspService
    public Object c1() throws Exception {
        RestClient client = new RestClient("http://www.google.com");
        try {
            return client.execute();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }

//        TimeUnit.MILLISECONDS.sleep(1000);
//        return "Hello c1 " + " thread:" + Thread.currentThread().getName();
    }

    @RequestMapping(value = "/c2", method = RequestMethod.GET)
    @TspService
    public Object c2() {
        TestCommand command = new TestCommand("test-Fallback");
        return command.execute();
    }


    public Object fallbackForConsumer() {
        return "error when consumer call service!";
    }

}
