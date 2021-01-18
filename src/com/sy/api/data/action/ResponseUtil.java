package com.sy.api.data.action;

import org.apache.http.HttpEntity;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @ProjectName: MiguApi
 * @Package: com.sy.api.data.action
 * @ClassName: AjaxResponse
 * @Description: java类作用描述
 * @Author: hingbox@163.com
 * @CreateDate: 2018/12/26 0:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/26 0:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ResponseUtil {
    private static String jsoncallback="";
    public static void responseOut(Object value){

        HttpServletResponse response = responseCommon();
        try {
            response.getWriter().write(value.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static HttpServletResponse responseCommon(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        return response;
    }

    /**
     * Ajax输出json
     **/
    public static void ajaxPrintByJson(Object content) {
        HttpServletResponse response = responseCommon();
        try {
            response.setContentType("text/json;charset=UTF-8");
            java.io.PrintWriter out = response.getWriter();
            out.print(jsoncallback+"("+content+")");
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得响应HTTP实体内容
     * @param response
     * @return
     * @throws java.io.IOException
     * @throws java.io.UnsupportedEncodingException
     */
    public static String getHttpEntityContent(org.apache.http.HttpResponse response) throws IOException, UnsupportedEncodingException {
        //通过HttpResponse 的getEntity()方法获取返回信息
        HttpEntity entity = response.getEntity();
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

}