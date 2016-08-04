/**
 * 工程名称:http
 * 文件名称:HttpSendHandler.java
 * 包名称： com.iss.http.xmlinstr.core.httpclient
 * 创建时间: 2016年8月4日下午3:38:43
 * Copyright (c) 2016, iSoftStone All Rights Reserved.
 *
*/
package lab.sharp.core.http;

import java.util.HashMap;
import java.util.Map;

import lab.sharp.core.util.StringUtils;

public class HttpSendHandler {
    private static String              DEFAULT_CHARSET                     = "GBK";
    
    private static String              REQUEST_KEY                         = "_USER_REQUEST_DATA";
    /**
     * 方法名称：sendRequest
     * 方法描述：建立请求，以模拟远程HTTP的POST请求方式构造并获取处理结果
     * 创建人： 邢凌霄
     * 创建时间：2016年8月4日 下午2:52:48
     * @param url 请求地址
     * @param message  请求报文
     * @param resultType
     * @return
     * @throws Exception
     * @since JDK 1.5
     */
    public String sendRequest(String url,String message) throws Exception {
    	
        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
        HttpRequest request = new HttpRequest();
        
        //设置编码集
        request.setCharset(DEFAULT_CHARSET);
        Map<String, String> sPara = new HashMap<String, String>();
        sPara.put(REQUEST_KEY, message);
        
        //转换参数
        request.setParameters(StringUtils.generatNameValuePair(sPara));
        request.setUrl(url);

        HttpResponse response = httpProtocolHandler.execute(request);
        if (response == null) {
        	System.out.println("无法捕捉回执信息！");
            return null;
        }
        String strResult = response.getStringResult(DEFAULT_CHARSET);
        return strResult;
    }
}

