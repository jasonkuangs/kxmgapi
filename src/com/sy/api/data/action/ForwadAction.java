package com.sy.api.data.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sy.basic.action.BaseAction;
import com.sy.basic.util.JsonUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts2.convention.annotation.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URI;
import java.util.Map;

/**
 * @ProjectName: MiguApi
 * @Package: com.sy.api.data.action
 * @ClassName: ForwadAction
 * @Description: 转发接口
 * @Author: hingbox@163.com
 * @CreateDate: 2019/1/3 1:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/3 1:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@ParentPackage("xiif-default")
@Namespace("/forwardapi")
//@Results({
//        @Result(name = "json",type="json")
//})
@Results({ @Result(name = "string", type = "stringResult", params = { "string", "resultStr"}) })
public class ForwadAction extends BaseAction {
    //直播聊天要用到的字段
    private String userName;
    private String userId;
    private String roomId;

    //解决跨域要用的的字段
    private String callbackParameter="jsoncallback";
    private String jsoncallback="";
    private final static String STRING = "string";
    private String resultStr;

    //直播聊天室发消息字段
//    private String userId;
    private String msg;
//    private String roomId;
//    private String url;

    //暂停广告字段
    private String mac;
    private String pnumber;
    private String id;
    private String ua;

    //获取节目下载地址
    private String uc;
    private String cid;
//    private String userId;
    private String userToken;
    private static String visitDownUrl;
//    private String callbackParameter="jsoncallback";
//    private String jsoncallback="";
//    private final static String STRING = "string";
//    private String resultStr;

    private static String visitContentUrl;//
    private static String adVisitUrl;//
    private static String feedBackUrl;//
    private static String url;
    private String type;
    private String contents;
    private String mobile;
    private String version;
    private String uuid;

    /**
     * 直播 获取聊天webstocketUrl
     * @params 请求参数 userName,userId,roomId,url请求服务端地址
     * @return
     * @throws IOException
     */
    @Action("getWebStocketUrl")
    public String getWebStocketUrl() throws IOException {
        url = JsonUtil.getProFile().getProperty("talkVisitUrl");
        //首先需要先创建一个DefaultHttpClient的实例
        HttpClient httpClient = new DefaultHttpClient();
        //先创建一个HttpGet对象,传入目标的网络地址,然后调用HttpClient的execute()方法即可:
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(URI.create(url+"/chatlbs/enterRoom?userName="+userName+"&userId="+userId+"&type=102&level=105&roomNo="+roomId));
        HttpResponse response = httpClient.execute(httpGet);

        String httpEntityContent =getHttpEntityContent(response);
        httpGet.abort();
        resultStr = jsoncallback+"("+httpEntityContent+")";
        return STRING;
    }


    /**
     * 直播中聊天发消息
     * @params 请求参数 msg,userId,roomId,url请求服务端地址
     * @return
     * @throws IOException
     */
    @Action("sendMessage")
    public String sendMessage() throws IOException {
        url = JsonUtil.getProFile().getProperty("talkVisitUrl");
        //首先需要先创建一个DefaultHttpClient的实例
        HttpClient httpClient = new DefaultHttpClient();
        //先创建一个HttpGet对象,传入目标的网络地址,然后调用HttpClient的execute()方法即可:
        HttpPost httpPost = new HttpPost();
        httpPost.setURI(URI.create(url+"/chatrecv/receiveMessage?userId="+userId+"&msg="+msg+"&roomNo="+roomId));
        HttpResponse response = httpClient.execute(httpPost);

        String httpEntityContent = getHttpEntityContent(response);
        httpPost.abort();
        resultStr = jsoncallback+"("+httpEntityContent+")";
        return STRING;

    }


    /**
     * 暂停广告
     * @Params mac mac地址，ua 浏览器信息，pnumber 手机号;id广告标识 0 贴片广告，1 暂停广告
     * @return
     */
    @Action("ad")
    public String ad() {
        adVisitUrl  = JsonUtil.getProFile().getProperty("adVisitUrl");
        RestTemplate restTemplate = new RestTemplate();
        if (isNotEmpty(id)) {
            if (id.equals("0")) {
                id = "0DAA898E58F35C1263D77E71B87E699D";
            } else {
                id = "14F7FD39E4C0E33D681ECD75F7B9B127";
            }
        } else {
            CheckObject c = new CheckObject("101", "广告ID不能为空!");
            String json = JSON.toJSON(c).toString();
            resultStr = jsoncallback+"("+json+")";
            return STRING;
        }
        if(isNotEmpty(pnumber)) {

        }else{
            pnumber = null;
        }
        if(isNotEmpty(mac)) {

        }else{
            mac = null;
        }
        if (isNotEmpty(ua)) {
            ua = ua.replaceAll(",",".").replaceAll(";",".").replaceAll("/",".").replaceAll(" ","");
        }else {
            ua =null;
        }
        String reqJsonStr = "{\"authid\":\"miguauthid\", \"token\":\"migutoken\",\"device\":{\"carrier\": 0,\"connectiontype\": 0,\"devicetype\": 1,\"did\": \"other\", \"didha\": 0, \"dpid\": \"other\",\"dpidha\": 0,\"mac\":\"" + mac + "\",\"pnumber\":\"" + pnumber + "\",\"ua\":\"" + ua + "\",\"networktraffictype\":3}, \"imp\":[{\"actiontype\":1,\"id\":\"" + id + "\"}],\"app\":{\"networktraffictype\":3}}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(reqJsonStr, headers);
        ResponseEntity<Map> resp = restTemplate.exchange(adVisitUrl+"/request/api10", HttpMethod.POST, entity, Map.class);
        System.out.println("打印信息"+resp.getBody());
        if (resp.getStatusCode() == HttpStatus.valueOf(204)) {
            CheckObject c = new CheckObject("204", "暂无广告内容!");
            String json = JSON.toJSON(c).toString();
            resultStr = jsoncallback+"("+json+")";
            return STRING;
        } else {
            JSONObject responseBody = new JSONObject(resp.getBody());
            resultStr = jsoncallback+"("+responseBody.toString()+")";
            return STRING;
        }
    }

    /**
     * 获取节目下载地址
     * @return
     * @throws IOException
     */
    @Action(value="getDownUrl")
    public String getDownUrl() throws IOException {
        visitDownUrl = JsonUtil.getProFile().getProperty("downVisitUrl");
        //首先需要先创建一个DefaultHttpClient的实例
        HttpClient httpClient = new DefaultHttpClient();
        //先创建一个HttpGet对象,传入目标的网络地址,然后调用HttpClient的execute()方法即可:
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(URI.create(visitDownUrl+"/playurl/v1/down/downurl?h265=false&contIds=" + cid + "&dolby=false&mt=10&nt=4&uc=" + uc + "&vr=false"));
        httpGet.addHeader("userId", userId);
        httpGet.addHeader("userToken", userToken);
        httpGet.addHeader("SDKCEId", "79acd784-cbbb-4cae-8778-8723e001164b");
        HttpResponse response = httpClient.execute(httpGet);

        String httpEntityContent = getHttpEntityContent(response);
        httpGet.abort();
        resultStr = jsoncallback+"("+httpEntityContent+")";
        return STRING;
    }

    /**
     * 获取节目列表 入参 cid
     * @return
     * @throws IOException
     */
    @Action(value="getContentInfo")
    public String getContentInfo() throws IOException {
        visitContentUrl = JsonUtil.getProFile().getProperty("wwwVisitUrl");
        //首先需要先创建一个DefaultHttpClient的实例
        HttpClient httpClient = new DefaultHttpClient();
        //先创建一个HttpGet对象,传入目标的网络地址,然后调用HttpClient的execute()方法即可:
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(URI.create(visitContentUrl+"/wap/resource/miguPC_client/data/detailData.jsp?cid=" + cid));
        HttpResponse response = httpClient.execute(httpGet);
        String httpEntityContent = getHttpEntityContent(response);
        httpGet.abort();
        resultStr = jsoncallback + "(" + httpEntityContent + ")";
        return STRING;
    }

    /**
     * 获取节目是否可以下载 1 表示可以下载 反之 不能下载
     * @return
     */
    @Action(value="getDownFlag")
    public String getDownFlag() {
        String downloadFlag = JsonUtil.getProFile().getProperty("downloadFlag");
        resultStr = jsoncallback+ "({\"download\":\""+downloadFlag+"\"})";
        return STRING;
    }

    /**
     * 意见反馈
     * @return
     * @throws IOException
     */
    @Action(value="feedback")
    public String feedback() throws IOException {
        feedBackUrl = JsonUtil.getProFile().getProperty("feedBackUrl");
        //首先需要先创建一个DefaultHttpClient的实例
        HttpClient httpClient = new DefaultHttpClient();
        //先创建一个HttpGet对象,传入目标的网络地址,然后调用HttpClient的execute()方法即可:
        HttpPost httpPost = new HttpPost();
        httpPost.setURI(URI.create(feedBackUrl+"/interaction/v2/feedback?type="+type+"&contents="+contents));
        httpPost.addHeader("mobile", mobile);
        httpPost.addHeader("channel", "pc");
        httpPost.addHeader("Phone-info", "pc||"+version);
        httpPost.addHeader("BUSS_ID", uuid);
        HttpResponse response = httpClient.execute(httpPost);

        String httpEntityContent = getHttpEntityContent(response);
        httpPost.abort();
        resultStr = jsoncallback+"("+httpEntityContent+")";
        return STRING;
    }

    /**
     * 获得响应HTTP实体内容
     * @param response
     * @return
     * @throws java.io.IOException
     * @throws java.io.UnsupportedEncodingException
     */
    public String getHttpEntityContent(org.apache.http.HttpResponse response) throws IOException, UnsupportedEncodingException {
        //通过HttpResponse 的getEntity()方法获取返回信息
        org.apache.http.HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line + "\n");
                line = br.readLine();
            }
            br.close();
            is.close();
            return sb.toString();
        }
        return "";
    }

    public static boolean isNotEmpty(CharSequence string) {
        return string != null && string.length() > 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getCallbackParameter() {
        return callbackParameter;
    }

    public void setCallbackParameter(String callbackParameter) {
        this.callbackParameter = callbackParameter;
    }

    public String getJsoncallback() {
        return jsoncallback;
    }

    public void setJsoncallback(String jsoncallback) {
        this.jsoncallback = jsoncallback;
    }

    public static String getSTRING() {
        return STRING;
    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String resultStr) {
        this.resultStr = resultStr;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    class CheckObject {
        private String code;
        private String message;
        private String resultcode;
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public CheckObject() {
        }

        public CheckObject(String code, String message) {
            this.code = code;
            this.message = message;

        }
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }









    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}