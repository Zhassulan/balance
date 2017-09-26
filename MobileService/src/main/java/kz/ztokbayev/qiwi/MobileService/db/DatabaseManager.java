package kz.ztokbayev.qiwi.MobileService.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;

import kz.ztokbayev.qiwi.MobileService.App;
import kz.ztokbayev.qiwi.MobileService.model.Client;
import kz.ztokbayev.qiwi.MobileService.db.*;

import java.io.Reader;
import java.util.List;

public class DatabaseManager {
	
	private static SqlSessionFactory sqlSessionFactory;
	private static ClientMapper clientMapper;
	private static Reader reader = null;
	
	static {
		try {
			reader = Resources.getResourceAsReader("xml/mybatis-config.xml");
	        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}
		catch	(Exception ex)
		{
			App.logger.info("Error message: " + ex.getMessage());
			App.logger.error("Stack trace: ", ex);
		}
	}
	
	public DatabaseManager()	{
		Initialize();
	}
	
	public void Initialize()	{
		try	(SqlSession session = sqlSessionFactory.openSession()) 	{
			//adding annotated mappers
			session.getConfiguration().addMapper(ClientMapper.class);
			reader = Resources.getResourceAsReader("sql/schema.sql");
			ScriptRunner runner = new ScriptRunner(session.getConnection());
			runner.setLogWriter(null);
			runner.setErrorLogWriter(null);
			runner.runScript(reader);
			session.commit();
			reader.close();
			App.logger.info("Database is initialized.");
			}
			catch	(Exception ex)
			{
				App.logger.info("Error message: " + ex.getMessage());
				App.logger.error("Stack trace: ", ex);
			}
	}
	
	public List<Client> GetClientByLogin(String login)	{
		List <Client> clients = null;
		try	(SqlSession session = sqlSessionFactory.openSession())	{
			clientMapper = session.getMapper(ClientMapper.class);
			clients = clientMapper.getByLogin(login);
		}
		catch	(Exception ex)
		{
			App.logger.info("Error message in GetClientByLogin: " + ex.getMessage());
			App.logger.error("Stack trace: ", ex);
		}	
		return clients;
	}
	
	public boolean addClient(Client client)	{
		try(SqlSession session = sqlSessionFactory.openSession()) {
			clientMapper = session.getMapper(ClientMapper.class);
			clientMapper.add(client);
			session.commit();
			return true;
		}
		catch	(Exception ex)
		{
			App.logger.info("Error message in addClient: " + ex.getMessage());
			App.logger.error("Stack trace: ", ex);
			return false;
		}
	}
	
}
