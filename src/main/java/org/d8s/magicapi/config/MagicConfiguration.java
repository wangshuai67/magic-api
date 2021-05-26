package org.d8s.magicapi.config;

import org.d8s.magicapi.adapter.Resource;
import org.d8s.magicapi.controller.RequestHandler;
import org.d8s.magicapi.interceptor.AuthorizationInterceptor;
import org.d8s.magicapi.interceptor.RequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.d8s.magicapi.provider.ApiServiceProvider;
import org.d8s.magicapi.provider.FunctionServiceProvider;
import org.d8s.magicapi.provider.GroupServiceProvider;
import org.d8s.magicapi.provider.ResultProvider;

import java.util.ArrayList;
import java.util.List;

public class MagicConfiguration {

	/**
	 * 接口映射
	 */
	private MappingHandlerMapping mappingHandlerMapping;

	/**
	 * 函数管理
	 */
	private MagicFunctionManager magicFunctionManager;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 编辑器配置文件
	 */
	private String editorConfig;

	/**
	 * 拦截器
	 */
	private final List<RequestInterceptor> requestInterceptors = new ArrayList<>();

	/**
	 * 接口查询Service
	 */
	private ApiServiceProvider magicApiService;

	/**
	 * 分组查询Service
	 */
	private GroupServiceProvider groupServiceProvider;

	/**
	 * 函数查询Service
	 */
	private FunctionServiceProvider functionServiceProvider;

	/**
	 * 动态数据源
	 */
	private MagicDynamicDataSource magicDynamicDataSource;

	/**
	 * 请求出错时，是否抛出异常
	 */
	private boolean throwException = false;

	/**
	 * 结果处理器
	 */
	private ResultProvider resultProvider;

	private Resource workspace;

	private List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<>();

	private AuthorizationInterceptor authorizationInterceptor;

	/**
	 * debug 超时时间
	 */
	private int debugTimeout;

	private boolean enableWeb = false;

	public void setEnableWeb(boolean enableWeb) {
		this.enableWeb = enableWeb;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addRequestInterceptor(RequestInterceptor requestInterceptor) {
		this.requestInterceptors.add(requestInterceptor);
	}

	public void setMappingHandlerMapping(MappingHandlerMapping mappingHandlerMapping) {
		this.mappingHandlerMapping = mappingHandlerMapping;
	}

	public void setMagicApiService(ApiServiceProvider magicApiService) {
		this.magicApiService = magicApiService;
	}

	public void setGroupServiceProvider(GroupServiceProvider groupServiceProvider) {
		this.groupServiceProvider = groupServiceProvider;
	}

	public void setResultProvider(ResultProvider resultProvider) {
		this.resultProvider = resultProvider;
	}

	public void setThrowException(boolean throwException) {
		this.throwException = throwException;
	}

	public void setHttpMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
		this.httpMessageConverters = httpMessageConverters;
	}

	public void setDebugTimeout(int debugTimeout) {
		this.debugTimeout = debugTimeout;
	}

	public void setMagicDynamicDataSource(MagicDynamicDataSource magicDynamicDataSource) {
		this.magicDynamicDataSource = magicDynamicDataSource;
	}

	public MappingHandlerMapping getMappingHandlerMapping() {
		return mappingHandlerMapping;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public AuthorizationInterceptor getAuthorizationInterceptor() {
		return authorizationInterceptor;
	}

	public void setAuthorizationInterceptor(AuthorizationInterceptor authorizationInterceptor) {
		this.authorizationInterceptor = authorizationInterceptor;
	}

	public List<RequestInterceptor> getRequestInterceptors() {
		return requestInterceptors;
	}

	public ApiServiceProvider getMagicApiService() {
		return magicApiService;
	}

	public GroupServiceProvider getGroupServiceProvider() {
		return groupServiceProvider;
	}

	public MagicDynamicDataSource getMagicDynamicDataSource() {
		return magicDynamicDataSource;
	}

	public boolean isThrowException() {
		return throwException;
	}

	public ResultProvider getResultProvider() {
		return resultProvider;
	}

	public List<HttpMessageConverter<?>> getHttpMessageConverters() {
		return httpMessageConverters;
	}

	public int getDebugTimeout() {
		return debugTimeout;
	}

	public boolean isEnableWeb() {
		return enableWeb;
	}

	public FunctionServiceProvider getFunctionServiceProvider() {
		return functionServiceProvider;
	}

	public void setFunctionServiceProvider(FunctionServiceProvider functionServiceProvider) {
		this.functionServiceProvider = functionServiceProvider;
	}

	public MagicFunctionManager getMagicFunctionManager() {
		return magicFunctionManager;
	}

	public void setMagicFunctionManager(MagicFunctionManager magicFunctionManager) {
		this.magicFunctionManager = magicFunctionManager;
	}

	public String getEditorConfig() {
		return editorConfig;
	}

	public void setEditorConfig(String editorConfig) {
		this.editorConfig = editorConfig;
	}

	public Resource getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Resource workspace) {
		this.workspace = workspace;
	}

	/**
	 * 打印banner
	 */
	public void printBanner() {

	}
}
