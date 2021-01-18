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
public class StatisticsAction extends BaseAction {

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
	private String duration;// 持续时长，
	private String video_id;
	private String caton_reason;//卡顿原因
	private String error_code;//播放错误上报错误码

	/**
	 * 2021-01-08 新增
	 * @return
	 */
	private String rateType;//码率类型
	private String playSessionId;//播放会话ID (唯一播放会话标识) 【首次播放、切换码率】
	private String videoType;//视频类型vod：点播；live：直播;playback：回看
	private String downloadTrace;//ts下载轨迹
	private String httpHeader;//获取流地址请求头
	private String httpResponse;//获取流地址的响应
	private String loadTime;//获取播放地址所用时长
	private String MG_MSG_FFRAME_TIME;//首帧响应时长
	private String passbyDuration;//经过时长，单位毫秒如果是本次播放第一次上报心跳，则填写从播放开始到现在的经过时长 如果不是本次播放第一次上报心跳，则填写从上一次上报心跳到现在经过的时长
	private String serviceIp;//流地址域名对应的ip
	private String MG_MSG_STUCK_DURATION;//卡顿的持续时间
	private String MG_MSG_STUCK_START;//发生卡顿的开始时间
	private String MG_MSG_STUCK_END;//卡顿结束时间
	//private String Reason;//卡顿原因
	private String play_error_code;//播放异常数据上报 report_type=8
	private String client_error_message;//崩溃错误信息 崩溃错误信息 report_type=99


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

