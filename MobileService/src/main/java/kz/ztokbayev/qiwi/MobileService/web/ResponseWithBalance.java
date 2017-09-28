package kz.ztokbayev.qiwi.MobileService.web;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * Класс наследник определяющий XML ответ с балансом от контроллера Spring MVC
 * @author Zhassulan Tokbaev
 * @version 1.0
 * @see Response
 **/

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
