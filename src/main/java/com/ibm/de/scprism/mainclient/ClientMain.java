package com.ibm.de.scprism.mainclient;

import java.util.Date;

import com.ibm.de.messenger.model.Message;
import com.ibm.de.messenger.service.MessageService;
import com.ibm.de.scprism.restfacade.RestFacade;

public class ClientMain {
	public static String resourceUri = "http://localhost:9999/messenger/webapi/messages";
	public static String resourceType = "application/json";
	public static Message message = new Message();

	public static void main(String[] args) {
		RestFacade facade = new RestFacade();
		// facade.runJerseyFacade(resourceUri, resourceType);

		// facade.runJaxFacade(resourceUri, resourceType);
		System.out.println(facade.getJaxFacade(resourceUri, resourceType));
/*		message.setAuthor("shankar");
		message.setCreated(new Date());
		message.setMessage("POST method called");*/
		//System.out.println(message.getAuthor() + message.getCreated() + message.getMessage());
		//System.out.println(facade.postJaxFacade(resourceUri, resourceType, message));
		
		
		//System.out.println(facade.deleteJaxFacade(resourceUri, resourceType));
	}

}