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
		if (phone.length() < length || phone.length() > length)	{
			resultCode = 2;
		}
		if (!Pattern.matches("[a-zA-Z]+", phone))	{
			resultCode = 2;
		}
	}
	
}
