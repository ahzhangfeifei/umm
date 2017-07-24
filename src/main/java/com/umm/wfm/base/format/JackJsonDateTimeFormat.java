package com.umm.wfm.base.format;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换yyyy-MM-dd HH:mm:ss
 *
 * @author baiguangcheng
 * @version Aug 1, 2016 4:12:29 PM
 */
public class JackJsonDateTimeFormat extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        String formattedDate = "";
        if (value != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formattedDate = formatter.format(value);
        }
        jgen.writeString(formattedDate);
    }
}
