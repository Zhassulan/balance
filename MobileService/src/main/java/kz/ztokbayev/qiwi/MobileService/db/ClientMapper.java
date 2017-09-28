package kz.ztokbayev.qiwi.MobileService.db;

import java.util.List;
import org.apache.ibatis.annotations.*;
import kz.ztokbayev.qiwi.MobileService.model.Client;

/**
 * Класс для маппинга (связки) объектов с запросами SQL в базу данных, согласно технологии MyBatis 
 * @author Zhassulan Tokbaev
 * @version 1.0
 * @see ClientMapper
 **/

public interface ClientMapper {
	/**
	 * SQL запросы для агента, получить по логину, добавить и получить по логину и паролю из базы
	 */
	final String getByLogin = "select * from clients where login = #{login}";
	final String add = "insert into clients (login, password) values (#{login}, #{password})";
	final String getByLoginAndPassword = "select * from clients where login = #{login} "
										+ "and password = #{password}";
	
	@Select(getByLogin)
	List <Client> getByLogin(String login);
	
	@Insert(add)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void add(Client client);
	
	@Select(getByLoginAndPassword)
	List <Client> getByLoginAndPassword(@Param("login")String login, @Param("password")String password);
}
