package kz.ztokbayev.qiwi.MobileService.web;

import kz.ztokbayev.qiwi.MobileService.model.*;
import kz.ztokbayev.qiwi.MobileService.db.*;
import kz.ztokbayev.qiwi.MobileService.App;
import kz.ztokbayev.qiwi.MobileService.MD5;
import kz.ztokbayev.qiwi.MobileService.PasswordValidator;
import kz.ztokbayev.qiwi.MobileService.PhoneValidator;

import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/xml")
public class XmlResponseController {
	
	private DatabaseManager dbmanager = new DatabaseManager();
	private int resultCode;

	@RequestMapping(value = "new-agt", method = RequestMethod.POST, produces={"application/xml"}, consumes={"application/xml"})	
	public @ResponseBody Response processXMLJsonRequest(@RequestBody Request request)	{
		Response response = new Response();
		if (request.getRequestType().equalsIgnoreCase("new-agt"))	{
			PhoneValidator phv = new PhoneValidator(request.getLogin());
			PasswordValidator psv = new PasswordValidator(request.getPassword());
			 
			if (phv.getResultCode() != 2 || psv.getResultCode() != 3)	{
				List <Client> clients = dbmanager.GetClientByLogin(request.getLogin());
				if (clients.size() > 0)	{
					response.setResultCode(1);
				}
			}
			
			Client client = new Client();
			client.setLogin(request.getLogin());
			List <Client> clients = dbmanager.GetClientByLogin(request.getLogin());
			if (clients.size() > 0)	{
				response.setResultCode(1);
			}
			else
			{
				
			}
			
			MD5 md5 = new MD5(request.getPassword());
			client.setPassword(md5.getHash());
			if (dbmanager.addClient(client))	{
				response.setResultCode(0);
			}
			else	{
				response.setResultCode(1);
			}
				
		}
		response.setResultCode(0);
	    return response;
	}
	
}
