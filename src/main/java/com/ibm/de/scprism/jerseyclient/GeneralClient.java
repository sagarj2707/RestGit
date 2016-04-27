package com.ibm.de.scprism.jerseyclient;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.ibm.de.messenger.interfaces.ClientInterface;
import com.ibm.de.messenger.interfaces.HttpMethods;
import com.ibm.de.messenger.model.Message;

/**
 * @author sjadhav
 *
 */
public class GeneralClient implements HttpMethods, ClientInterface {

	/* Client Configuration and Registration of Authentication feature */
	public ClientConfig clientConfig() {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(httpAuth());
		return clientConfig;
	}

	/*
	 * Authentication using UserId and Password: Supports Basic and Digest based
	 * Authentication
	 */
	public HttpAuthenticationFeature httpAuth() {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.universalBuilder()
				.credentials("username", "password").build();
		return feature;
	}

	/* create a unsecured client */
	public Client newClient() {
		Client myGenClient = ClientBuilder.newClient(clientConfig());
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
			get = "GET successful" + "\nResponse Status : " + response.getStatusInfo() + "\n";
		} else {
			get = String.valueOf("GET Failed with status code: " + response.getStatus());
		}
		return get + response.readEntity(String.class);

	}

	/* HTTP Method POST with parameter */
	public String postData(Message message) {
		String post;
		Response response = webTarget().request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(message, MediaType.APPLICATION_JSON), Response.class);
		if (response.getStatus() == Status.OK.getStatusCode()) {
			post = "POST successful" + "\nResponse Status : " + response.getStatusInfo() + "\nHeader Info :"
					+ response.getHeaders().toString() + "\n";
		} else {
			post = String.valueOf("POST Failed with status code: " + response.getStatus());
		}
		return post + response.readEntity(String.class);
	}

	/* HTTP Method PUT */
	public String putData() {
		String put;
		Response response = webTarget().request(MediaType.APPLICATION_JSON)
				.put(Entity.json(MediaType.APPLICATION_JSON));
		System.out.println(response.getHeaderString("COOKIE"));
		if (response.getStatus() == Status.OK.getStatusCode()) {
			put = "PUT successful";
		} else {
			put = String.valueOf("PUT Failed with status code: " + response.getStatus());
		}
		return put + response.readEntity(String.class);
	}

	/* HTTP Method DELETE */
	public String deleteData() {
		String delete;
		WebTarget plus = webTarget().path("3");
		Response response = plus.request(MediaType.APPLICATION_JSON).delete();
		if (response.getStatus() == Status.OK.getStatusCode()) {
			delete = "DELETE successful" + "\nResponse Status : " + response.getStatusInfo() + "\n";
		} else {
			delete = String.valueOf("DELETE Failed with status code: " + response.getStatus());
		}
		return delete + response.readEntity(String.class);
	}

	/* HTTP Method POST */
	public String postData() {
		String post;
		Response response = webTarget().request(MediaType.APPLICATION_JSON)
				.post(Entity.json(MediaType.APPLICATION_JSON), Response.class);
		System.out.println(response.getHeaderString("user-agent"));
		if (response.getStatus() == Status.OK.getStatusCode()) {
			post = "POST successful";
		} else {
			post = String.valueOf("POST Failed with status code: " + response.getStatus());
		}
		return post;
	}
}
