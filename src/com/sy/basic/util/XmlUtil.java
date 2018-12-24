/**
 * 
 */
package com.sy.basic.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import com.thoughtworks.xstream.XStream;

/**
 * xml文档读取，创建工具
 * @author Tom
 * @date 2013-9-3 下午6:01:44
 */
@SuppressWarnings("rawtypes")
public class XmlUtil {

    /**
     * 读取xml文档
     * @param filePath
     * @return 返回一个对象
     * @throws FileNotFoundException
     */
    public static Object readXml(String filePath, Class classType) throws FileNotFoundException {
	return readXml(new FileInputStream(filePath), classType, false);
    }

    /**
     * @param file
     * @param classType
     * @return
     * @throws FileNotFoundException
     */
    public static Object readXml(File file, Class classType) throws FileNotFoundException {
	return readXml(new FileInputStream(file), classType, false);
    }

    /**
     * @param file
     * @param classType
     * @param isAnnotation
     * @return
     * @throws FileNotFoundException
     */
    public static Object readXml(File file, Class classType, boolean isAnnotation) throws FileNotFoundException {
	return readXml(new FileInputStream(file), classType, isAnnotation);
    }

    /**
     * @param stream
     * @param classType
     * @param isAnnotation
     * @return
     */
    public static Object readXml(InputStream stream, Class classType, boolean isAnnotation) {
	BufferedInputStream in = new BufferedInputStream(stream);
	XStream xmlData = new XStream();
	if (isAnnotation) {
	    xmlData.autodetectAnnotations(isAnnotation);
	}
	xmlData.processAnnotations(classType);
	Object object = xmlData.fromXML(in);
	try {
	    in.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return object;
    }

    /**
     * @param data
     * @param classType
     * @param isAnnotation
     * @return
     */
    public static Object readXml(String data, Class classType, boolean isAnnotation) {
	XStream xmlData = new XStream();
	if (isAnnotation) {
	    xmlData.autodetectAnnotations(isAnnotation);
	}
	xmlData.processAnnotations(classType);
	return xmlData.fromXML(data);
    }

    /**
     * 将对象转换为xml数据
     * @param obj
     * @return
     */
    public static String objToXmlStr(Object obj) {
	XStream xml = new XStream();
	xml.autodetectAnnotations(true);
	return xml.toXML(obj);
    }

    /**
     * 创建xml文档
     * @param outStream
     * @param writeObject
     * @param isMakeHead
     */
    public static void writeXml(OutputStream outStream, Object writeObject, boolean isMakeHead) {
	BufferedOutputStream out = null;
	StringBuilder builder = new StringBuilder();
	if (isMakeHead) {
	    builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	}
	out = new BufferedOutputStream(outStream);
	XStream xml = new XStream();
	xml.autodetectAnnotations(true);
	builder.append(xml.toXML(writeObject));
	try {
	    out.write(builder.toString().getBytes("utf-8"));
	    out.flush();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (out != null) {
		    out.close();
		}
		if (outStream != null) {
		    outStream.close();
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * 创建xml文档
     * @param filePath 写入的xml路径
     * @param writeObject 将写入的对象
     */
    public static void writeXml(String filePath, Object writeObject) {
	try {
	    writeXml(new FileOutputStream(filePath), writeObject, true);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

    }
    /**
     * xmltoobject
     */
    /*
     * public static Object xmlToObject(String filepath,Class object){
     * JAXBContext context = JAXBContext.newInstance(object);
     * Unmarshaller unmarshaller = context.createUnmarshaller();
     * Student student = (Student)unmarshaller.unmarshal(new StringReader(filepath));
     * return null;
     * }
     */
}
