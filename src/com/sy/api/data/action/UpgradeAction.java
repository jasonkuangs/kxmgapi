package com.sy.api.data.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sy.basic.action.BaseAction;
import com.sy.basic.util.JsonUtil;

@Namespace("/upgrade")
@Results({ @Result(name = "string", type = "stringResult", params = { "string", "resultStr" }) })
public class UpgradeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9054547164004444650L;
	private static Logger logger = Logger.getLogger(UpgradeAction.class);
	private final static String STRING = "string";	
	private String resultStr;		
	private String version;
	private static String resourceUrl;
	private static String resourceVersion;
	
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
	@Action("getVer")
	public String getVer() {
		logger.info("====[getVer]====version:"+version +" clientip:"+getRemoteAddress());
		resourceUrl = JsonUtil.getProFile().getProperty("resourceUrl");
		resourceVersion = JsonUtil.getProFile().getProperty("resourceVersion");
		try {
			if(version.equalsIgnoreCase(resourceVersion)){
				resultStr="null";
			}else{
				resultStr=resourceUrl;//+"/download/Setup.exe";
			}
			return STRING;
		} catch (Exception e) {
			// TODO: handle exception
			return STRING;
		}
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
