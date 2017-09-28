package kz.ztokbayev.qiwi.MobileService;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
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
	/** 
	 * константы из файла конфигурации, смысл в названии
	 */
	public static final int resultCodeSuccess = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeSuccess"));
	public static final int resultCodeAgtExists = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeAgtExists"));
	public static final int resultCodeBadPhoneFormat = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeBadPhoneFormat"));
	public static final int resultCodeWeakPwd = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeWeakPwd"));
	public static final int resultCodeOtherErr = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeOtherErr"));
	public static final int resultCodeWrongLoginOrPwd = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeWrongLoginOrPwd"));
	public static final int passwordLength = Integer.parseInt(PropsManager.getInstance().getProperty("passwordLength"));
	public static final int mobileLength = Integer.parseInt(PropsManager.getInstance().getProperty("mobileLength"));
	
	/** 
	 * логгер
	 */
	public static Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args )	{
    	SpringApplication.run(App.class, args);
    }
}
