package com.summer.framework.service.http;

import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.summer.framework.base.constant.ErrorCode;
import com.summer.framework.base.tools.DialogUtils;
import com.summer.framework.base.tools.JsonUtils;
import com.summer.framework.base.tools.ReflectUtils;

public class HttpResponseHandler extends JsonHttpResponseHandler
{
	private static final int SUCCESS = 0;
	private static final String OPCODE = "opcode";
	private static final String RESULT = "result";
	private static final String ERROR = "showErrorMessage";
	private Object context;
	private Class<?> resultCls;
	private String refreshMethod;
	
	public HttpResponseHandler(Object context, Class<?> resultCls, String refreshMethod)
	{
		this.context = context;
		this.resultCls = resultCls;
		this.refreshMethod = refreshMethod;
	}
	
	@Override
	public void onStart()
	{
		DialogUtils.showLoading(context);
	}
	
	@Override
	public void onFinish()
	{
		DialogUtils.dismissLoading();
	}
	
	@Override
	public void onFailure(Throwable throwable, String error)
	{
		DialogUtils.showError(context);
	}
	
	@Override
	public void onSuccess(JSONObject response)
	{
		try
		{
			if (response.getInt(OPCODE) == SUCCESS)
			{
				Object bean = JsonUtils.parserJSONObject(response.getJSONObject(RESULT), resultCls);
				ReflectUtils.invokeMethod(context, refreshMethod, bean, resultCls);
			} else
			{
				throw new JSONException(ErrorCode.getErrorMessage(response.getInt(OPCODE)));
			}
		} catch (Exception e)
		{
			ReflectUtils.invokeMethod(context, ERROR, e.getMessage(), String.class);
		}
	}
}
