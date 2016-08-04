package lab.sharp.core.http;

import org.apache.commons.httpclient.NameValuePair;


 /* *
  *类名：HttpRequest
  *功能：Http请求对象的封装
  *详细：封装Http请求
 * 创建时间: 2016年8月4日 下午3:27:10
 * 创建人： 邢凌霄
 * 版本： 1.0
 * @since JDK 1.5
 */
public class HttpRequest {

    /** HTTP GET method */
    public static final String METHOD_GET        = "GET";

    /** HTTP POST method */
    public static final String METHOD_POST       = "POST";
    /**
     * 待请求的url
     */
    private String             url               = null;

    /**
     * 默认的请求方式
     */
    private String             method            = METHOD_POST;

    private int                timeout           = 0;

    private int                connectionTimeout = 0;

    /**
     * Post方式请求时组装好的参数值对
     */
    private NameValuePair[]    parameters        = null;

    /**
     * 默认的请求编码方式
     */
    private String             charset           = "";



    public NameValuePair[] getParameters() {
        return parameters;
    }

    public void setParameters(NameValuePair[] parameters) {
        this.parameters = parameters;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * @return Returns the charset.
     */
    public String getCharset() {
        return charset;
    }

    /**
     * @param charset The charset to set.
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }


}
