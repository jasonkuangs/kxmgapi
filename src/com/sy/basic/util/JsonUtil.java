package com.sy.basic.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import net.sf.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class JsonUtil {

	private static ObjectMapper objectMapper = null;

	static {
		objectMapper = new ObjectMapper();
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		// objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		// 设置序列化是默认值和null不进行序列化。
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		// objectMapper.setSerializationInclusion(Include.NON_DEFAULT);
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * 直接序列化不过滤字段
	 * 
	 * @param value
	 *            待序列化的实体对象
	 * @return
	 */
	public static String jsonSerialization(Object value) {

		try {
			return objectMapper.writeValueAsString(value);
		} catch (Exception e) {
			throw new RuntimeException("解析对象错误");
		}
	}

	/**
	 * 设置过滤字段的序列化
	 * 
	 * @param value
	 *            待序列化的实体对象
	 * @param filterName
	 *            过滤器名
	 * @param properties
	 *            过滤的实体属性
	 * @return
	 */
	public static String jsonSerialization(Object value, String filterName, String... properties) {

		try {
			return filter(filterName, properties).writeValueAsString(value);
		} catch (Exception e) {
			throw new RuntimeException("解析对象错误");
		}
	}

	public static <T> T jsonDeserialization(String json, Class<?> clazz) {
		try {
			return (T) objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException("反序列化对象错误");
		}
	}

	private static ObjectMapper filter(String filterName, String... properties) {

		FilterProvider filterProvider = new SimpleFilterProvider().addFilter(filterName, SimpleBeanPropertyFilter.serializeAllExcept(properties));
		objectMapper.setFilters(filterProvider);

		return objectMapper;
	}

	/**
	 * 复杂类型json反序列化 ,比如List,Map
	 * 
	 * @param json
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 */
	public static <T> T jsonDeserialization(String json, Class<?> collectionClass, Class<?>... elementClasses) {
		try {
			JavaType javaType = getJavaType(collectionClass, elementClasses);
			return objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			throw new RuntimeException("反序列化对象错误");
		}
	}

	private static JavaType getJavaType(Class<?> collectionClass, Class<?>... elementClasses) {
		return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	public static void main(String[] args) throws ParseException {

		// getProFile();
		//getUserJSONData("orYjxjp-Lst2ekapKWMTZ9fxdlwE");

		// String privateRoleList = "1,2,3";
		// List<String> tempList = Arrays.asList(privateRoleList.split(","));
		// List<String> menuList = new ArrayList<String>(tempList);
		// System.out.println(menuList.remove("0"));

		/*
		 * String privateRoleList = "1,2,3"; List<String> tempList = Arrays.asList(privateRoleList.split(",")); List<String> menuList = new ArrayList<String>(tempList);
		 * System.out.println(menuList.remove("0")); Map<String,Object> paramMap = new HashMap<String,Object>(); paramMap.put("dArg1", 1); paramMap.put("dArg2", "007");
		 * paramMap.put("dArg3", "高富帅"); List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(); list.add(paramMap); String json = jsonSerialization(list);
		 * List<Map<String,Object>> list2 = jsonDeserialization(json, ArrayList.class, Demo.class); System.out.println(list2.size());
		 */
		
//		String postData = "{\"userGroup\": \"userGroup1\",\"manufacturer\": \"manufacturer1\",\"firewareVer\": \"firewareVer1\",\"areaId\": \"areaId1\",\"account\": \"account1\",\"AREID\": \"\",\"AppInfo\": [{\"packageName\": \"com.android.smart.terminal.settings\",\"port\": [\"48376\",\"52\",\"809\"]},{\"packageName\": \"com.android.smart.terminal.settings\",\"port\": [\"38388\"]},{\"packageName\": \"com.android.smart.terminal.settings\", \"port\": [\"5555\"]}],\"Mac\": \"70E45A265CFB\",\"Type\": \"Type1\"}";
//		JSONObject json = JSONObject.fromObject(postData);
//		TestaInfo pb = new TestaInfo();
//		System.out.println("account="+json.getString("account"));
//		System.out.println("areaId="+json.getString("areaId"));
//		System.out.println("firewareVer="+json.getString("firewareVer"));
//		System.out.println("Mac="+json.getString("Mac"));
//		System.out.println("manufacturer="+json.getString("manufacturer"));
//		System.out.println("Type="+json.getString("Type"));
//		System.out.println("userGroup="+json.getString("userGroup"));
//		
		
//		while (ite.hasNext()) {
//		String key = (String) ite.next();
//			System.out.println(key+"="+json.getString(key));	
//		}
		
	}

	public static JSONObject getUserJSONData(String userId) {
		String getURL = getProFile().getProperty("userUrl") + "&userId=" + userId;
		URL getUrl;
		BufferedReader reader = null;
		StringBuilder sb = null;
		HttpURLConnection connection = null;
		try {

			getUrl = new URL(getURL);
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.setConnectTimeout(1000);
			connection.setReadTimeout(2000);
			connection.connect();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			sb = new StringBuilder();
			String lines = "";
			while ((lines = reader.readLine()) != null) {
				sb.append(lines);
			}
			reader.close();
			connection.disconnect();

			JSONObject jsonData = JSONObject.fromObject(sb.toString());
			// System.out.println("===========nickname="+jsonData.getString("nickname"));
			return jsonData;
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("json:" + e.getMessage());
			try {
				reader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
			connection.disconnect();
		}
		return null;
	}

	public static Properties getProFile() {
		InputStream inputStream;
		inputStream = JsonUtil.class.getClassLoader().getResourceAsStream("config/system.properties");
		Properties p = new Properties();
		// System.out.println("inputStream=="+inputStream);
		try {
			p.load(inputStream);
			inputStream.close();
			return p;

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return null;
	}
	
	

}
