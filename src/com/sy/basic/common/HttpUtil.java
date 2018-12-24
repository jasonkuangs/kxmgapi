package com.sy.basic.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class HttpUtil {
	public static void  httpPostUtil(String pathUrl, String content) {
		// Post请求的url，与get不同的是不需要带参数
		URL postUrl = null;
		try {
			postUrl = new URL(pathUrl);
		} catch (MalformedURLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		// 打开连接
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) postUrl.openConnection();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		// 设置是否向connection输出，因为这个是post请求，参数要放在
		// http正文内，因此需要设为true
		connection.setDoOutput(true);
		// Read from the connection. Default is true.
		connection.setDoInput(true);
		connection.setConnectTimeout(2000);
		connection.setReadTimeout(2000);
		
		// 默认是 GET方式
		try {
			connection.setRequestMethod("POST");
		} catch (ProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// Post 请求不能使用缓存
		connection.setUseCaches(false);
		
		connection.setInstanceFollowRedirects(true);

		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		// 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
		// 进行编码
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
		// 要注意的是connection.getOutputStream会隐含的进行connect。
		try {
			connection.connect();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			connection.disconnect();
			return;
		}
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(connection.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connection.disconnect();
			return;
		}

		// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
		try {
			out.writeBytes(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connection.disconnect();
			return;
		}

		try {
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connection.disconnect();
			return;
		}
		try {
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connection.disconnect();
			return;
		}

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			connection.disconnect();
			return;
		}
		String line;

		try {
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connection.disconnect();
			return;
		}

		try {
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connection.disconnect();
			return;
		}
		connection.disconnect();
	}
	
}
