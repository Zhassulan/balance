package kz.ztokbayev.qiwi.MobileService;

import java.util.regex.Pattern;
import kz.ztokbayev.qiwi.MobileService.App;

/**
 * Класс для проверки получения хеша из объекта String, паттерн синглтон с "ленивой" 
 * инициализацией (по запросу).
 * @author Zhassulan Tokbaev
 * @version 1.0
 * @see PhoneValidator
 * 
*/

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
	
	/** метод проверки формата номера телефона
	 * @see Validate()
	 * @param String phone номер
	 */
	public int Validate(String phone)	{
		//[0-9] только цифры, длина 10
		if (!Pattern.matches("^[0-9]{" + App.mobileLength + "}$", phone))
			return App.resultCodeBadPhoneFormat;
		else return App.resultCodeSuccess;
	}

}
