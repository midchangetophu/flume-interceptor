package com.atguigu.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

/**
 * create with Intellij IDEA
 *
 * @Auther:xiaohu
 * @Date: 2021/12/02/15:45
 * @Description:
 */
public class ETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 把数据拿出来 进行校验
        byte[] body = event.getBody();
        String log = new String(body, Charset.forName("UTF-8"));
        //校验
        if (JSONUtils.isValidate(log)){
            return event;
        }
        return null;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()){
            Event next = iterator.next();
            if (intercept(next) == null){
                iterator.remove();
            }
        }
        return events;
    }

    @Override
    public void close() {

    }
    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new ETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }

}
