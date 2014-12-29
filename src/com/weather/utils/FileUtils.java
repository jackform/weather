package com.weather.utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;


public class FileUtils {
	private final static int BUFFER_LENGTH = 1024;
	
	public static String readFromStream(InputStream is) throws IOException
	{
		return readFromStream(is,"gbk");
	}
	
	public static String readFromStream(InputStream is,String unicode) throws IOException
	{
		if( !unicode.equals("gbk") && !unicode.equals("utf-8") )
			return null;
		
		char[] buffer = new char[BUFFER_LENGTH];
		InputStreamReader reader = new InputStreamReader(is,unicode);
		StringWriter strGather = new StringWriter();
		int strLength = 0;
		try {
			while( (strLength = reader.read(buffer)) != -1 ) {
				strGather.write(buffer, 0, strLength);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( null != reader ) {
				reader.close();
			}
			strGather.close();
		}
		return strGather.toString();
	}

}
