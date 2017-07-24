package com.umm.wfm.utils;

import com.umm.ats.core.conf.BaseSystemConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

/**
 * spring 配置服务
 *
 * @author zhangff
 */
@ManagedResource(objectName= "com.umm.ats:type=StaticAndConfig,name=SystemConfig" , description= "配置项目" )
@Service
public class SystemConfig extends BaseSystemConfig {

    @Value("${sample.flag:true}")
    private boolean sampleFlag;

    @ManagedAttribute
    public boolean isSampleFlag() {
        return sampleFlag;
    }

    public void setSampleFlag(boolean sampleFlag) {
        this.sampleFlag = sampleFlag;
    }
}
