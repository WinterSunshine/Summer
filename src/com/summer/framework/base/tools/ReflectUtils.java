package com.summer.framework.base.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.util.Log;

import com.summer.framework.service.http.HttpResponseHandler;

public class ReflectUtils
{
	private ReflectUtils()
	{
	}
	
	public static void invokeMethod(Object receiver, String methodName, Object param, Class<?> parameterTypes)
	{
		try
		{
			Method declaredMethod = getDeclaredMethod(receiver, methodName, parameterTypes);
			declaredMethod.setAccessible(true);
			declaredMethod.invoke(receiver, param);
		} catch (Exception e)
		{
			Log.e(receiver.getClass().getSimpleName(), e.getMessage());
		}
	}
	
	public static void invokeMethod(Object receiver, String methodName)
	{
		try
		{
			Method method = getDeclaredMethod(receiver, methodName);
			method.setAccessible(true);
			method.invoke(receiver);
		} catch (Exception e)
		{
			Log.e(receiver.getClass().getSimpleName(), e.getMessage());
		}
	}
	
	public static HttpResponseHandler constructHttpResponse(Object context, Class<?> resultCls, String refreshMethod)
			throws Exception
	{
		Constructor<HttpResponseHandler> constructor = HttpResponseHandler.class.getConstructor(Object.class, Class.class,
				String.class);
		return constructor.newInstance(context, resultCls, refreshMethod);
	}
	
	private static Method getDeclaredMethod(Object receiver, String methodName, Class<?>... parameterTypes)
	{
		Method method = null;
		for (Class<?> clazz = receiver.getClass(); clazz != Object.class; clazz = clazz.getSuperclass())
		{
			try
			{
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				return method;
			} catch (Exception e)
			{
				continue;
			}
		}
		throw new NoSuchMethodError(methodName);
	}
}
