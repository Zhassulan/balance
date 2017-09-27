package kz.ztokbayev.qiwi.MobileService.web;

import com.fasterxml.jackson.annotation.JsonGetter;

public class ResponseWithBalance extends Response {
	
	private Double balance;
	
	@JsonGetter("bal")
	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
