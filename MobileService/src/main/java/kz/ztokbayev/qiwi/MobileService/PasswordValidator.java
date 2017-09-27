package kz.ztokbayev.qiwi.MobileService;

import java.util.regex.Pattern;

/**
 * Класс для проверки получения хеша из объекта String, паттерн синглтон с "ленивой" 
 * инициализацией (по запросу).
 * @author Zhassulan Tokbaev
 * @version 1.0
 * @see PasswordValidator
 * 
*/

public class PasswordValidator {
	
	/** ссылка на будущий экземпляр класса **/
	private static volatile PasswordValidator _instance = null;
	
	/** конструктор
	 * @see PasswordValidator
	 **/
	private PasswordValidator() {
	}

	/** инициализация класса, ссылки и получение ссылки
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
	
	/** метод проверки пароля на сложность, устанавливает код результата в значение 3
	 * @see Validate
	 **/
	public int Validate(String password)	{
		/** ^ - начало строки, (?=.*[0-9]) - как минимум одна цифра, 
		 * (?=.*[a-z]) - как минимум одна маленькая буква
		 * (?=.*[A-Z]) - как минимум одна большая буква
		 * {8,} - длина миниум 8 символов - берём из конфига
		 * $ - конец строки
		 */
        String regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{" + PropsManager.getInstance().getProperty("password_length") + ",}$";
        if (!Pattern.matches(regexp, password))	
        	return 3;
        else 
        	return 0;
	}
	
}
