package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * create with Intellij IDEA
 *
 * @Auther:xiaohu
 * @Date: 2021/12/02/16:31
 * @Description:
 */
public class TimeStampInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        //将日志拦下来获取header头
        Map<String, String> headers = event.getHeaders();
        //获取bodyts
        byte[] body = event.getBody();
        String log = new String(body, StandardCharsets.UTF_8);
        String ts = JSONObject.parseObject(log).getString("ts");
        //赋值给时间戳
        headers.put("timestamp", ts);

        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    @Override
    public void close() {

    }
    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new TimeStampInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
