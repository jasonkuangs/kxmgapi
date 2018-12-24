package com.sy.basic.action;

import com.sy.basic.common.HttpUtil;

public class mainAction {
	 public static void main(String[] args){
		  try{
			    String xmmpin="http://127.0.0.1:8080/AmtpushServer/notification.do";
			    String requestString="action=sendByItvname&title=1&itvname=pwg255&message=updatewebcat{*{*{*{*{pwg255";
			    System.out.println(xmmpin);
			    HttpUtil.httpPostUtil(xmmpin, requestString);
		    }catch(Exception ex){
		    	System.out.println("error:"+ex.toString());
		    	
		    }
		 
	 }
}
