package com.sy.basic.service;

import com.sy.basic.common.ServiceException;

/**
 * http请求
 * @author xp
 * @date 2015年11月11日 上午11:58:22
 */
public interface HttpRequesterService {

    /**
     * 发送get请求
     * @author xp
     * @date 2015年11月11日 上午11:34:33
     * @param url发送请求的 URL
     * @param param请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     * @throws ServiceException
     */
    public String sendGet(String url, String param) throws Exception;

    /**
     * 发送post请求
     * @author xp
     * @date 2015年11月11日 上午11:34:33
     * @param url发送请求的 URL
     * @param param请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     * @throws ServiceException
     */
    public String sendPost(String url, String param) throws Exception;
}
