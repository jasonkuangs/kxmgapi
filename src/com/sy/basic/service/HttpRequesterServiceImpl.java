package com.sy.basic.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sy.basic.common.ServiceException;

/**
 * http请求
 * 
 * @author xp
 * @date 2015年11月11日 上午11:58:22
 */
@Service("httpRequesterService")
public class HttpRequesterServiceImpl {

	private static Logger logger = Logger
			.getLogger(HttpRequesterServiceImpl.class);
		
	private URL realUrl= null;
	private URLConnection connection= null;
		
	
	/**
	 * 发送get请求
	 * 
	 * @author xp
	 * @date 2015年11月11日 上午11:34:33
	 * @param url发送请求的
	 *            URL
	 * @param param请求参数
	 *            ，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 * @throws ServiceException
	 */
	public String sendPost(String url, String param) throws Exception {	
		PrintWriter out = null;
		BufferedReader in = null;		
		try {
			realUrl = new URL(url);
			// 打开和URL之间的连接
			connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setConnectTimeout(1);

			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(connection.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));	
//			String line;
//			 while ((line = in.readLine()) != null) {
//				System.out.println("=="+line);
//			}
			return in.readLine();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}								
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {				
				if (out != null) {
					out.close();
				}				
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}
	
}
