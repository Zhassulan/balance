package kz.ztokbayev.qiwi.MobileService.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import kz.ztokbayev.qiwi.MobileService.App;
import kz.ztokbayev.qiwi.MobileService.model.Client;
import java.io.Reader;
import java.util.List;

//Singleton pattern
public class DatabaseManager {
	
	private static volatile DatabaseManager _instance = null;
	private static SqlSessionFactory sqlSessionFactory;
	private static ClientMapper clientMapper;
	private static Reader reader = null;
	
	static {
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
	        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}
		catch	(Exception ex)
		{
			App.logger.info("Error message: " + ex.getMessage());
			App.logger.error("Stack trace: ", ex);
		}
	}
	
	private DatabaseManager()	{
		Initialize();
	}
	
	public static synchronized DatabaseManager getInstance() {
        if (_instance == null)
        	 synchronized (DatabaseManager.class) {
                 if (_instance == null)
                     _instance = new DatabaseManager();
             }
        return _instance;
    }
	
	public void Initialize()	{
		try	(SqlSession session = sqlSessionFactory.openSession()) 	{
			//adding annotated mappers
			session.getConfiguration().addMapper(ClientMapper.class);
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
	
	public List<Client> GetClientByLoginAndPassword(String login, String password)	{
		List <Client> clients = null;
		try	(SqlSession session = sqlSessionFactory.openSession())	{
			clientMapper = session.getMapper(ClientMapper.class);
			clients = clientMapper.getByLoginAndPassword(login, password);
		}
		catch	(Exception ex)
		{
			App.logger.info("Error message in GetClientByLoginAndPassword: " + ex.getMessage());
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
