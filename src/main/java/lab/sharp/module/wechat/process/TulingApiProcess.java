/**
 * 工程名称:SpringSharp
 * 文件名称:TulingApiProcess.java
 * 包名称： lab.sharp.module.wechat.process
 * 创建时间: 2016年8月3日下午5:19:31
 * Copyright (c) 2016, iSoftStone All Rights Reserved.
 *
*/
package lab.sharp.module.wechat.process;
import java.io.IOException;  
import java.io.UnsupportedEncodingException;  
import java.net.URLEncoder;  
  
import org.apache.http.HttpResponse;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.util.EntityUtils;  
import org.json.JSONException;  
import org.json.JSONObject;  
/**
 * 类名称: TulingApiProcess
 * 类描述：调用图灵机器人api接口，获取智能回复内容 
 * 创建时间: 2016年8月3日 下午5:19:39
 * 创建人： 邢凌霄
 * 版本： 1.0
 * @since JDK 1.5
 */
public class TulingApiProcess {  
    /** 
     * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果 
     * @param content 
     * @return 
     */  
    public String getTulingResult(String content){  
        /** 此处为图灵api接口，参数key需要自己去注册申请，先以11111111代替 */  
        String apiUrl = "http://www.tuling123.com/openapi/api?key=517f3dc33946496bbacb544b03b7e046&info=";  
        String param = "";  
        try {  
            param = apiUrl+URLEncoder.encode(content,"utf-8");  
        } catch (UnsupportedEncodingException e1) {  
            // TODO Auto-generated catch block  
            e1.printStackTrace();  
        } //将参数转为url编码  
          
        /** 发送httpget请求 */  
        HttpGet request = new HttpGet(param);  
        String result = "";  
        try {  
            HttpResponse response = HttpClients.createDefault().execute(request);  
            if(response.getStatusLine().getStatusCode()==200){  
                result = EntityUtils.toString(response.getEntity());  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        /** 请求失败处理 */  
        if(null==result){  
            return "对不起，你说的话真是太高深了……";  
        }  
          
        try {  
            JSONObject json = new JSONObject(result);  
            //以code=100000为例，参考图灵机器人api文档  
            if(100000==json.getInt("code")){  
                result = json.getString("text");  
            }  
        } catch (JSONException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return result;  
    }  
}  

