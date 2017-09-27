package kz.ztokbayev.qiwi.MobileService.web;

import kz.ztokbayev.qiwi.MobileService.model.*;
import kz.ztokbayev.qiwi.MobileService.db.*;
import kz.ztokbayev.qiwi.MobileService.MD5;
import kz.ztokbayev.qiwi.MobileService.PasswordValidator;
import kz.ztokbayev.qiwi.MobileService.PhoneValidator;
import java.util.List;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/xml")
public class XmlResponseController {
	
	@RequestMapping(value = "new-agt", method = RequestMethod.POST, produces={"application/xml"}, consumes={"application/xml"})	
	public @ResponseBody Response processXMLRequestNewAgent(@RequestBody Request request)	{
		Response response = new Response();
		if (request.getRequestType().equalsIgnoreCase("new-agt"))	{
			
			PhoneValidator phoneValidator = new PhoneValidator(request.getLogin());
			PasswordValidator passwordValidator = new PasswordValidator(request.getPassword());
			
			if (phoneValidator.getResultCode() == 2)	{
				response.setResultCode(2);
				return response;
			}
			if (passwordValidator.getResultCode() == 3)	{
				response.setResultCode(3);
				return response;
			}
			
			List <Client> clients = DatabaseManager.getInstance().GetClientByLogin(request.getLogin());
			if (clients.size() > 0)	{
				response.setResultCode(1);
				return response;
			}
			
			Client client = new Client();
			client.setLogin(request.getLogin());			
			client.setPassword(MD5.getInstance().getHash(request.getPassword()));
			if (DatabaseManager.getInstance().addClient(client))
				response.setResultCode(0);
			else
				//Другая ошибка
				response.setResultCode(5);
		}
	    return response;
	}
	
	@RequestMapping(value = "agt-bal", method = RequestMethod.POST, produces={"application/xml"}, consumes={"application/xml"})	
	public @ResponseBody ResponseWithBalance processXMLRequestGetBalance(@RequestBody Request request)	{
		ResponseWithBalance response = new ResponseWithBalance();
		
		if (request.getRequestType().equalsIgnoreCase("agt-bal"))	{
			
			PhoneValidator phoneValidator = new PhoneValidator(request.getLogin());
			PasswordValidator passwordValidator = new PasswordValidator(request.getPassword());
			
			if (phoneValidator.getResultCode() == 2)	{
				response.setResultCode(2);
				return response;
			}
			if (passwordValidator.getResultCode() == 3)	{
				response.setResultCode(3);
				return response;
			}
			
			List <Client> clients = DatabaseManager.getInstance().GetClientByLoginAndPassword(request.getLogin(), MD5.getInstance().getHash(request.getPassword()));
			if (clients.size() == 1)	{
				response.setResultCode(0);
				Client client = clients.get(0);
				response.setBalance(client.getBalance());
				return response;
			}
			else //не верный логин или пароль
				response.setResultCode(1);
		}
	    return response;
	}
	
}
