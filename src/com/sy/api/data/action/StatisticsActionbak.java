package com.sy.api.data.action;

import com.google.gson.Gson;
import com.sy.basic.action.BaseAction;
import com.sy.basic.util.JsonUtil;
import com.sy.basic.util.NewKafkaSendUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

@Namespace("/statistics")
@Results({ @Result(name = "string", type = "stringResult", params = { "string",
		"resultStr" }) })
public class StatisticsActionbak extends BaseAction {

	private static final long serialVersionUID = 9054547164004444650L;
	private static Logger logger = Logger.getLogger(StatisticsAction.class);

	private final static String STRING = "string";

	public String getCurrent_system() {
		return current_system;
	}

	public void setCurrent_system(String current_system) {
		this.current_system = current_system;
	}

	public String getDown_load_num() {
		return down_load_num;
	}

	public void setDown_load_num(String down_load_num) {
		this.down_load_num = down_load_num;
	}

	public String getStart_up_num() {
		return start_up_num;
	}

	public void setStart_up_num(String start_up_num) {
		this.start_up_num = start_up_num;
	}

	public String getInstall_num() {
		return install_num;
	}

	public void setInstall_num(String install_num) {
		this.install_num = install_num;
	}

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getCrash_num() {
		return crash_num;
	}

	public void setCrash_num(String crash_num) {
		this.crash_num = crash_num;
	}

	public String getNow_date() {
		return now_date;
	}

	public void setNow_date(String now_date) {
		this.now_date = now_date;
	}

	private String current_system;
	private String down_load_num;
	private String start_up_num;
	private String install_num;
	private String mac_address;
	private String crash_num;
	private String now_date;
	

	/**
	 * 升级测试
	 * 
	 * @return
	 */
	@Action("save")
	public String save() {
		logger.info("====[save begin]====");
		logger.info("ip_address: " + getRemoteAddress());
		logger.info("current_system:" + current_system);
		logger.info("down_load_num:" + down_load_num);
		logger.info("start_up_num:" + start_up_num);
		logger.info("install_num:" + install_num);
		logger.info("mac_address:" + mac_address);
		logger.info("crash_num:" + crash_num);
		logger.info("now_date:" + now_date);
		logger.info("====[save end]====");
		// 保存入数据库，这里边为了性能保存在日志中，
		return STRING;
	}

	private String report_type;
	private String visit_time;
	private String visitor_type;
	private String user_id;
	private String account_name;
	private String phone_number;
	private String os;
	private String browser;
	private String app_version;
	private String visit_session_id;
	private String visit_url;
	private String detail;
	private String duration;// 持续时长，播放上报或者退出上报的时候可携带
	private String video_id;
	
	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public String getVisit_session_id() {
		return visit_session_id;
	}

	public void setVisit_session_id(String visit_session_id) {
		this.visit_session_id = visit_session_id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	private String getRemoteAddress() {
		String tmpret = "";
		try {

			tmpret = this.getRequest().getHeader("X-Forwarded-For");
			if (tmpret == null || tmpret.equals(""))
				tmpret = this.getRequest().getRemoteAddr();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return tmpret;
	}

	public String getReport_type() {
		return report_type;
	}

	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}

	public String getVisit_time() {
		return visit_time;
	}

	public void setVisit_time(String visit_time) {
		this.visit_time = visit_time;
	}

	public String getVisitor_type() {
		return visitor_type;
	}

	public void setVisitor_type(String visitor_type) {
		this.visitor_type = visitor_type;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	public String getVisit_url() {
		return visit_url;
	}

	public void setVisit_url(String visit_url) {
		this.visit_url = visit_url;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Action("report")
	public String report() {

		String systemTime = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		StringBuffer tmpbuffer = new StringBuffer();
		/*
		 * logger.info("====[report begin]====");
		 * logger.info("ip_address: "+getRemoteAddress());
		 * logger.info("sys_time: "+systemTime);
		 * logger.info("report_type: "+report_type);
		 * logger.info("visit_time: "+visit_time);
		 * logger.info("visitor_type: "+visitor_type);
		 * logger.info("user_id: "+user_id);
		 * logger.info("account_name: "+account_name);
		 * logger.info("phone_number: "+phone_number); logger.info("os: "+os);
		 * logger.info("browser: "+browser);
		 * logger.info("mac_address: "+mac_address);
		 * logger.info("app_version: "+app_version);
		 * logger.info("visit_session_id: "+visit_session_id);
		 * logger.info("visit_url: "+visit_url); logger.info("detail: "+detail);
		 * logger.info("====[report end]====");
		 */
		String tmpremoteip = getRemoteAddress();
		tmpbuffer.append("====[report begin]====").append("\r\n")
				.append("ip_address: " + tmpremoteip).append("\r\n")
				.append("sys_time: " + systemTime).append("\r\n")
				.append("report_type: " + report_type).append("\r\n")
				.append("visit_time: " + visit_time).append("\r\n")
				.append("visitor_type: " + visitor_type).append("\r\n")
				.append("user_id: " + user_id).append("\r\n")
				.append("account_name: " + account_name).append("\r\n")
				.append("phone_number: " + phone_number).append("\r\n")
				.append("os: " + os).append("\r\n")
				.append("browser: " + browser).append("\r\n")
				.append("mac_address: " + mac_address).append("\r\n")
				.append("app_version: " + app_version).append("\r\n")
				.append("visit_session_id: " + visit_session_id).append("\r\n")
				.append("duration: ").append(duration == null ? "0" : duration).append("\r\n")
				.append("video_id: ").append(video_id == null ? "" : video_id).append("\r\n")
				.append("visit_url: " + visit_url).append("\r\n")
				.append("detail: " + detail).append("\r\n")
				.append("====[report end]====").append("\r\n");
		logger.info(tmpbuffer.toString());
		try {
			if ("true".equals(JsonUtil.getProFile().get("kafka.notify"))) {
				Map tmpmap = new Hashtable();
				tmpmap.put("ip_address", tmpremoteip);
				tmpmap.put("sys_time", systemTime);
				tmpmap.put("report_type", report_type == null ? "0"
						: report_type);
				tmpmap.put("visit_time", visit_time == null ? "" : visit_time);
				tmpmap.put("visitor_type", visitor_type == null ? ""
						: visitor_type);
				tmpmap.put("user_id", user_id == null ? "" : user_id);
				tmpmap.put("account_name", account_name == null ? ""
						: account_name);
				tmpmap.put("phone_number", phone_number == null ? ""
						: phone_number);
				tmpmap.put("os", os == null ? "" : os);
				tmpmap.put("browser", browser == null ? "" : browser);
				tmpmap.put("mac_address", mac_address == null ? ""
						: mac_address);
				tmpmap.put("app_version", app_version == null ? ""
						: app_version);
				tmpmap.put("visit_session_id", visit_session_id == null ? ""
						: visit_session_id);
				tmpmap.put("visit_url", visit_url == null ? "" : visit_url);
				tmpmap.put("duration", duration == null ? "0" : duration);				
				tmpmap.put("video_id", video_id == null ? "" : video_id);
				tmpmap.put("detail", detail == null ? "" : detail);
				Gson tmpjson = new Gson();
				String jsonStr = tmpjson.toJson(tmpmap); // toJson
				if (Integer.parseInt(duration) <=10800) {
					NewKafkaSendUtil.getInstance().sendMessage(jsonStr);
					logger.info(jsonStr);
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return STRING;
	}

}
