package kz.ztokbayev.qiwi.MobileService.web;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Класс определяющий XML ответ от контроллера Spring MVC
 * @author Zhassulan Tokbaev
 * @version 1.0
 * @see Response
 **/

@JsonRootName(value = "response")
public class Response {

	private int resultCode;

	@JsonGetter("result-code")
	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}
