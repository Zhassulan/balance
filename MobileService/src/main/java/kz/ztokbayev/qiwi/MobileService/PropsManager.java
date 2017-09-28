package kz.ztokbayev.qiwi.MobileService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс для получения данных из файла конфигурации приложения Spring application.properties
 * Паттерн синглтон с ленивой инициализацией (т.е. по запросу)  
 * @author Zhassulan Tokbaev
 * @version 1.0
 * @see PasswordValidator
 **/

public class PropsManager {
	/** ссылка на будущий экземпляр класса **/
	private static volatile PropsManager _instance = null;
	/** класс для обработки свойств **/
	private Properties appProps;
	
	/** конструктор 
	 * @see PropsManager()
	 * **/
	private PropsManager()	{
		/** получение пути к файлу **/
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "application.properties";
		appProps = new Properties();
		try {
			appProps.load(new FileInputStream(appConfigPath));
			}
		catch (FileNotFoundException ex) {
			App.logger.info("Error message in PropsManager constructor " + ex.getMessage());
			App.logger.error("Stack trace: ", ex);
		}
		catch (IOException ex) {
			App.logger.info("Error message in PropsManager constructor: " + ex.getMessage());
			App.logger.error("Stack trace: ", ex);
		}
	}
	
	/** метод получения ссылки экземпляра класса
	 * @see getInstance()
	 */
	public static synchronized PropsManager getInstance() {
        if (_instance == null)
        	 synchronized (PropsManager.class) {
                 if (_instance == null)
                     _instance = new PropsManager();
             }
        return _instance;
    }
	
	/** метод получения значения по имени свойства в конфигурации приложения
	 * @see getProperty()
	 * @param String param название параметра
	 */
	public String getProperty(String param)	{
		return appProps.getProperty(param);
	}
}
