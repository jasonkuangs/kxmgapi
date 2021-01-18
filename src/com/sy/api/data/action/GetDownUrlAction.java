package com.sy.api.data.action;

import com.sy.basic.action.BaseAction;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Namespace("/api")
@Results({ @Result(name = "string", type = "stringResult", params = { "string", "resultStr"}) })
public class GetDownUrlAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9054547164004444650L;
	private static Logger logger = Logger.getLogger(GetDownUrlAction.class);
	private final static String STRING = "string";	
	private String resultStr;		
	private String version;
	private static String resourceUrl;
	private static String resourceVersion;
	private String callbackParameter="jsoncallback";
	private String jsoncallback="";
	
	public String getJsoncallback() {
		return jsoncallback;
	}
	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = jsoncallback;
	}
	public String getCallbackParameter() {
		return callbackParameter;
	}
	public void setCallbackParameter(String callbackParameter) {
		this.callbackParameter = callbackParameter;
	}
	private String getRemoteAddress(){
		String tmpret = "";
		try{
			tmpret = this.getRequest().getHeader("X-Forwarded-For");
			if(tmpret==null||tmpret.equals(""))
				tmpret = this.getRequest().getRemoteAddr();
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return tmpret;
	}
	/**
	 * 升级测试
	 * 
	 * @return
	 */
	@Action("getD")
	public String getVer() {
		logger.info("====[getDownUrl]==== clientip:"+getRemoteAddress());
		//resourceUrl = JsonUtil.getProFile().getProperty("resourceUrl");
		//resourceVersion = JsonUtil.getProFile().getProperty("resourceVersion");
		resultStr=jsoncallback+"({\"code\":\"0\",\"message\":\"ok\"})";	
		return STRING;
	}
	
	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
}
