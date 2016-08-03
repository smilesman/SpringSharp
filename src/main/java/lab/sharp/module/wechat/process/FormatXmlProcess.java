/**
 * 工程名称:SpringSharp
 * 文件名称:FormatXmlProcess.java
 * 包名称： lab.sharp.module.wechat.process
 * 创建时间: 2016年8月3日下午5:21:14
 * Copyright (c) 2016, iSoftStone All Rights Reserved.
 *
*/
package lab.sharp.module.wechat.process;
import java.util.Date;
/**
 * 类名称: FormatXmlProcess
 * 类描述：封装最终的xml格式结果 
 * 创建时间: 2016年8月3日 下午5:21:21
 * 创建人： 邢凌霄
 * 版本： 1.0
 * @since JDK 1.5
 */
public class FormatXmlProcess {  
    /** 
     * 封装文字类的返回消息 
     * @param to 
     * @param from 
     * @param content 
     * @return 
     */  
    public String formatXmlAnswer(String to, String from, String content) {  
        StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(to);  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(from);  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(date.getTime());  
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");  
        sb.append(content);  
        sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");  
        return sb.toString();  
    }  
}  


