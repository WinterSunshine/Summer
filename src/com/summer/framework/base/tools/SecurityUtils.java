package com.summer.framework.base.tools;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import android.util.Base64;
import android.util.Log;

public class SecurityUtils
{
	private SecurityUtils()
	{
	}
	
	/**
	 * 用MD5算法进行加密
	 * 
	 * @param str 需要加密的字符串
	 * @return MD5加密后的结果
	 */
	public static String encodeMD5String(String str)
	{
		return encode(str, "MD5");
	}
	
	/**
	 * 用SHA算法进行加密
	 * 
	 * @param str 需要加密的字符串
	 * @return SHA加密后的结果
	 */
	public static String encodeSHAString(String str)
	{
		return encode(str, "SHA");
	}
	
	/**
	 * 用base64算法进行加密
	 * 
	 * @param str 需要加密的字符串
	 * @return base64加密后的结果
	 */
	public static String encodeBase64String(String str)
	{
		return Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
	}
	
	/**
	 * 用base64算法进行解密
	 * 
	 * @param str 需要解密的字符串
	 * @return base64解密后的结果
	 * @throws IOException
	 */
	public static String decodeBase64String(String str)
	{
		return new String(Base64.decode(str.getBytes(), Base64.DEFAULT));
	}
	
	private static String encode(String str, String method)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance(method);
			md.update(str.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e)
		{
			Log.e(SecurityUtils.class.getSimpleName(), e.getMessage());
			return "";
		}
	}
}
