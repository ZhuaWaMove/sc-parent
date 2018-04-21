package cn.com.commen.util;

import org.apache.commons.lang.StringUtils;


public class DefultStringUtils {
	
	public static String replaceString(String path) {
		if(StringUtils.isNotBlank(path)) {
			path = path.startsWith("/") ? path : "/" + path;
			path = path.endsWith("/") ? path : path + "/";
			if (!path.contains("**")) {
				path = path + "**";
			}
		}
		return path;
	}
}
