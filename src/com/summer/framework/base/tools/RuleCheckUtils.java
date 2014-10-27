package com.summer.framework.base.tools;

import java.util.regex.Pattern;

import android.graphics.drawable.Drawable;

public class RuleCheckUtils
{
	private static final String MATCH_QQ_REGEX = "^\\d{5,10}$";
	private static final String MATCH_NUMBER_REGEX = "\\d+";
	private static final String MATCH_PHONE_REGEX = "((13[0-9])|(14[57])|(15[^4,\\D])|(170)|(18[0-9]))\\d{8}$";
	private static final String MATCH_EMAIL_REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	
	private RuleCheckUtils()
	{
	}
	
	public static boolean matchQQRegex(String qq)
	{
		return Pattern.matches(MATCH_QQ_REGEX, qq);
	}
	
	public static boolean matchNumberRegex(String pwd)
	{
		return Pattern.matches(MATCH_NUMBER_REGEX, pwd);
	}
	
	public static boolean matchPhoneRegex(String phonNum)
	{
		return Pattern.matches(MATCH_PHONE_REGEX, phonNum);
	}
	
	public static boolean matchEmailRegex(String email)
	{
		return Pattern.matches(MATCH_EMAIL_REGEX, email);
	}
	
	public static boolean equals(Drawable a, Drawable b)
	{
		return a.getConstantState().equals(b.getConstantState());
	}
}
