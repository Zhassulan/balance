package kz.ztokbayev.qiwi.MobileService.model;

/**
 * Класс модель POJO Клиента (Агента)
 * @author Zhassulan Tokbaev
 * @version 1.0
 * @see Client
 **/

public class Client {

	private Integer id;
	private String login;
	private String password;
	private Double balance;
	
	public Client()	{
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Client(String login, String password) {
		super();
		this.login = login;
		this.password = password;
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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
