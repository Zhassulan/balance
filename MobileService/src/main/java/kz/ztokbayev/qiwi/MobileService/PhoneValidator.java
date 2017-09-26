package kz.ztokbayev.qiwi.MobileService;

import java.util.regex.Pattern;

public class PhoneValidator {
	
	private int resultCode;
	private String phone;
	private int length;

	public PhoneValidator(String phone, int length)	{
		super();
		this.phone = phone;
		this.length = length;
		Validate();
	}
	
	public int getResultCode() {
		return resultCode;
	}
	
	private void Validate()	{
		resultCode = 0;
		//[0-9] только цифры, длина 10
		if (!Pattern.matches("^[0-9]{10}$", phone))	{
			resultCode = 2;
		}
	}
	
}
