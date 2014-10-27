package com.summer.framework.base.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class ResourceUtils
{
	public static String geFileFromAssets(Context context, String fileName) throws IOException
	{
		StringBuilder s = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(fileName)));
		try
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				s.append(line);
			}
			return s.toString();
		} finally
		{
			br.close();
		}
	}
	
	public static String geFileFromRaw(Context context, int resId) throws IOException
	{
		StringBuilder s = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resId)));
		try
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				s.append(line);
			}
			return s.toString();
		} finally
		{
			br.close();
		}
	}
	
	public static List<String> geFileToListFromAssets(Context context, String fileName) throws IOException
	{
		List<String> fileContent = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(fileName)));
		try
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				fileContent.add(line);
			}
			br.close();
			return fileContent;
		} finally
		{
			br.close();
		}
	}
	
	public static List<String> geFileToListFromRaw(Context context, int resId) throws IOException
	{
		List<String> fileContent = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resId)));
		try
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				fileContent.add(line);
			}
			br.close();
			return fileContent;
		} finally
		{
			br.close();
		}
	}
}
