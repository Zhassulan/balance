package kz.ztokbayev.qiwi.MobileService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	private String password;
	private StringBuffer hash; 
	
	public MD5(String password)	{
		super();
		this.password = password;
		setHash();
	}
	
	private void setHash()	{
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			hash = new StringBuffer();
			for (byte b : digest) {
				hash.append(String.format("%02x", b & 0xff));
			}	
		}
		catch (Exception ex) {
			App.logger.info("Error message in setHash: " + ex.getMessage());
			App.logger.error("Stack trace: ", ex);
		}
		
	}
	
	public String getHash()	{
		return hash.toString();
	}
	
}
