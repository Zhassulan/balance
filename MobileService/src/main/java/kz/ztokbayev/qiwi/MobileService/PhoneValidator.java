package kz.ztokbayev.qiwi.MobileService;

import java.util.regex.Pattern;

public class PhoneValidator {
	
	/** ссылка на будущий экземпляр класса **/
	private static volatile PhoneValidator _instance = null;
	
	private PhoneValidator()	{
	}
	
	/** инициализация класса, ссылки и получение ссылки
	 * @see getInstance()
	 */
	public static synchronized PhoneValidator getInstance() {
        if (_instance == null)
        	 synchronized (PhoneValidator.class) {
                 if (_instance == null)
                     _instance = new PhoneValidator();
             }
        return _instance;
    }
	
	public int Validate(String phone)	{
		//[0-9] только цифры, длина 10 (берём из конфига)
		if (!Pattern.matches("^[0-9]{" + PropsManager.getInstance().getProperty("mobile_length") + "}$", phone))
			return 2;
		else return 0;
	}

}
