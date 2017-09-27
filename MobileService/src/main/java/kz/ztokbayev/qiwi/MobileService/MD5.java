package kz.ztokbayev.qiwi.MobileService;

import java.security.MessageDigest;

public class MD5 {
	
	private static volatile MD5 _instance = null;
	
	private MD5()	{
	}
	
	public static synchronized MD5 getInstance() {
        if (_instance == null)
        	 synchronized (MD5.class) {
                 if (_instance == null)
                     _instance = new MD5();
             }
        return _instance;
    }
	
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
