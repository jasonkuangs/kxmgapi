package com.sy.basic.interceptor;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sy.basic.util.AesUtil;

/**
 * 参数trim拦截器
 * @author xp
 * @date 2015年11月10日 上午11:36:42
 */
@Component
public class TrimInterceptor extends AbstractInterceptor {

    /**
     * @author xp
     * @date 2015年11月10日 上午11:36:37
     */
    private static final long serialVersionUID = 4945899961415671549L;

    public String intercept(ActionInvocation invocation) throws Exception {
//    	opt = AesUtil.aesDecrypt(opt, AesUtil.PARAMS_KEY);
//		if (!StringUtils.hasText(opt)||!opt.equals("update")) {
//		}
//		data = AesUtil.aesDecrypt(data, AesUtil.PARAMS_KEY);
//		if (!StringUtils.hasText(data)) {
//			logger.info("参数错误data:"+data);
	Map<String, Object> parameters = invocation.getInvocationContext().getParameters();
	for (String key : parameters.keySet()) {
	    Object value = parameters.get(key);
	    if (value instanceof String[]) {
		String[] valueArray = (String[]) value;
		for (int i = 0; i < valueArray.length; i++) {
		    valueArray[i] = valueArray[i].trim();
		}
		parameters.put(key, valueArray);
	    }
	}
	return invocation.invoke();
    }

}
