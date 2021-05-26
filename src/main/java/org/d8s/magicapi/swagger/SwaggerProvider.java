package org.d8s.magicapi.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.d8s.magicapi.model.ApiInfo;
import org.d8s.magicapi.model.Path;
import org.springframework.web.bind.annotation.ResponseBody;
import org.d8s.magicapi.config.MappingHandlerMapping;
import org.d8s.magicapi.provider.GroupServiceProvider;
import org.d8s.script.parsing.ast.literal.BooleanLiteral;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 生成swagger用的json
 */
public class SwaggerProvider {

	private MappingHandlerMapping mappingHandlerMapping;

	/**
	 * 基础路径
	 */
	private String basePath;

	private GroupServiceProvider groupServiceProvider;

	private SwaggerEntity.Info info;

	public void setMappingHandlerMapping(MappingHandlerMapping mappingHandlerMapping) {
		this.mappingHandlerMapping = mappingHandlerMapping;
	}

	public void setGroupServiceProvider(GroupServiceProvider groupServiceProvider) {
		this.groupServiceProvider = groupServiceProvider;
	}

	public void setInfo(SwaggerEntity.Info info) {
		this.info = info;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	@ResponseBody
	public SwaggerEntity swaggerJson() {
		List<ApiInfo> infos = mappingHandlerMapping.getApiInfos();
		SwaggerEntity swaggerEntity = new SwaggerEntity();
		swaggerEntity.setInfo(info);
		swaggerEntity.setBasePath(this.basePath);
		ObjectMapper mapper = new ObjectMapper();
		for (ApiInfo info : infos) {
			String groupName = groupServiceProvider.getFullName(info.getGroupId()).replace("/", "-");
			String requestPath = "/" + mappingHandlerMapping.getRequestPath(info.getGroupId(), info.getPath());
			SwaggerEntity.Path path = new SwaggerEntity.Path();
			path.addTag(groupName);
			boolean hasBody = false;
			try {
				List<SwaggerEntity.Parameter> parameters = parseParameters(mapper, info);
				hasBody = parameters.stream().anyMatch(it -> "body".equals(it.getIn()));
				parameters.forEach(path::addParameter);
				path.addResponse("200", mapper.readValue(Objects.toString(info.getResponseBody(), "{}"), Object.class));
			} catch (Exception ignored) {
			}
			if (hasBody) {
				path.addConsume("application/json");
			} else {
				path.addConsume("*/*");
			}
			path.addProduce("application/json");
			path.setSummary(StringUtils.defaultIfBlank(info.getDescription(), info.getName()));

			swaggerEntity.addPath(requestPath, info.getMethod(), path);
		}
		return swaggerEntity;
	}

	private List<SwaggerEntity.Parameter> parseParameters(ObjectMapper mapper, ApiInfo info) {
		List<SwaggerEntity.Parameter> parameters = new ArrayList<>();
		info.getParameters().forEach(it -> parameters.add(new SwaggerEntity.Parameter(it.isRequired(), it.getName(), "query", it.getDataType().getJavascriptType(), it.getDescription(), it.getValue())));
		info.getHeaders().forEach(it -> parameters.add(new SwaggerEntity.Parameter(it.isRequired(), it.getName(), "header", it.getDataType().getJavascriptType(), it.getDescription(), it.getValue())));
		List<Path> paths = new ArrayList<>(info.getPaths());
		MappingHandlerMapping.findGroups(info.getGroupId())
				.stream()
				.flatMap(it -> it.getPaths().stream())
				.forEach(it -> {
					if (!paths.contains(it)) {
						paths.add(it);
					}
				});
		paths.forEach(it -> parameters.add(new SwaggerEntity.Parameter(it.isRequired(), it.getName(), "path", it.getDataType().getJavascriptType(), it.getDescription(), it.getValue())));
		try {
			Object object = mapper.readValue(info.getRequestBody(), Object.class);
			if ((object instanceof List || object instanceof Map) && BooleanLiteral.isTrue(object)) {
				parameters.add(new SwaggerEntity.Parameter(false, "body", "body", object instanceof List ? "array" : "object", null, object));
			}
		} catch (Exception ignored) {
		}
		return parameters;
	}
}
