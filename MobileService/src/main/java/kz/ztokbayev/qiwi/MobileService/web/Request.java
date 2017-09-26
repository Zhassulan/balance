package kz.ztokbayev.qiwi.MobileService.web;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Request {

	private String requestType;
	private String login;
	private String password;
	
	@JsonGetter("request-type")
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
