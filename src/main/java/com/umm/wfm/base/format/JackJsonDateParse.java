package com.umm.wfm.base.format;

import com.umm.wfm.security.log.LogUtil;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换yyyy-MM-dd
 *
 * @author baiguangcheng
 * @version Aug 1, 2016 4:12:22 PM
 */
public class JackJsonDateParse extends JsonDeserializer<Date> {
    private static final Logger LOGGER = LogUtil.getLogger(JackJsonDateParse.class);

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = jp.getText();
        if (date == null || date.startsWith("0000") || date.trim().length() == 0) {
            return null;
        }
        try {
            return format.parse(date);
        } catch (Exception e) {
            LOGGER.error("date parse Error.", e);
        }
        return null;
    }

}