package com.ibm.de.messenger.interfaces;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 * @author sjadhav
 *
 */
public interface HttpMethods{

	@GET
	@Path("*/*")
	public String getData();

	@POST
	@Path("*/*")
	public String postData();

	@PUT
	@Path("*/*")
	public String putData();

	@DELETE
	@Path("*/*")
	public String deleteData();
}
