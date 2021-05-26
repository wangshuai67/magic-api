package org.d8s.magicapi.utils;

import java.util.regex.Pattern;

public class PathUtils {

	private static final Pattern REPLACE_SLASH_REGX = Pattern.compile("/+");

	/**
	 * 将多个/替换为一个/
	 */
	public static String replaceSlash(String path) {
		return REPLACE_SLASH_REGX.matcher(path).replaceAll("/");
	}
}
