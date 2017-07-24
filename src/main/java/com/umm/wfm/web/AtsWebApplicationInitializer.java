package com.umm.wfm.web;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.umm.ats.core.tracer.datashare.DataSharedFilter;
import com.umm.wfm.security.log.LogUtil;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Properties;

public class AtsWebApplicationInitializer implements WebApplicationInitializer {

    private static final Logger LOGGER = LogUtil.getLogger(AtsWebApplicationInitializer.class);

    @Override
    public void onStartup(ServletContext container) {
        customInit();

        container.setInitParameter("contextConfigLocation", "classpath*:spring/spring-*.xml");
        container.setInitParameter("contextInitializerClasses",
                "com.umm.ats.core.framework.ContextProfileInitializer, "
                        + "com.umm.zkconfig.client.boot.ConfigPrepareApplicationContextInitializer");

        // hystrix stream
        ServletRegistration.Dynamic hystrixMetricsStreamServlet = container.addServlet(
                "hystrixMetricsStreamServlet", new HystrixMetricsStreamServlet());
        hystrixMetricsStreamServlet.addMapping("/hystrix.stream");

        // spring dispatcher
        ServletRegistration.Dynamic springDispatcher = container.addServlet(
                "dispatcher", new DispatcherServlet());
        springDispatcher.setInitParameter("contextConfigLocation",
                "classpath:dispatcher-servlet.xml");
        springDispatcher.setLoadOnStartup(1);
        springDispatcher.addMapping("/");

        // listener
        container.addListener(ContextLoaderListener.class);

        // thread data share filter
        container.addFilter("DataSharedFilter", DataSharedFilter.class)
                .addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST),
                        true, "dispatcher");

        // character encoding filter
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        container.addFilter("CharacterEncodingFilter", encodingFilter)
                .addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST),
                        true, "dispatcher");

        // shiro filter for CAS
        DelegatingFilterProxy proxy = new DelegatingFilterProxy("shiroFilter");
        proxy.setTargetFilterLifecycle(true);
//        container.addFilter("shiroFilter", proxy)
//                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/manage/*");
        container.addFilter("shiroFilter", proxy)
                .addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST),
                        true, "dispatcher");
    }

    /**
     * 自定义初始化
     */
    private void customInit() {
        // 载入logback配置
        initLogger();

        // convertUtils register
        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new ShortConverter(null), Short.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);

        // fast json default setting
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
    }


    /**
     * 载入logback配置
     */
    private void initLogger() {
        String logbackConfigLocation;
        try {
            Resource resourceSetting = new ClassPathResource("main-setting.properties");
            Properties properties = PropertiesLoaderUtils.loadProperties(resourceSetting);
            logbackConfigLocation = "log/logback_" + properties.getProperty("application.envName") + ".xml";
        } catch (Exception ex) {
            throw new RuntimeException("can't loading main-setting.properties file from classpath.", ex);
        }

        try {
            Resource resource = new ClassPathResource(logbackConfigLocation);
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            loggerContext.reset();
            JoranConfigurator joranConfigurator = new JoranConfigurator();
            joranConfigurator.setContext(loggerContext);
            joranConfigurator.doConfigure(resource.getInputStream());
            LOGGER.info("logback configure file loaded. filePath={}", resource.getURI());
        } catch (Exception e) {
            LOGGER.error("can't loading logback configure file. classpath:" + logbackConfigLocation, e);
        }
    }
}