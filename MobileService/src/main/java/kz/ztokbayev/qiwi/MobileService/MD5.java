package kz.ztokbayev.qiwi.MobileService;

import java.security.MessageDigest;

/**
 * Класс для получения хеша из объекта String, паттерн синглтон с "ленивой" 
 * инициализацией (по запросу) 
 * @author Zhassulan Tokbaev
 * @version 1.0
 * @see MD5
 * 
*/

public class MD5 {
	
	/** ссылка на будущий экземпляр класса **/
	private static volatile MD5 _instance = null;
	
	/** конструктор
	 * @see MD5()
	 */
	private MD5()	{
	}
	
	/** инициализация класса, ссылки и получение ссылки
	 * @see getInstance()
	 */
	public static synchronized MD5 getInstance() {
        if (_instance == null)
        	 synchronized (MD5.class) {
                 if (_instance == null)
                     _instance = new MD5();
             }
        return _instance;
    }
	
	/** метод распечатки двумерного массива в виде матрицы
	 * @param String password пароль
	 * @see getHash
	 */
	public String getHash(String password)	{
		StringBuffer hash = null;
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			hash = new StringBuffer();
			for (byte b : digest) {
				hash.append(String.format("%02x", b & 0xff));
			}	
		}
		catch (Exception ex) {
			App.logger.info("Error message in getHash: " + ex.getMessage());
			App.logger.error("Stack trace: ", ex);
		}
		return hash.toString();
	}
	
}
