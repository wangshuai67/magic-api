package org.d8s.magicapi.provider.impl;

import org.d8s.magicapi.model.JsonBean;
import org.d8s.magicapi.model.RequestEntity;
import org.d8s.magicapi.provider.ResultProvider;
import org.d8s.magicapi.script.ScriptManager;
import org.d8s.script.MagicScriptContext;

public class DefaultResultProvider implements ResultProvider {

	private final String responseScript;

	public DefaultResultProvider(String responseScript) {
		this.responseScript = responseScript;
	}

	@Override
	public Object buildResult(RequestEntity requestEntity, int code, String message, Object data) {
		long timestamp = System.currentTimeMillis();
		if (this.responseScript != null) {
			MagicScriptContext context = new MagicScriptContext();
			context.set("code", code);
			context.set("message", message);
			context.set("data", data);
			context.set("apiInfo", requestEntity.getApiInfo());
			context.set("request", requestEntity.getRequest());
			context.set("response", requestEntity.getResponse());
			context.set("timestamp", timestamp);
			context.set("requestTime", requestEntity.getRequestTime());
			context.set("executeTime", timestamp - requestEntity.getRequestTime());
			return ScriptManager.executeExpression(responseScript, context);
		} else {
			return new JsonBean<>(code, message, data, (int) (timestamp - requestEntity.getRequestTime()));
		}
	}
}
