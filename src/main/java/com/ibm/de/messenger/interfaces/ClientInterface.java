package com.ibm.de.messenger.interfaces;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 * @author sjadhav
 *
 */
public interface ClientInterface {

	/* Define Client Configuration */
	public ClientConfig clientConfig();

	/* Create instance of a REST Client */
	public Client newClient();

	/* Build a new web resource target. */
	public WebTarget webTarget();

	/* SSL Configuration */
	public SslConfigurator mySslConfigurator();

	/* SSLContext */
	public SSLContext mySslContext();

	/* Authentication for requests */
	public HttpAuthenticationFeature httpAuth();
}
