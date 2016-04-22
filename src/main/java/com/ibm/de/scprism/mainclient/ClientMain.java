package com.ibm.de.scprism.mainclient;

import com.ibm.de.messenger.model.Message;
import com.ibm.de.scprism.restfacade.RestFacade;

public class ClientMain {
	public static String resourceUri = "http://localhost:9999/messenger/webapi/messages/";
	public static String resourceType = "application/json";
	public static Message message = new Message(3L, "Hello from JAX-RS Client", "sagar");

	public static void main(String[] args) {
		RestFacade facade = new RestFacade();
		// facade.runJerseyFacade(resourceUri, resourceType);

		// facade.runJaxFacade(resourceUri, resourceType);
		// System.out.println(facade.runJaxFacade(resourceUri, resourceType));
		System.out.println(facade.postJaxFacade(resourceUri, resourceType, message));
	}

}