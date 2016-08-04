/**
 * 工程名称:http
 * 文件名称:StringUtils.java
 * 包名称： com.iss.http.xmlinstr.core.util
 * 创建时间: 2016年8月4日下午3:09:02
 * Copyright (c) 2016, iSoftStone All Rights Reserved.
 *
*/
package lab.sharp.core.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;

public class StringUtils {
    /**
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
	public static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }

        return nameValuePair;
    }
	
    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("D:\\" + "log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

