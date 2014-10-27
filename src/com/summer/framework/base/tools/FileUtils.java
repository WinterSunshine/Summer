package com.summer.framework.base.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.os.Handler;

public class FileUtils
{
	private FileUtils()
	{
	}
	
	public static void recursionFile(File cacheFile, List<File> cacheFiles)
	{
		File[] files = cacheFile.listFiles();
		for (File file : files)
		{
			if (file.isDirectory())
			{
				recursionFile(file, cacheFiles);
			} else
			{
				cacheFiles.add(file);
			}
		}
	}
	
	public static void delFile(String path)
	{
		new File(path).delete();
	}
	
	public static File downloadFile(String serverPath, String localPath, Handler hanler) throws Exception
	{
		File apkInstallPath = new File(localPath, "xxxxx.apk");
		URL url = new URL(serverPath);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setConnectTimeout(5000);
		con.setRequestProperty("Accept-Encoding", "identity");
		double maxLength = con.getContentLength();
		InputStream is = con.getInputStream();
		FileOutputStream fos = new FileOutputStream(apkInstallPath);
		BufferedInputStream bis = new BufferedInputStream(is);
		byte[] buffer = new byte[1024 * 10];
		int len;
		int total = 0;
		while ((len = bis.read(buffer)) != -1)
		{
			fos.write(buffer, 0, len);
			total += len;
			fos.flush();
			int progress = (int) (MathUtils.saveTwoDecimal(total / maxLength) * 100);
			hanler.obtainMessage(0, progress).sendToTarget();
		}
		fos.close();
		bis.close();
		return apkInstallPath;
	}
	
	public static void WriteCrashLog(File logFile, Throwable ex) throws Exception
	{
		PrintWriter writer = new PrintWriter(new FileWriter(logFile, true));
		try
		{
			ex.printStackTrace(writer);
			writer.flush();
		} finally
		{
			writer.close();
		}
	}
}
