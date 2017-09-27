package kz.ztokbayev.qiwi.MobileService.web;

import kz.ztokbayev.qiwi.MobileService.*;
import kz.ztokbayev.qiwi.MobileService.model.*;
import kz.ztokbayev.qiwi.MobileService.db.*;

import java.util.List;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/xml")
public class XmlResponseController {
	
	@RequestMapping(value = "new-agt", method = RequestMethod.POST, produces={"application/xml"}, consumes={"application/xml"})	
	public @ResponseBody Response processXMLRequestNewAgent(@RequestBody Request request)	{
		Response response = new Response();
		if (request.getRequestType().equalsIgnoreCase("new-agt"))	{
			
			if (PhoneValidator.getInstance().Validate(request.getLogin()) == App.resultCodeBadPhoneFormat)	{
				response.setResultCode(App.resultCodeBadPhoneFormat);
				return response;
			}
			if (PasswordValidator.getInstance().Validate(request.getPassword()) == App.resultCodeWeakPwd)	{
				response.setResultCode(App.resultCodeWeakPwd);
				return response;
			}
			
			List <Client> clients = DatabaseManager.getInstance().GetClientByLogin(request.getLogin());
			if (clients.size() > 0)	{
				response.setResultCode(App.resultCodeAgtExists);
				return response;
			}
			
			Client client = new Client();
			client.setLogin(request.getLogin());			
			client.setPassword(MD5.getInstance().getHash(request.getPassword()));
			if (DatabaseManager.getInstance().addClient(client))
				response.setResultCode(App.resultCodeSuccess);
			else
				response.setResultCode(App.resultCodeOtherErr);
		}
	    return response;
	}
	
	@RequestMapping(value = "agt-bal", method = RequestMethod.POST, produces={"application/xml"}, consumes={"application/xml"})	
	public @ResponseBody ResponseWithBalance processXMLRequestGetBalance(@RequestBody Request request)	{
		ResponseWithBalance response = new ResponseWithBalance();
		
		if (request.getRequestType().equalsIgnoreCase("agt-bal"))	{
			List <Client> clients = DatabaseManager.getInstance().GetClientByLoginAndPassword(request.getLogin(), MD5.getInstance().getHash(request.getPassword()));
			if (clients.size() > 0)	{
				response.setResultCode(App.resultCodeSuccess);
				Client client = clients.get(0);
				response.setBalance(client.getBalance());
				return response;
			}
			else //не верный логин или пароль
				response.setResultCode(App.resultCodeWrongLoginOrPwd);
		}
	    return response;
	}
	
}
