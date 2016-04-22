package com.ibm.de.scprism.jerseyclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ibm.de.messenger.model.Message;
import com.ibm.de.scprism.restfacade.RestFacade;

public class GeneralClient implements HttpMethods {

	public Client genClient() {
		Client myGenClient = ClientBuilder.newClient();
		return myGenClient;
	}

	public WebTarget genWebTarget() {
		WebTarget webTarget = genClient().target(RestFacade.resource.getResourceURI());
		return webTarget;
	}

	
	public String getData() {
		// TODO Auto-generated method stub
		Response response = genWebTarget().request(MediaType.APPLICATION_JSON).get();
		System.out.println("Status code: " + response.getStatus());
		// System.out.println("Result: \n" + response.readEntity(String.class));
		return response.readEntity(String.class);

	}

	public String postData(Message message) {
		// TODO Auto-generated method stub
		String navigation;
		Response response = genWebTarget().request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(message, MediaType.APPLICATION_JSON), Response.class);
		if (response.getStatus() == Status.CREATED.getStatusCode()) {
			navigation = "post successful";
		} else {
			navigation = String.valueOf("Response: "+response.getStatus());
		}
		return navigation;
	}

	
	public String putData() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String deleteData() {
		// TODO Auto-generated method stub
		return null;
	}

	public String postData() {
		// TODO Auto-generated method stub
		return null;
	}

}
