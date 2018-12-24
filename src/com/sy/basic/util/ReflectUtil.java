/**
 * 
 */
package com.sy.basic.util;

import java.lang.reflect.Method;

/**
 * 反射工具
 * @author Tom
 * @date 2014-1-23 下午5:15:48
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReflectUtil {
    // /**
    // * 加载后的类
    // */
    //
    // private Class ObjectClass;
    // /**
    // * 创建的对象
    // */
    // private Object object;
    //
    // /**
    // * 参数类型数组
    // */
    // private Class[] parameterTypes;
    //
    // /**
    // * 形参参数
    // */
    // private Object[] params;

    /**
     * 调用反射方法工厂
     * @param clazz 调用类路径
     * @param method 调用方法名
     * @param param 传入参数
     * @return Object
     */

    public Object reflectMethodFactory(String clazz, String method, Object... params) {
	try {
	    Class objectClass = Class.forName(clazz);
	    Object object = objectClass.newInstance();
	    Class[] parameterTypes = null;
	    if (params != null) {
		int len = params.length;
		parameterTypes = new Class[len];
		while (--len >= 0) {
		    parameterTypes[len] = params[len].getClass();
		}
	    }

	    Method _method = objectClass.getMethod(method, parameterTypes);

	    return _method.invoke(object, params);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    // public static void main(String[] args) {
    // Map map = new HashMap();
    // new ReflectUtil().reflectMethodFactory("test.model.uploadfilter.TemplateFilterModel", "uploadFileFilter", map);
    // }

    // /**
    // * @return the objectClass
    // */
    // public Class getObjectClass() {
    // return ObjectClass;
    // }
    //
    // /**
    // * @param objectClass the objectClass to set
    // */
    // public void setObjectClass(Class objectClass) {
    // ObjectClass = objectClass;
    // }
    //
    // /**
    // * @return the object
    // */
    // public Object getObject() {
    // return object;
    // }
    //
    // /**
    // * @param object the object to set
    // */
    // public void setObject(Object object) {
    // this.object = object;
    // }
    //
    // /**
    // * @return the parameterTypes
    // */
    // public Class[] getParameterTypes() {
    // return parameterTypes;
    // }
    //
    // /**
    // * @param parameterTypes the parameterTypes to set
    // */
    // public void setParameterTypes(Class[] parameterTypes) {
    // this.parameterTypes = parameterTypes;
    // }
    //
    // /**
    // * @return the params
    // */
    // public Object[] getParams() {
    // return params;
    // }
    //
    // /**
    // * @param params the params to set
    // */
    // public void setParams(Object[] params) {
    // this.params = params;
    // }

}
