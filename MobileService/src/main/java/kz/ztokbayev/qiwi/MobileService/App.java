package kz.ztokbayev.qiwi.MobileService;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	
	public static Logger logger = Logger.getLogger(App.class);
	
    public static void main( String[] args )	{
    	SpringApplication.run(App.class, args);
    }
}
