package com.summer.framework.service.http;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.summer.framework.base.annotation.HttpRequest;
import com.summer.framework.base.constant.Constant;
import com.summer.framework.base.tools.ReflectUtils;

public class HttpProxyInvocation implements InvocationHandler
{
	private AsyncHttpClient client = new AsyncHttpClient();
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable
	{
		String[] arguments = method.getAnnotation(HttpRequest.class).arguments();
		Class<?> resultCls = method.getAnnotation(HttpRequest.class).resultClass();
		String refreshMethod = method.getAnnotation(HttpRequest.class).refreshMethod();
		HttpResponseHandler response = ReflectUtils.constructHttpResponse(params[params.length - 1], resultCls, refreshMethod);
		client.post(Constant.BASE_URL + method.getName(), createRequestParams(arguments, params), response);
		return new Object();
	}
	
	private RequestParams createRequestParams(String[] arguments, Object[] params)
	{
		RequestParams requestParams = new RequestParams();
		for (int pos = 0; pos < arguments.length; pos++)
		{
			requestParams.put(arguments[pos], params[pos].toString());
		}
		return requestParams;
	}
}
