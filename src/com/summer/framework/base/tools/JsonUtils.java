package com.summer.framework.base.tools;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class JsonUtils
{
	private static Gson gson = new Gson();
	
	private JsonUtils()
	{
	}
	
	/**
	 * 将JSON对象转换为一个Bean对象
	 */
	public static <T> T parserJSONObject(JSONObject jsonObj, Class<T> resultCls)
	{
		return gson.fromJson(jsonObj.toString(), resultCls);
	}
	
	/**
	 * 将JSON数组转换为一个集合
	 */
	public static <T> List<T> parserJSONArray(JSONObject jsonObj, Type type) throws JSONException
	{
		return gson.fromJson(jsonObj.toString(), type);
	}
}
