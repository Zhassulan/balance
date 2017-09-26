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
	
	private DatabaseManager dbmanager = new DatabaseManager();

	@RequestMapping(value = "new-agt", method = RequestMethod.POST, produces={"application/xml"}, consumes={"application/xml"})	
	public @ResponseBody Response processXMLJsonRequest(@RequestBody Request request)	{
		Response response = new Response();
		if (request.getRequestType().equalsIgnoreCase("new-agt"))	{
			
			PhoneValidator phoneValidator = new PhoneValidator(request.getLogin(), 10);
			PasswordValidator passwordValidator = new PasswordValidator(request.getPassword());
			
			if (phoneValidator.getResultCode() == 2)	{
				response.setResultCode(2);
				return response;
			}
			if (passwordValidator.getResultCode() == 3)	{
				response.setResultCode(3);
				return response;
			}
			
			List <Client> clients = dbmanager.GetClientByLogin(request.getLogin());
			if (clients.size() > 0)	{
				response.setResultCode(1);
				return response;
			}
			
			Client client = new Client();
			client.setLogin(request.getLogin());			
			MD5 md5 = new MD5(request.getPassword());
			client.setPassword(md5.getHash());
			if (dbmanager.addClient(client))
				response.setResultCode(0);
			else
				response.setResultCode(5);
		}
	    return response;
	}
	
}
