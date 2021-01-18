package com.sy.api.data.action;

import com.sy.basic.action.BaseAction;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts2.convention.annotation.*;

import java.io.IOException;
import java.net.URI;

/**
 * @ProjectName: MiguApi
 * @Package: com.sy.api.data.action
 * @ClassName: DownUrlAction
 * @Description: 获取节目下载地址
 * @Author: hingbox@163.com
 * @CreateDate: 2018/12/26 1:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/26 1:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@ParentPackage("xiif-default")
@Namespace("/api")
@Results({
        @Result(name = "json",type="json")
})

public class DownUrlAction extends BaseAction {
    private String uc;
    private String cid;
    private String userId;
    private String userToken;
    private String visitUrl;


    @Action(value="getDownUrl")
    public String getDownUrl() throws IOException {
//        String uc = reqMap.get("uc").toString();
//        String cid = reqMap.get("cid").toString();
//
//        String userId = reqMap.get("userId").toString();
//        String userToken = reqMap.get("userToken").toString();
//        String visitUrl = reqMap.get("visitUrl").toString();
        //首先需要先创建一个DefaultHttpClient的实例
        HttpClient httpClient = new DefaultHttpClient();
        //先创建一个HttpGet对象,传入目标的网络地址,然后调用HttpClient的execute()方法即可:
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(URI.create(visitUrl+"/playurl/v1/down/downurl?h265=false&contIds=" + cid + "&dolby=false&mt=10&nt=4&uc=" + uc + "&vr=false"));
        httpGet.addHeader("userId", userId);
        httpGet.addHeader("userToken", userToken);
        httpGet.addHeader("SDKCEId", "79acd784-cbbb-4cae-8778-8723e001164b");
        HttpResponse response = httpClient.execute(httpGet);

        String httpEntityContent = ResponseUtil.getHttpEntityContent(response);
        httpGet.abort();
        ResponseUtil.ajaxPrintByJson(httpEntityContent);
        ResponseUtil.responseOut(httpEntityContent);
        return "json";
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }
}