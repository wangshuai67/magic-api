package org.d8s.magicapi.modules;

import org.d8s.magicapi.config.MagicModule;
import org.springframework.core.env.Environment;
import org.d8s.script.annotation.Comment;

public class EnvModule implements MagicModule {

	private final Environment environment;

	public EnvModule(Environment environment) {
		this.environment = environment;
	}

	@Override
	public String getModuleName() {
		return "env";
	}

	@Comment("获取配置")
	public String get(@Comment("配置项") String key) {
		return environment.getProperty(key);
	}

	@Comment("获取配置")
	public String get(@Comment("配置项") String key, @Comment("未配置时的默认值") String defaultValue) {
		return environment.getProperty(key, defaultValue);
	}
}
