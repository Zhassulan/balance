package kz.ztokbayev.qiwi.MobileService;

public class PasswordValidator {
	
	private String password;
	private Integer resultCode;
	
	public PasswordValidator(String password) {
		super();
		this.password = password;
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
        String regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,8}$";
        if (password.matches(regexp))
        	resultCode = 0;
        else 
        	resultCode = 3;
	}
	
}
