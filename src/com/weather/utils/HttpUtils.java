package com.weather.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
	public static String readJsonFromUrl(String urlStr)
	{
		String content = null;
		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("referer", "http://mobile.weather.com.cn");
			conn.connect();
			content = FileUtils.readFromStream(conn.getInputStream(),"utf-8");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
}
