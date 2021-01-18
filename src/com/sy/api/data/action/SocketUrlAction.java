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
 * @ClassName: SocketUrlAction
 * @Description: 直播聊天室  获取socketUrl
 * @Author: hingbox@163.com
 * @CreateDate: 2018/12/26 11:21
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/26 11:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@ParentPackage("xiif-default")
@Namespace("/api")
@Results({
        @Result(name = "json",type="json")
})
public class SocketUrlAction extends BaseAction {

    private String userName;
    private String userId;
    private String roomId;
    private String url;

    @Action("getWebStocketUrl")
    public String getWebStocketUrl() throws IOException {
        //首先需要先创建一个DefaultHttpClient的实例
        HttpClient httpClient = new DefaultHttpClient();
        //先创建一个HttpGet对象,传入目标的网络地址,然后调用HttpClient的execute()方法即可:
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(URI.create(url+"/chatlbs/enterRoom?userName="+userName+"&userId="+userId+"&type=102&level=105&roomNo="+roomId));
        HttpResponse response = httpClient.execute(httpGet);

        String httpEntityContent = ResponseUtil.getHttpEntityContent(response);
        httpGet.abort();
        ResponseUtil.ajaxPrintByJson(httpEntityContent);
        ResponseUtil.responseOut(httpEntityContent);
        return "json";
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}