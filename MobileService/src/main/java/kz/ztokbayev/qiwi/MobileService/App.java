package kz.ztokbayev.qiwi.MobileService;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Точка входа в приложение
 * @author Zhassulan Tokbaev
 * @version 1.0
 * @see App
*/

@SpringBootApplication
public class App {
	
	public static final int resultCodeSuccess = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeSuccess"));
	public static final int resultCodeAgtExists = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeAgtExists"));
	public static final int resultCodeBadPhoneFormat = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeBadPhoneFormat"));
	public static final int resultCodeWeakPwd = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeWeakPwd"));
	public static final int resultCodeOtherErr = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeOtherErr"));
	public static final int resultCodeWrongLoginOrPwd = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeWrongLoginOrPwd"));
	
	//логгер
	public static Logger logger = Logger.getLogger(App.class);

	//запуск приложения
    public static void main( String[] args )	{
    	SpringApplication.run(App.class, args);
    }
}
