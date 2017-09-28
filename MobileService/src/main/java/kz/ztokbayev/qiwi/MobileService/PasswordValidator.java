package kz.ztokbayev.qiwi.MobileService;

import java.util.regex.Pattern;
import kz.ztokbayev.qiwi.MobileService.App;

/**
 * Класс с методом проверки пароля на сложность и его длину
 * Паттерн синглтон с ленивой инициализацией (т.е. по запросу)  
 * @author Zhassulan Tokbaev
 * @version 1.0
 * @see PasswordValidator
 **/

public class PasswordValidator {
	
	/** ссылка на будущий экземпляр класса **/
	private static volatile PasswordValidator _instance = null;
	
	/** конструктор
	 * @see PasswordValidator
	 **/
	private PasswordValidator() {
	}

	/** метод получения ссылки экземпляра класса
	 * @see getInstance()
	 */
	public static synchronized PasswordValidator getInstance() {
        if (_instance == null)
        	 synchronized (PasswordValidator.class) {
                 if (_instance == null)
                     _instance = new PasswordValidator();
             }
        return _instance;
    }
	
	/** метод проверки пароля на сложность
	 * @see Validate
	 * @param String password пароль
	 **/
	public int Validate(String password)	{
		/** ^ - начало строки, (?=.*[0-9]) - как минимум одна цифра, 
		 * (?=.*[a-z]) - как минимум одна маленькая буква
		 * (?=.*[A-Z]) - как минимум одна большая буква
		 * {8,} - длина миниум 8 символов - берём из конфига
		 * $ - конец строки
		 */
        String regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{" + App.passwordLength + ",}$";
        if (!Pattern.matches(regexp, password))	
        	return App.resultCodeWeakPwd;
        else 
        	return App.resultCodeSuccess;
	}
	
}
