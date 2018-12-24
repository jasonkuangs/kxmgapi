package com.sy.basic.util;

import org.apache.log4j.Logger;

public class LoggerUtil {

    private static Logger managerLogger = Logger.getLogger("manager");
    private static Logger clientLogger = Logger.getLogger("client");
    private static String LogSwitch=JsonUtil.getProFile().getProperty("LogSwitch");

    public static Logger getManagerLogger() {
    	return managerLogger;
    }

    public static Logger getClientLogger() {
    	return clientLogger;
    }

    public static boolean getLog() {
    	return LogSwitch.equalsIgnoreCase("true");
    }
    
}
