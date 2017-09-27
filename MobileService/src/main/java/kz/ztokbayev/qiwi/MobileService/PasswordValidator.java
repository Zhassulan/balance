package kz.ztokbayev.qiwi.MobileService;

import java.util.regex.Pattern;

public class PasswordValidator {
	
	private String password;
	private Integer resultCode;
	
	public PasswordValidator(String password) {
		super();
		this.password = password;
		resultCode = 0;
		Validate();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	private void Validate()	{
		/** ^ - начало строки, (?=.*[0-9]) - как минимум одна цифра, 
		 * (?=.*[a-z]) - как минимум одна маленькая буква
		 * (?=.*[A-Z]) - как минимум одна большая буква
		 * {8,} - длина миниум 8 символов - берём из конфига
		 * $ - конец строки
		 */
        String regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{" + PropsManager.getInstance().getProperty("password_length") + ",}$";
        if (!Pattern.matches(regexp, password))	resultCode = 3;
	}
	
}
