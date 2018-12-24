package com.sy.basic.common;

/**
 * 服务异常对象
 * @author xp
 * @date 2015年11月10日 上午10:50:23
 */
public class ServiceException extends RuntimeException {

    /**
     * @author xp
     * @date 2015年11月10日 上午10:51:58
     */
    private static final long serialVersionUID = 1757251449480694064L;

    public ServiceException() {
	super();
    }

    public ServiceException(String paramString, Throwable paramThrowable, boolean paramBoolean1,
	    boolean paramBoolean2) {
	super(paramString, paramThrowable, paramBoolean1, paramBoolean2);
    }

    public ServiceException(String paramString, Throwable paramThrowable) {
	super(paramString, paramThrowable);
    }

    public ServiceException(String paramString) {
	super(paramString);
    }

    public ServiceException(Throwable paramThrowable) {
	super(paramThrowable);
    }

}
