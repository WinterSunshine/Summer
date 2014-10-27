package com.summer.framework.base.tools;

import java.math.BigDecimal;

public class MathUtils
{
	private MathUtils()
	{
	}
	
	/**
	 * 浮点数A大于B
	 */
	public static boolean moreThan(double a, double b)
	{
		return new BigDecimal(a).compareTo(new BigDecimal(b)) == 1 ? true : false;
	}
	
	/**
	 * 浮点数A等于B
	 */
	public static boolean equals(double a, double b)
	{
		return new BigDecimal(a).compareTo(new BigDecimal(b)) == 0 ? true : false;
	}
	
	/**
	 * 浮点数A小于B
	 */
	public static boolean lessThan(double a, double b)
	{
		return new BigDecimal(a).compareTo(new BigDecimal(b)) == -1 ? true : false;
	}
	
	/**
	 * 浮点数加法保留两位小数
	 */
	public static double add(double a, double b)
	{
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		return saveTwoDecimal(b1.add(b2).doubleValue());
	}
	
	/**
	 * 浮点数减法保留两位小数
	 */
	public static double sub(double a, double b)
	{
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		return saveTwoDecimal(b1.subtract(b2).doubleValue());
	}
	
	/**
	 * 浮点数乘法保留两位小数
	 */
	public static double mul(double a, double b)
	{
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		return saveTwoDecimal(b1.multiply(b2).doubleValue());
	}
	
	/**
	 * 浮点数除法保留两位小数
	 */
	public static double div(double a, double b)
	{
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		return saveTwoDecimal(b1.divide(b2).doubleValue());
	}
	
	public static double saveTwoDecimal(double totalPrice)
	{
		BigDecimal bigDecimal = new BigDecimal(totalPrice);
		return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
