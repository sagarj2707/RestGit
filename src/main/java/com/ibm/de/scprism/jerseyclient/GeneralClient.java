package com.ibm.de.scprism.jerseyclient;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.ibm.de.messenger.model.Message;

public class GeneralClient implements HttpMethods, ClientInterface {

	public HttpAuthenticationFeature httpAuth() {
		// HttpAuthenticationFeature feature =
		// HttpAuthenticationFeature.universal("username", "password");
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.universalBuilder()
				.credentials("username", "password").build();
		return feature;
	}

	/* create a unsecured client */
	public Client newClient() {
		Client myGenClient = ClientBuilder.newClient();
		myGenClient.register(httpAuth());
		return myGenClient;
	}

	/* create a secured client */
	public Client secureClient() {
		Client secureClient = ClientBuilder.newBuilder().sslContext(mySslContext()).build();
		secureClient.register(httpAuth());
		return secureClient;
	}

	/* SSL configuration for secured client */
	public SslConfigurator mySslConfigurator() {
		SslConfigurator sslConfig = SslConfigurator.newInstance().trustStoreFile("./truststore_client")
				.trustStorePassword("secret-password-for-truststore").keyStoreFile("./keystore_client")
				.keyPassword("secret-password-for-keystore");
		return sslConfig;
	}

	/* SSLContext object for secured client */
	public SSLContext mySslContext() {
		SSLContext mySsl = mySslConfigurator().createSSLContext();
		return mySsl;
	}

	/* WebTarget i.e. address of the Web Service */
	public WebTarget webTarget() {
		WebTarget webTarget = newClient().target("http://localhost:9999/messenger/webapi/messages");
		return webTarget;
	}

	/* HTTP Method GET */
	public String getData() {
		String get;
		Response response = webTarget().request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == Status.OK.getStatusCode()) {
			get = "post successful";
		} else {
			get = String.valueOf("POST Failed with status code: " + response.getStatus());
		}
		System.out.println(get);
		// System.out.println("Status code: " + response.getStatus());
		return response.readEntity(String.class) + response.getHeaders() + response.getStatusInfo();

	}

	/* HTTP Method POST with parameter */
	public String postData(Message message) {
		String post;
		Response response = webTarget().request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(message, MediaType.APPLICATION_JSON), Response.class);
		System.out.println(response.getHeaderString("user-agent"));
		if (response.getStatus() == Status.OK.getStatusCode()) {
			post = "post successful";
		} else {
			post = String.valueOf("POST Failed with status code: " + response.getStatus());
		}
		return post;
	}

	/* HTTP Method PUT */
	public String putData() {
		String post;
		Response response = webTarget().request(MediaType.APPLICATION_JSON)
				.put(Entity.json(MediaType.APPLICATION_JSON));
		System.out.println(response.getHeaderString("COOKIE"));
		if (response.getStatus() == Status.OK.getStatusCode()) {
			post = "PUT successful";
		} else {
			post = String.valueOf("PUT Failed with status code: " + response.getStatus());
		}
		return post;
	}

	/* HTTP Method DELETE */
	public String deleteData() {
		String delete;
		WebTarget plus = webTarget().path("1");
		Response response = plus.request(MediaType.APPLICATION_JSON).delete();
		System.out.println(response.getHeaders());
		if (response.getStatus() == Status.OK.getStatusCode()) {
			delete = "delete successful";
		} else {
			delete = String.valueOf("DELETE Failed with status code: " + response.getStatus());
		}
		return delete;
	}

	/* HTTP Method POST */
	public String postData() {
		String post;
		Response response = webTarget().request(MediaType.APPLICATION_JSON)
				.post(Entity.json(MediaType.APPLICATION_JSON), Response.class);
		System.out.println(response.getHeaderString("user-agent"));
		if (response.getStatus() == Status.OK.getStatusCode()) {
			post = "post successful";
		} else {
			post = String.valueOf("POST Failed with status code: " + response.getStatus());
		}
		return post;
	}

}
