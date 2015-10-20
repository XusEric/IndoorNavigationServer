package com.nav.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper {
	static private String _dbUserName = null;
	static private String _dbPassWord = null;
	static private String _dbDriver = null;
	static private String _dbUrl = null;
	static{
		loads();
	}
	synchronized static public void loads(){
		if(_dbUserName==null||_dbPassWord==null||_dbDriver==null||_dbUrl==null)
		{
			InputStream fis = ConfigHelper.class.getResourceAsStream("/dbinfo.properties");
			Properties dbProps = new Properties();
			try {
				dbProps.load(fis);
				_dbUserName = dbProps.getProperty("dbUserName");
				_dbPassWord = dbProps.getProperty("dbPassword");
				_dbDriver = dbProps.getProperty("dbDriver");
				_dbUrl = dbProps.getProperty("dbUrl");
			}
			catch (Exception e) {
				System.err.println("不能读取属性文件. " +
				"请确保db.properties在CLASSPATH指定的路径中");
			}
			finally  
	        {  
	            try  
	            { fis.close();}  
	            catch(IOException e) {e.printStackTrace();}  
	            fis = null;//垃圾回收站上收拾  
	        }  
		}
	}
	
	public static String getDbUserName() {
		if(_dbUserName==null)
			loads();
			return _dbUserName;
	}
	
	public static String getDbPassWord() {
		if(_dbPassWord==null)
			loads();
			return _dbPassWord;
	}
	
	public static String getDbDriver() {
		if(_dbDriver==null)
			loads();
			return _dbDriver;
	}

	public static String getDbUrl() {
		if(_dbUrl==null)
			loads();
			return _dbUrl;
	}
}
