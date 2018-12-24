/**
 * 
 */
package com.sy.basic.util;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Xml 处理工具类
 * @author Tom
 * @date 2014-1-21 下午4:46:10
 */
public class XmlHandleUtil {
    /**
     * xml 根元素
     */
    private Element root;

    /**
     * dom4j xml文件
     */
    private Document document;

    /**
     * 构造函数
     * @param xmlClassPath xml相对于class 文件路径
     */
    public XmlHandleUtil(String xmlClassPath) {
	InputStream inputStream = null;
	try {
	    inputStream = getClass().getResourceAsStream(xmlClassPath);
	    SAXReader saxReader = new SAXReader();
	    document = saxReader.read(inputStream);
	    root = document.getRootElement();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (inputStream != null)
		    inputStream.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * @return the root
     */
    public Element getRoot() {
	return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(Element root) {
	this.root = root;
    }

    /**
     * @return the document
     */
    public Document getDocument() {
	return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(Document document) {
	this.document = document;
    }
}
