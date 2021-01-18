package com.sy.api.data.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sy.basic.action.BaseAction;
import org.apache.struts2.convention.annotation.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @ProjectName: MiguApi
 * @Package: com.sy.api.data.action
 * @ClassName: AdAction
 * @Description: 获取暂停广告
 * @Author: hingbox@163.com
 * @CreateDate: 2018/12/26 10:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/26 10:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@ParentPackage("xiif-default")
@Namespace("/api")
@Results({
        @Result(name = "json",type="json")
})
public class AdAction extends BaseAction {


    private String mac;
    private String pnumber;
    private String id;
    private String ua;
    @Action("ad")
    public String ad() {
        RestTemplate restTemplate = new RestTemplate();
//        String mac = reqMap.get("mac").toString() == "" ? "\"\"" : "\"" + reqMap.get("mac").toString() + "\"";//mac地址
//        String ua = reqMap.get("ua").toString() == "" ? "\"\"" : "\"" + reqMap.get("ua").toString() + "\"";//ua信息
//        String pnumber = reqMap.get("pnumber").toString() == "" ? "\"\"" : reqMap.get("pnumber").toString();
//        String id = reqMap.get("id").toString();//广告id,如果等于0，表示是贴片，如果等于1，表示是暂停
        if (isNotEmpty(id)) {
            if (id.equals("0")) {
                id = "0DAA898E58F35C1263D77E71B87E699D";
            } else {
                id = "14F7FD39E4C0E33D681ECD75F7B9B127";
            }
        } else {
            CheckObject c = new CheckObject("101", "广告ID不能为空!");
            String json = JSON.toJSON(c).toString();
            ResponseUtil.ajaxPrintByJson(json);
            ResponseUtil.responseOut(json);
            return "json";
        }
        if (isNotEmpty(ua)) {
            ua = ua.replaceAll(",",".").replaceAll(";",".").replaceAll("/",".").replaceAll(" ","");
            //System.out.println("ua"+ua);
        }
        String reqJsonStr = "{\"authid\":\"miguauthid\", \"token\":\"migutoken\",\"device\":{\"carrier\": 0,\"connectiontype\": 0,\"devicetype\": 1,\"did\": \"other\", \"didha\": 0, \"dpid\": \"other\",\"dpidha\": 0,\"mac\":" + mac + ",\"pnumber\":" + pnumber + ",\"ua\":" + ua + ",\"networktraffictype\":3}, \"imp\":[{\"actiontype\":1,\"id\":" + id + "}],\"app\":{\"networktraffictype\":3}}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(reqJsonStr, headers);
        ResponseEntity<Map> resp = restTemplate.exchange("http://ggx.cmvideo.cn/request/api10", HttpMethod.POST, entity, Map.class);
        System.out.println("打印信息"+resp.getBody());
        if (resp.getStatusCode() == HttpStatus.valueOf(204)) {
            CheckObject c = new CheckObject("204", "暂无广告内容!");
            String json = JSON.toJSON(c).toString();
            ResponseUtil.ajaxPrintByJson(json);
            ResponseUtil.responseOut(json);
            return "json";
        } else {
            JSONObject responseBody = new JSONObject(resp.getBody());
            ResponseUtil.ajaxPrintByJson(responseBody.toString());
            ResponseUtil.responseOut(responseBody.toString());
            return "json";
        }
    }

    public static boolean isNotEmpty(CharSequence string) {
        return string != null && string.length() > 0;
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
}