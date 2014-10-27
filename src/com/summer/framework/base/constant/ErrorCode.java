package com.summer.framework.base.constant;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;

public class ErrorCode
{
	private static final BidiMap error = new TreeBidiMap();
	static
	{
		error.put(9999, "失败");
		error.put(1000, "参数缺失");
		error.put(1001, "正在处理中");
		error.put(1002, "用户中途取消");
		error.put(1003, "用户名不存在");
		error.put(1004, "密码错误");
	}
	
	private ErrorCode()
	{
	}
	
	public static String getErrorMessage(int errorCode)
	{
		return error.get(errorCode).toString();
	}
	
	public static int getErrorCode(String errorMsg)
	{
		return Integer.parseInt(error.getKey(errorMsg).toString());
	}
}
