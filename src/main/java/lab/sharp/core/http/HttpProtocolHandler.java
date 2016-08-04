package lab.sharp.core.http;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;



 /* *
  *类名：HttpProtocolHandler
  *功能：Http方式访问
  *详细：获取远程HTTP数据
 * 创建时间: 2016年8月4日 下午3:02:22
 * 创建人： 邢凌霄
 * 版本： 1.0
 * @since JDK 1.5
 */
public class HttpProtocolHandler {

    private static String              DEFAULT_CHARSET                     = "GBK";
    
    /** 连接超时时间，由bean factory设置，缺省为8秒钟 */
    private int                        defaultConnectionTimeout            = 2000;

    /** 回应超时时间, 由bean factory设置，缺省为30秒钟 */
    private int                        defaultSoTimeout                    = 10000;

    private int                        defaultMaxConnPerHost               = 30;

    private int                        defaultMaxTotalConn                 = 80;

    /** 默认等待HttpConnectionManager返回连接超时（只有在达到最大连接数时起作用）：1秒*/
    private static final long          defaultHttpConnectionManagerTimeout = 3 * 1000;

    /**
     * HTTP连接管理器，该连接管理器必须是线程安全的.
     */
    private HttpConnectionManager      connectionManager;

    private static HttpProtocolHandler httpProtocolHandler                 = new HttpProtocolHandler();

    /**
     * 工厂方法
     * 
     * @return
     */
    public static HttpProtocolHandler getInstance() {
        return httpProtocolHandler;
    }

    /**
     * 私有的构造方法
     */
    private HttpProtocolHandler() {
        // 创建一个线程安全的HTTP连接池
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxConnPerHost);
        connectionManager.getParams().setMaxTotalConnections(defaultMaxTotalConn);
    }
    /**
     * 方法名称：execute
     * 方法描述： 执行Http请求
     * 创建人： 邢凌霄
     * 创建时间：2016年8月4日 下午3:35:43
     * @param request 请求数据
     * @return
     * @throws HttpException
     * @throws IOException
     * @since JDK 1.5
     */
    public HttpResponse execute(HttpRequest request) throws HttpException, IOException {
        HttpClient httpclient = new HttpClient(connectionManager);

        // 设置连接超时
        int connectionTimeout = defaultConnectionTimeout;
        if (request.getConnectionTimeout() > 0) {
            connectionTimeout = request.getConnectionTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);

        // 设置回应超时
        int soTimeout = defaultSoTimeout;
        if (request.getTimeout() > 0) {
            soTimeout = request.getTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

        // 设置等待ConnectionManager释放connection的时间
        httpclient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);

        String charset = request.getCharset();
        charset = charset == null ? DEFAULT_CHARSET : charset;
        PostMethod method = null;

    	//post模式
        method = new PostMethod(request.getUrl());
        for (int i = 0; i < request.getParameters().length; i++) {
        	method.addParameter(request.getParameters()[i].getName(), request.getParameters()[i].getValue());
        }
        HttpMethodParams param = method.getParams();  
        param.setContentCharset(charset);  

        // 设置Http Header中的User-Agent属性
        HttpResponse response = new HttpResponse();

        try {
        	
            httpclient.executeMethod(method);
            response.setStringResult(method.getResponseBodyAsString());
            response.setResponseHeaders(method.getResponseHeaders());
        } catch (UnknownHostException ex) {
        	System.out.println("发送失败，请检查网络连接是否畅通....................");
            return null;
        } catch (IOException ex) {

            return null;
        } catch (Exception ex) {

            return null;
        } finally {
            method.releaseConnection();
        }
        return response;
    }


}
