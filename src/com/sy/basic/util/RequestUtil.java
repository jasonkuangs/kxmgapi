package com.sy.basic.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @desp
 * @author KOLER
 * @since 2014年8月25日 下午2:44:06
 */
public class RequestUtil {

    /**
     * 获取链接状态
     * @param url
     * @return
     * @throws IOException
     */
    public static RequestParam getStatus(URL url) {
	RequestParam param = new RequestParam();
	long start = System.currentTimeMillis();
	HttpURLConnection conn = null;
	try {
	    conn = getConn(url);
	    param.setCode(conn.getResponseCode());
	} catch (IOException e) {
	    System.out.println("地址" + url + "连接超时");
	}
	long end = System.currentTimeMillis();
	param.setTime((int) (end - start));
	return param;
    }

    public static RequestParam getStatus(String url) throws IOException {
	return getStatus(new URL(url));
    }

    /**
     * 得到连接
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getConn(URL url) throws IOException {
	return (HttpURLConnection) url.openConnection();
    }

    /**
     * 得到连接
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static HttpURLConnection getConn(String url) throws MalformedURLException, IOException {
	return getConn(new URL(url));
    }

    /**
     * 响应参数
     * @desp
     * @author KOLER
     * @since 2014年8月25日 下午2:50:06
     */
    public static class RequestParam {

	/**
	 * 响应码
	 */
	private int code;
	/**
	 * 响应时间
	 */
	private int time;

	public int getCode() {
	    return code;
	}

	public void setCode(int code) {
	    this.code = code;
	}

	public int getTime() {
	    return time;
	}

	public void setTime(int time) {
	    this.time = time;
	}

	public boolean isRunning() {

	    return code == 200 ? true : false;
	}
    }
}