	public String getCaton_reason() {
		return caton_reason;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public void setCaton_reason(String caton_reason) {
		this.caton_reason = caton_reason;
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

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getPlaySessionId() {
		return playSessionId;
	}

	public void setPlaySessionId(String playSessionId) {
		this.playSessionId = playSessionId;
	}

	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}
//
//	public String getDataSource() {
//		return dataSource;
//	}
//
//	public void setDataSource(String dataSource) {
//		this.dataSource = dataSource;
//	}

	public String getDownloadTrace() {
		return downloadTrace;
	}

	public void setDownloadTrace(String downloadTrace) {
		this.downloadTrace = downloadTrace;
	}

	public String getHttpHeader() {
		return httpHeader;
	}

	public void setHttpHeader(String httpHeader) {
		this.httpHeader = httpHeader;
	}

	public String getHttpResponse() {
		return httpResponse;
	}

	public void setHttpResponse(String httpResponse) {
		this.httpResponse = httpResponse;
	}

	public String getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(String loadTime) {
		this.loadTime = loadTime;
	}

	public String getMG_MSG_FFRAME_TIME() {
		return MG_MSG_FFRAME_TIME;
	}

	public void setMG_MSG_FFRAME_TIME(String MG_MSG_FFRAME_TIME) {
		this.MG_MSG_FFRAME_TIME = MG_MSG_FFRAME_TIME;
	}

	public String getPassbyDuration() {
		return passbyDuration;
	}

	public void setPassbyDuration(String passbyDuration) {
		this.passbyDuration = passbyDuration;
	}

	public String getServiceIp() {
		return serviceIp;
	}

	public void setServiceIp(String serviceIp) {
		this.serviceIp = serviceIp;
	}

	public String getMG_MSG_STUCK_DURATION() {
		return MG_MSG_STUCK_DURATION;
	}

	public void setMG_MSG_STUCK_DURATION(String MG_MSG_STUCK_DURATION) {
		this.MG_MSG_STUCK_DURATION = MG_MSG_STUCK_DURATION;
	}

	public String getMG_MSG_STUCK_START() {
		return MG_MSG_STUCK_START;
	}

	public void setMG_MSG_STUCK_START(String MG_MSG_STUCK_START) {
		this.MG_MSG_STUCK_START = MG_MSG_STUCK_START;
	}

	public String getMG_MSG_STUCK_END() {
		return MG_MSG_STUCK_END;
	}

	public void setMG_MSG_STUCK_END(String MG_MSG_STUCK_END) {
		this.MG_MSG_STUCK_END = MG_MSG_STUCK_END;
	}

//	public String getReason() {
//		return Reason;
//	}
//
//	public void setReason(String reason) {
//		Reason = reason;
//	}

	public String getPlay_error_code() {
		return play_error_code;
	}

	public void setPlay_error_code(String play_error_code) {
		this.play_error_code = play_error_code;
	}

	public String getClient_error_message() {
		return client_error_message;
	}

	public void setClient_error_message(String client_error_message) {
		this.client_error_message = client_error_message;
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
				.append("visit_time: " + systemTime).append("\r\n")
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
				.append("caton_reason: " + caton_reason).append("\r\n")
				.append("error_code: " + error_code).append("\r\n")

				//2021-01-11新增字段

				.append("rateType: " + rateType).append("\r\n")
				.append("playSessionId: " + playSessionId).append("\r\n")
				.append("videoType: " + videoType).append("\r\n")
				.append("downloadTrace: " + downloadTrace).append("\r\n")
				.append("httpHeader: " +  httpHeader).append("\r\n")
				.append("httpResponse: " + httpResponse).append("\r\n")
				.append("loadTime: " + loadTime).append("\r\n")
				.append("MG_MSG_FFRAME_TIME: " + MG_MSG_FFRAME_TIME).append("\r\n")
				.append("passbyDuration: " + passbyDuration).append("\r\n")
				.append("serviceIp: " + serviceIp).append("\r\n")
				.append("MG_MSG_STUCK_DURATION: " + MG_MSG_STUCK_DURATION).append("\r\n")
				.append("MG_MSG_STUCK_START: " + MG_MSG_STUCK_START).append("\r\n")
				.append("MG_MSG_STUCK_END: " + MG_MSG_STUCK_END).append("\r\n")
				.append("play_error_code: " + play_error_code).append("\r\n")
				.append("client_error_message: " + client_error_message).append("\r\n")
				.append("====[report end]====").append("\r\n");
		logger.info(tmpbuffer.toString());
		try {
			if ("true".equals(JsonUtil.getProFile().get("kafka.notify"))) {
				Map tmpmap = new Hashtable();
				tmpmap.put("ip_address", tmpremoteip);
				tmpmap.put("sys_time", systemTime);
				tmpmap.put("report_type", report_type == null ? "0"
						: report_type);
				tmpmap.put("visit_time",systemTime);
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
				tmpmap.put("caton_reason", caton_reason == null ? "" : caton_reason);
				tmpmap.put("error_code", error_code == null ? "" : error_code);
				//2021-01-11新增字段
				tmpmap.put("rateType", rateType == null ? "" : rateType);
				tmpmap.put("playSessionId", playSessionId == null ? "" : playSessionId);
				tmpmap.put("videoType", videoType == null ? "" : videoType);
				tmpmap.put("downloadTrace", downloadTrace == null ? "" : downloadTrace);
				tmpmap.put("httpHeader", httpHeader == null ? "" : httpHeader);
				tmpmap.put("httpResponse", httpResponse == null ? "" : httpResponse);
				tmpmap.put("loadTime", loadTime == null ? "" : loadTime);
				tmpmap.put("MG_MSG_FFRAME_TIME", MG_MSG_FFRAME_TIME == null ? "" : MG_MSG_FFRAME_TIME);
				tmpmap.put("passbyDuration", passbyDuration == null ? "" : passbyDuration);
				tmpmap.put("serviceIp", serviceIp == null ? "" : serviceIp);
				tmpmap.put("MG_MSG_STUCK_DURATION", MG_MSG_STUCK_DURATION == null ? "" : MG_MSG_STUCK_DURATION);
				tmpmap.put("MG_MSG_STUCK_START", MG_MSG_STUCK_START == null ? "" : MG_MSG_STUCK_START);
				tmpmap.put("MG_MSG_STUCK_END", MG_MSG_STUCK_END == null ? "" : MG_MSG_STUCK_END);
				tmpmap.put("play_error_code", play_error_code == null ? "" : play_error_code);
				tmpmap.put("client_error_message", client_error_message == null ? "" : client_error_message);


				Gson tmpjson = new Gson();
				if (duration == null || duration.equals("")){
					duration = "0";
				}
				String jsonStr = tmpjson.toJson(tmpmap); // toJson
				try{
					if (Integer.parseInt(duration) <= 10800){
						NewKafkaSendUtil.getInstance().sendMessage(jsonStr);
						logger.info(jsonStr);
					}
				}catch(Exception e){
					logger.error(e.getMessage());
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return STRING;
	}

}
