package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.lang.*;
/**
 * 
 * @author sraddank
 *@version1.0
 */
public class PropertiesFileReader {

	
	private static PropertiesFileReader INSTANCE = null;
	static Properties properties = new Properties();
	
	private PropertiesFileReader(){

	}
	
	public static synchronized PropertiesFileReader getInstance() {
        if (INSTANCE==null) {
        	INSTANCE = new PropertiesFileReader();
        }
        return INSTANCE;
    }
	
	public Properties readProperties(String name){
		
		InputStream in = null;
		try {
			if(null != name){
			
			properties.load(new FileReader(new File("src/resources/"+name)));
			System.out.println("Properties File Loadded successfully : " + name);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public static String getProperty(String key){
		String value = null;
		if(null != key && !"".equalsIgnoreCase(key)){
			value = (String)properties.get(key);
		}
		return value;
	}

	public static void setProperty(String key, String prodTitle) {
		// TODO Auto-generated method stub
		if(null != key && !"".equalsIgnoreCase(key)){
		properties.setProperty(key, prodTitle);
		}		
	}
	
}
