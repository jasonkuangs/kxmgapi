package com.sy.api.data.action;

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
 * @ClassName: TransmitAction
 * @Description: 获取节目列表列表
 * @Author: hingbox@163.com
 * @CreateDate: 2018/12/25 15:41
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/25 15:41
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Namespace("/api")
@ParentPackage("xiif-default")
@Results({@Result(name="success",type="json") })
public class ProgramInfoAction {
    private String cid;

    /**
     * 获取节目列表 入参 cid
     * @return
     * @throws IOException
     */
    @Action(value="getContentInfo")
    public String getContentInfo() throws IOException {
        //首先需要先创建一个DefaultHttpClient的实例
        HttpClient httpClient = new DefaultHttpClient();
        //先创建一个HttpGet对象,传入目标的网络地址,然后调用HttpClient的execute()方法即可:
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(URI.create("http://www.miguvideo.com/wap/resource/miguPC_client/data/detailData.jsp?cid="+cid));
        HttpResponse response = httpClient.execute(httpGet);
        String httpEntityContent = ResponseUtil.getHttpEntityContent(response);
        httpGet.abort();
        ResponseUtil.ajaxPrintByJson(httpEntityContent);
        ResponseUtil.responseOut(httpEntityContent);
        return "json";
    }

    //==================================
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}