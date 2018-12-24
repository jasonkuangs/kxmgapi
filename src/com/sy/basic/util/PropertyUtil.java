package com.sy.basic.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 获取属性文件值工具
 * @author Administrator
 * @date 2016-8-11
 */
public class PropertyUtil extends PropertyPlaceholderConfigurer {

	public static Map<String, String> properties = new HashMap<String, String>();

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);

		// 将properties存入MAP
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			properties.put(keyStr, value);
		}
	}

	/**
	 * 获取属性文件的value
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return properties.get(key);
	}

}
