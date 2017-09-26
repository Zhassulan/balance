package kz.ztokbayev.qiwi.MobileService.db;

import java.util.List;
import org.apache.ibatis.annotations.*;

import kz.ztokbayev.qiwi.MobileService.model.Client;

public interface ClientMapper {

	final String getByLogin = "select * from clients where login = #{login}";
	final String add = "insert into clients (login, password) values (#{login}, #{password})";
	
	@Select(getByLogin)
	List <Client> getByLogin(String login);
	
	@Insert(add)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void add(Client client);
	
}
