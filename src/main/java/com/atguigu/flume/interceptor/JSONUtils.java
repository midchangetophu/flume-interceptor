package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

/**
 * create with Intellij IDEA
 *
 * @Auther:xiaohu
 * @Date: 2021/12/02/15:53
 * @Description:
 */
public class JSONUtils {
     /*public static void main(String[] args) {
         System.out.println(isValidate("{\"age\":18}"));
         }*/

    public static boolean isValidate(String log) {
        try {
            JSON.parse(log);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
}
