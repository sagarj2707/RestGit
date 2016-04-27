package com.ibm.de.scprism.restfacade;

import com.ibm.de.messenger.model.Message;
import com.ibm.de.scprism.jerseyclient.ClientResources;
import com.ibm.de.scprism.jerseyclient.GeneralClient;

/**
 * @author sjadhav
 *
 */
public class RestFacade {

	public static ClientResources resource;

	private static GeneralClient jaxClient;

	public RestFacade() {
		resource = new ClientResources();
		jaxClient = new GeneralClient();
	}

	/* JAX Facade Method */
	public String getJaxFacade(String resourceUri, String resourceType) {
		resource.setResourceURI(resourceUri);
		resource.setResourceType(resourceType);
		return jaxClient.getData();
	}

	public String postJaxFacade(String resourceUri, String resourceType, Message message) {
		resource.setResourceURI(resourceUri);
		resource.setResourceType(resourceType);
		return jaxClient.postData(message);
	}

	public String deleteJaxFacade(String resourceUri, String resourceType) {
		resource.setResourceURI(resourceUri);
		resource.setResourceType(resourceType);
		return jaxClient.deleteData();
	}

}
