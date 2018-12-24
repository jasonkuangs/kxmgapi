package com.sy.basic.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sy.basic.obj.ResultJsonObject;

/**
 * 统一Action
 * @author xp
 * @date 2015年11月19日 上午10:09:29
 */
@ResultPath("/WEB-INF/jsp")
@ParentPackage("xiif-default")
@Results({ @Result(name = "success", type = "json", params = { "root", "result" }), // 成功
	@Result(name = "none", type = "json", params = { "root", "result" }), // 无结果
	@Result(name = "error", type = "json", params = { "root", "result" }), // 错误
	@Result(name = "json", type = "json", params = { "root", "result" }) }) // 数据
public class BaseAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

    /**
     * @author xp
     * @date 2015年8月19日 下午7:10:41
     */
    private static final long serialVersionUID = 8516369620938926881L;

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected ResultJsonObject<Object> result = new ResultJsonObject<Object>();// 分页对象

    @Override
    public void setServletRequest(HttpServletRequest request) {
	this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
	this.response = response;
	response.setContentType("text/html;charset=UTF-8");
    }

    public HttpServletRequest getRequest() {
	return request;
    }

    public HttpServletResponse getResponse() {
	return response;
    }

    /*
     * 获取数据 ***********************************************************
     */
    /**
     * 得到session
     * @return
     */
    public final HttpSession getSession() {
	return getRequest().getSession();
    }

    /**
     * 得到session中的值
     * @param attrKey
     * @return
     */
    public final Object getSessionAttr(String attrKey) {
	return getSession().getAttribute(attrKey);
    }

    /**
     * 得到请求参数
     * @param parmKey
     * @return
     */
    public final String getRequestParm(String parmKey) {
	return getRequest().getParameter(parmKey);
    }

    /**
     * 得到传递参数
     * @param attrKey
     * @return
     */
    public final Object getRequestAttr(String attrKey) {
	return getRequest().getAttribute(attrKey);
    }

    /**
     * 得到cookie中的数据
     * @return
     */
    public final Cookie[] getCookies() {
	return getRequest().getCookies();
    }

    /**
     * 得到cookie中的数据
     * @return
     */
    public final Map<String, String> getCookiesMap() {
	Map<String, String> result = new HashMap<String, String>();
	for (Cookie cookie : getCookies()) {
	    result.put(cookie.getName(), cookie.getValue());
	}
	return result;
    }

    /**
     * 得到请求中的流
     * @return
     * @throws IOException
     */
    public final InputStream getInputStream() throws IOException {
	return request.getInputStream();
    }

    /**
     * 得到客户端ip
     * @return
     */
    public final String getClientIp() {

	String ip = request.getHeader("x-forwarded-for");
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    ip = request.getHeader("Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    ip = request.getRemoteAddr();
	}
	return ip;
    }

    /**
     * 获取项目下文件路径
     * @param abstractPath
     *            相对路径
     * @return 绝对路径
     */
    protected String getWebRealPath(String abstractPath) {
	return getSession().getServletContext().getRealPath(abstractPath);
    }

    /*
     * 设置数据 ************************************************
     */
    /**
     * 返回script响应数据
     * @param msg
     */
    protected void responseScript(String msg) {
	StringBuilder rep = new StringBuilder("<script>" + msg + "</script>");
	try {
	    getResponse().setCharacterEncoding("UTF-8");
	    getResponse().getWriter().println(rep.toString());
	    getResponse().flushBuffer();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * 设置传递参数
     * @param attrKey
     *            参数名
     * @param attrValue
     *            传递的内容
     */
    public final void setRequestAttr(String attrKey, Object attrValue) {
	getRequest().setAttribute(attrKey, attrValue);
    }

    /**
     * 向cookie中添加一组数据
     * @param name
     *            cookie名
     * @param value
     *            cookie值
     * @param validity
     *            有效期以s为单位
     */
    public final void addCookie(String name, String value, int validity) {
	Cookie cookie = new Cookie(name, value);
	cookie.setPath("/");
	if (validity > 0) {
	    cookie.setMaxAge(validity);
	}
	getResponse().addCookie(cookie);
    }

    /**
     * 添加session
     * @param name
     * @param value
     */
    public final void addSession(String name, Object value) {
	getSession().setAttribute(name, value);
    }

    /**
     * 将消息返回页面
     * @param msg
     * @throws IOException
     */
    public void writeStr(String msg) throws IOException {
	getResponse().getWriter().write(msg);
    }

    /**
     * 写流数据
     * @param b
     * @throws IOException
     */
    public void writeOutStream(byte[] b) throws IOException {
	getResponse().getOutputStream().write(b);
    }

    /**
     * 将字符串以流的形式写入response
     * @param streamStr
     *            要写入输出流的字符串
     * @param charsetName
     *            指定编码格式
     * @throws IOException
     */
    public void writeOutStream(String streamStr, String charsetName) throws IOException {
	byte[] bSrc = null;
	if (charsetName == null || charsetName.equals("")) {
	    bSrc = streamStr.getBytes();
	} else {
	    bSrc = streamStr.getBytes(charsetName);
	}
	writeOutStream(bSrc);
    }

    /*
     * 数据瘦身 ***************************************
     */
    /**
     * 清空sessions
     * @param ignores
     *            清除过程中要忽略的session名
     */
    public final void clearSessions(List<String> ignores) {
	Enumeration<String> keys = getSession().getAttributeNames();
	String key;
	while (keys.hasMoreElements()) {
	    key = keys.nextElement();
	    if (ignores == null || !ignores.contains(key)) {
		getSession().removeAttribute(key);
	    }
	}
    }

    /**
     * 清除cookies中的cookie
     * @param ignores
     *            清除过程中要忽略的cookie名
     */
    public final void clearCookies(List<String> ignores) {
	for (Cookie cookie : getCookies()) {
	    if (ignores == null || !ignores.contains(cookie.getName())) {
		cookie.setValue(null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	    }
	}
    }

    /**
     * 清空cookie和session
     */
    public final void clearCache() {
	clearSessions(null);
	clearCookies(null);
    }

    public String notFound() {
	return "notFound";
    }

    public ResultJsonObject<Object> getResult() {
	return result;
    }
}
