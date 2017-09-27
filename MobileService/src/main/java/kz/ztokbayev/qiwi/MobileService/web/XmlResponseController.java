package kz.ztokbayev.qiwi.MobileService.web;

import kz.ztokbayev.qiwi.MobileService.model.*;
import kz.ztokbayev.qiwi.MobileService.db.*;
import kz.ztokbayev.qiwi.MobileService.MD5;
import kz.ztokbayev.qiwi.MobileService.PasswordValidator;
import kz.ztokbayev.qiwi.MobileService.PhoneValidator;
import kz.ztokbayev.qiwi.MobileService.PropsManager;

import java.util.List;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/xml")
public class XmlResponseController {
	
	private static final int resultCodeSuccess = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeSuccess"));
	private static final int resultCodeAgtExists = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeAgtExists"));
	private static final int resultCodeBadPhoneFormat = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeBadPhoneFormat"));
	private static final int resultCodeWeakPwd = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeWeakPwd"));
	private static final int resultCodeOtherErr = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeOtherErr"));
	private static final int resultCodeWrongLoginOrPwd = Integer.parseInt(PropsManager.getInstance().getProperty("resultCodeWrongLoginOrPwd"));
	
	@RequestMapping(value = "new-agt", method = RequestMethod.POST, produces={"application/xml"}, consumes={"application/xml"})	
	public @ResponseBody Response processXMLRequestNewAgent(@RequestBody Request request)	{
		Response response = new Response();
		if (request.getRequestType().equalsIgnoreCase("new-agt"))	{
			
			if (PhoneValidator.getInstance().Validate(request.getLogin()) == resultCodeBadPhoneFormat)	{
				response.setResultCode(resultCodeBadPhoneFormat);
				return response;
			}
			if (PasswordValidator.getInstance().Validate(request.getPassword()) == resultCodeWeakPwd)	{
				response.setResultCode(resultCodeWeakPwd);
				return response;
			}
			
			List <Client> clients = DatabaseManager.getInstance().GetClientByLogin(request.getLogin());
			if (clients.size() > 0)	{
				response.setResultCode(resultCodeAgtExists);
				return response;
			}
			
			Client client = new Client();
			client.setLogin(request.getLogin());			
			client.setPassword(MD5.getInstance().getHash(request.getPassword()));
			if (DatabaseManager.getInstance().addClient(client))
				response.setResultCode(resultCodeSuccess);
			else
				response.setResultCode(resultCodeOtherErr);
		}
	    return response;
	}
	
	@RequestMapping(value = "agt-bal", method = RequestMethod.POST, produces={"application/xml"}, consumes={"application/xml"})	
	public @ResponseBody ResponseWithBalance processXMLRequestGetBalance(@RequestBody Request request)	{
		ResponseWithBalance response = new ResponseWithBalance();
		
		if (request.getRequestType().equalsIgnoreCase("agt-bal"))	{
			List <Client> clients = DatabaseManager.getInstance().GetClientByLoginAndPassword(request.getLogin(), MD5.getInstance().getHash(request.getPassword()));
			if (clients.size() > 0)	{
				response.setResultCode(resultCodeSuccess);
				Client client = clients.get(0);
				response.setBalance(client.getBalance());
				return response;
			}
			else //не верный логин или пароль
				response.setResultCode(resultCodeWrongLoginOrPwd);
		}
	    return response;
	}
	
}
