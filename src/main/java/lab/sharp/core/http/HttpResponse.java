package lab.sharp.core.http;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;


/* *
 * 类名：HttpResponse
 * 功能：Http返回对象的封装
 * 详细：封装Http返回信息
 * 创建时间: 2016年8月4日 下午3:27:30
 * 创建人： 邢凌霄
 * 版本： 1.0
 * @since JDK 1.5
 */
public class HttpResponse {

    /**
     * 返回中的Header信息
     */
    private Header[] responseHeaders;

    /**
     * String类型的result
     */
    private String   stringResult;


    public Header[] getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Header[] responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public String getStringResult(String charset) throws UnsupportedEncodingException {
        if (stringResult != null) {
            return stringResult;
        }
        return null;
    }

    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }

}
