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
		if (phone.length() < 10 || phone.length() > 10)	{
			resultCode = 2;
		}
		if (!Pattern.matches("[a-zA-Z]+", phone))	{
			resultCode = 2;
		}
	}
	
}
