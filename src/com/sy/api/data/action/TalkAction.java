package com.sy.api.data.action;

import com.sy.basic.action.BaseAction;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts2.convention.annotation.*;

import java.io.IOException;
import java.net.URI;

/**
 * @ProjectName: MiguApi
 * @Package: com.sy.api.data.action
 * @ClassName: TalkAction
 * @Description: 直播聊天室 发送消息
 * @Author: hingbox@163.com
 * @CreateDate: 2018/12/26 14:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/26 14:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@ParentPackage("xiif-default")
@Namespace("/api")
@Results({
        @Result(name = "json",type="json")
})
public class TalkAction extends BaseAction{
    private String userId;
    private String msg;
    private String roomId;
    private String url;
    @Action("sendMessage")
    public String sendMessage() throws IOException {
        //首先需要先创建一个DefaultHttpClient的实例
        HttpClient httpClient = new DefaultHttpClient();
        //先创建一个HttpGet对象,传入目标的网络地址,然后调用HttpClient的execute()方法即可:
        HttpPost httpPost = new HttpPost();
        httpPost.setURI(URI.create(url+"/chatrecv/receiveMessage?userId="+userId+"&msg="+msg+"&roomNo="+roomId));
        HttpResponse response = httpClient.execute(httpPost);

        String httpEntityContent = ResponseUtil.getHttpEntityContent(response);
        httpPost.abort();
        ResponseUtil.ajaxPrintByJson(httpEntityContent);
        ResponseUtil.responseOut(httpEntityContent);
        return "json";

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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