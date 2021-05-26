package org.d8s.magicapi.modules;

import org.apache.commons.lang3.StringUtils;
import org.d8s.magicapi.config.MagicModule;
import org.d8s.script.annotation.Comment;
import org.d8s.script.exception.MagicScriptAssertException;

import java.util.regex.Pattern;

/**
 * 断言模块
 */
public class AssertModule implements MagicModule {

	/**
	 * 判断值不能为null
	 *
	 * @param value   值
	 * @param code    状态码
	 * @param message 状态说明
	 */
	@Comment("判断值不能为空")
	public void notNull(@Comment("值") Object value, @Comment("判断失败时的code") int code, @Comment("判断失败时的说明") String message) {
		if (value == null) {
			throw new MagicScriptAssertException(code, message);
		}
	}

	/**
	 * 判断值不能为empty
	 *
	 * @param value   值
	 * @param code    状态码
	 * @param message 状态说明
	 */
	@Comment("判断值不能为Empty")
	public void notEmpty(@Comment("值") String value, @Comment("判断失败时的code") int code, @Comment("判断失败时的说明") String message) {
		if (StringUtils.isEmpty(value)) {
			throw new MagicScriptAssertException(code, message);
		}
	}

	/**
	 * 判断值不能为blank
	 *
	 * @param value   值
	 * @param code    状态码
	 * @param message 状态说明
	 */
	@Comment("判断值不能为Blank")
	public void notBlank(@Comment("值") String value, @Comment("判断失败时的code") int code, @Comment("判断失败时的说明") String message) {
		if (StringUtils.isBlank(value)) {
			throw new MagicScriptAssertException(code, message);
		}
	}

	/**
	 * 正则验证值
	 *
	 * @param value   值
	 * @param code    状态码
	 * @param message 状态说明
	 */
	@Comment("正则判断")
	public void regx(@Comment("值") String value, String pattern, @Comment("判断失败时的code") int code, @Comment("判断失败时的说明") String message) {
		if (value == null || !Pattern.compile(pattern).matcher(value).matches()) {
			throw new MagicScriptAssertException(code, message);
		}
	}

	/**
	 * 判断值值是否为true
	 *
	 * @param value   值
	 * @param code    状态码
	 * @param message 状态说明
	 */
	@Comment("判断值是否为true")
	public void isTrue(@Comment("值") boolean value, @Comment("判断失败时的code") int code, @Comment("判断失败时的说明") String message) {
		if (!value) {
			throw new MagicScriptAssertException(code, message);
		}
	}


	@Override
	public String getModuleName() {
		return "assert";
	}
}
