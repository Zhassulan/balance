package kz.ztokbayev.qiwi.MobileService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropsManager {
	
	private static volatile PropsManager _instance = null;
	private Properties appProps;
	
	private PropsManager()	{
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "application.properties";
		appProps = new Properties();
		try {
			appProps.load(new FileInputStream(appConfigPath));
			}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized PropsManager getInstance() {
        if (_instance == null)
        	 synchronized (PropsManager.class) {
                 if (_instance == null)
                     _instance = new PropsManager();
             }
        return _instance;
    }
	
	public String getProperty(String param)	{
		return appProps.getProperty(param);
	}
}
