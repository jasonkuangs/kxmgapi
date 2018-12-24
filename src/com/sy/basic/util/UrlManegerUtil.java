package com.sy.basic.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 路径工具类
 * @author xiangchao
 * @date 2015年7月10日 上午10:50:54
 */
public class UrlManegerUtil {
    /**
     * 当前项目的物理路径
     * @param request
     * @return String as "F\:/tomcat/webapps/appplate/"
     */
    public static String getServerPath(HttpServletRequest request) {
	return request.getSession().getServletContext().getRealPath("/");
    }

    /**
     * 当前项目的web路径
     * @param request
     * @return String as "http://localhost:8080/appplate/"
     */
    public static String getWebUrl(HttpServletRequest request) {
	return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath() + "/";
    }

    /**
     * 物理路径
     * @param request
     * @param dirName web服务器里的目录名
     * @return String as "F\:/tomcat/webapps/${dirName}"
     */
    public static String getServerPath(HttpServletRequest request, String dirName) {
	return request.getSession().getServletContext().getRealPath("/") + "../" + dirName;
    }

    /**
     * web路径
     * @param request
     * @param dirName web服务器里的目录名
     * @return String as "http://localhost:8080/${dirName}"
     */
    public static String getWebUrl(HttpServletRequest request, String dirName) {
	return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + dirName;
    }
}
