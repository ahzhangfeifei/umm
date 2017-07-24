package com.umm.wfm.service.res;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The type Active time config.
 */
@Component
public class ActiveTimeConfig {

    @Value("${activeTime:172800}")
    private int activeTime;

    @Value("${noActiveTime:43200}")
    private int noActiveTime;

    /**
     * Gets cache time.
     *
     * @param type the type
     * @return the cache time
     */
    public int getCacheTime(int type) {
        int seconds = 0;
        switch (type) {
            case 0:
                seconds = activeTime;
            case 1:
                seconds = noActiveTime;
            default:
                seconds = noActiveTime;
        }
        return seconds;
    }

}
