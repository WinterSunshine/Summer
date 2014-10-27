package com.summer.framework.base.tools;

import java.util.ArrayList;
import java.util.List;

public class ParamUtils
{
	public final List<ParamUtils.NameValue> nameValueArray = new ArrayList<ParamUtils.NameValue>();
	
	public class NameValue
	{
		public final String name;
		public final Object value;
		
		public NameValue(String name, Object value)
		{
			this.name = name;
			this.value = value;
		}
		
		@Override
		public String toString()
		{
			return name + ":" + value;
		}
	}
	
	public static ParamUtils build()
	{
		return new ParamUtils();
	}
	
	public List<ParamUtils.NameValue> create()
	{
		return nameValueArray;
	}
	
	public ParamUtils put(String name, String value)
	{
		appendToParamsArray(name, value);
		return this;
	}
	
	public ParamUtils put(String name, int value)
	{
		appendToParamsArray(name, value);
		return this;
	}
	
	public ParamUtils put(String name, boolean value)
	{
		appendToParamsArray(name, value);
		return this;
	}
	
	public ParamUtils put(String name, float value)
	{
		appendToParamsArray(name, value);
		return this;
	}
	
	public ParamUtils put(String name, long value)
	{
		appendToParamsArray(name, value);
		return this;
	}
	
	public ParamUtils put(String name, double value)
	{
		appendToParamsArray(name, value);
		return this;
	}
	
	private ParamUtils appendToParamsArray(String name, Object value)
	{
		nameValueArray.add(new NameValue(name, value));
		return this;
	}
}
