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
	//логгер
	public static Logger logger = Logger.getLogger(App.class);

	//запуск приложения
    public static void main( String[] args )	{
    	SpringApplication.run(App.class, args);
    }
}
