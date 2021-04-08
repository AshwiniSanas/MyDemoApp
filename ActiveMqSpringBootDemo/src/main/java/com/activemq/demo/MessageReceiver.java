package com.activemq.demo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class is used to route the message from the queue to the mentioned rest endpoint
 * 
 * @author Ashwini.Sanas
 *
 */
@Component
public class MessageReceiver extends RouteBuilder {

	@Value("${inbound.endpoint}")
	private String inboundEndpoint;
	
	@Value("${endpoint}")
	private String endpoint;

	@Override
	public void configure() throws Exception {

		from("jms:queue:" + inboundEndpoint).marshal().json(JsonLibrary.Jackson).to(endpoint);

	}
}