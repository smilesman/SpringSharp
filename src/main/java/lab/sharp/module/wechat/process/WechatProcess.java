/**
 * 工程名称:SpringSharp
 * 文件名称:WechatProcess.java
 * 包名称： lab.sharp.module.wechat.process
 * 创建时间: 2016年8月3日下午5:16:08
 * Copyright (c) 2016, iSoftStone All Rights Reserved.
 *
*/
package lab.sharp.module.wechat.process;

import lab.sharp.module.wechat.entity.ReceiveXmlEntity;
import lab.sharp.module.wechat.entity.ReceiveXmlProcess;

/**
 * 类名称: WechatProcess
 * 类描述：微信xml消息处理流程逻辑类 
 * 创建时间: 2016年8月3日 下午5:16:11
 * 创建人： 邢凌霄
 * 版本： 1.0
 * @since JDK 1.5
 */
public class WechatProcess {  
    /** 
     * 解析处理xml、获取智能回复结果（通过图灵机器人api接口） 
     * @param xml 接收到的微信数据 
     * @return  最终的解析结果（xml格式数据） 
     */  
    public String processWechatMag(String xml){  
        /** 解析xml数据 */  
        ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);  
          
        /** 以文本消息为例，调用图灵机器人api接口，获取回复内容 */  
        String result = "";  
        if("text".endsWith(xmlEntity.getMsgType())){  
            result = new TulingApiProcess().getTulingResult(xmlEntity.getContent());  
        }  
          
        /** 此时，如果用户输入的是“你好”，在经过上面的过程之后，result为“你也好”类似的内容  
         *  因为最终回复给微信的也是xml格式的数据，所有需要将其封装为文本类型返回消息 
         * */  
        result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);  
          
        return result;  
    }  
}  

