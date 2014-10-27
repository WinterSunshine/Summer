package com.summer.framework.base.tools;

import java.lang.reflect.Proxy;

import com.summer.framework.service.api.IHttpService;
import com.summer.framework.service.http.HttpProxyInvocation;

public class ProxyUtils
{
	private static HttpProxyInvocation proxyHandler = new HttpProxyInvocation();
	
	public static IHttpService getHttpProxy()
	{
		return (IHttpService) Proxy.newProxyInstance(proxyHandler.getClass().getClassLoader(), new Class[]{IHttpService.class}, proxyHandler);
	}
}
