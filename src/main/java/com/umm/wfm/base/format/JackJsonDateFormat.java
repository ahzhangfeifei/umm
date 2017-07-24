package com.umm.wfm.base.format;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期解析yyyy-MM-dd
 *
 * @author baiguangcheng
 * @version Aug 1, 2016 4:04:55 PM
 */
public class JackJsonDateFormat extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        String formattedDate = "";
        if (value != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = formatter.format(value);
        }
        jgen.writeString(formattedDate);
    }

}
