package br.com.capiteweb.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class Rest extends ResourceConfig {
	public Rest() {
		this.register(new RestBinder());
		this.packages(true, new String[]{"br.com.capiteweb.rest"});
	}
}