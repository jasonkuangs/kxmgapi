package com.sy.basic.obj;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

public class StringResult extends StrutsResultSupport {
	private static final long serialVersionUID = -2170851885677724808L;
	private static final Logger logger = Logger.getLogger(StringResult.class);

	private String contentTypeName;
	private String string = "";

	public StringResult() {
		super();

	}

	public StringResult(String location) {
		super(location);

	}

	public void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(HTTP_RESPONSE);
		String contentType = conditionalParse(contentTypeName, invocation);
		if (contentType == null) {
			contentType = "text/plain; charset=UTF-8";
		}
		response.setContentType(contentType);
		PrintWriter out = response.getWriter();
		String result = (String) invocation.getStack().findValue(string);
		out.println(result);
		out.flush();
		out.close();
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getContentTypeName() {
		return contentTypeName;
	}

	public void setContentTypeName(String contentTypeName) {
		this.contentTypeName = contentTypeName;
	}

}