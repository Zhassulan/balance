package kz.ztokbayev.qiwi.MobileService;

import java.util.regex.Pattern;

public class PhoneValidator {
	
	private int resultCode;
	private String phone;
	
	public PhoneValidator(String phone)	{
		super();
		this.phone = phone;
		Validate();
	}
	
	public int getResultCode() {
		return resultCode;
	}
	
	private void Validate()	{
		resultCode = 0;
		//[0-9] только цифры, длина 10 (берём из конфига)
		if (!Pattern.matches("^[0-9]{" + PropsManager.getInstance().getProperty("mobile_length") + "}$", phone))	{
			resultCode = 2;
		}
	}
	
}
