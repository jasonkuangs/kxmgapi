package com.sy.basic.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * yhl
 * 2015年7月10日 上午11:53:54
 * @描述 消息码配置
 */
public class RetCodeConst {
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface Desc {
	String desc();
    }

    /**
     * yhl
     * 2015年7月10日 上午11:54:27
     * @描述
     * @param RetCode 消息编号
     * @return 获取消息码对应描述
     */
    public static String getDesc(int RetCode) {
	String s = "";
	try {
	    s = RetCodeConst.class.getDeclaredField("R" + RetCode).getAnnotation(RetCodeConst.Desc.class).desc();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return s;
    }


    @Desc(desc = "请求成功")
    public static final int R60000001 = 60000001;
    @Desc(desc = "请求失败")
    public static final int R60000002 = 60000002;
    @Desc(desc = "非法请求")
    public static final int R60000003 = 60000003;
    @Desc(desc = "请求错误")
    public static final int R60000004 = 60000004;
    @Desc(desc = "无结果")
    public static final int R60000005 = 60000005;
    @Desc(desc = "请求异常")
    public static final int R60000006 = 60000006;
    @Desc(desc = " ")
    public static final int R60000007 = 60000007;
    @Desc(desc = " ")
    public static final int R60000008 = 60000008;
    @Desc(desc = " ")
    public static final int R60000009 = 60000009;
    @Desc(desc = " ")
    public static final int R50000010 = 50000010;

}