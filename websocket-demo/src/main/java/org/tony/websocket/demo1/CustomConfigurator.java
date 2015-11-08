package org.tony.websocket.demo1;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomConfigurator extends Configurator{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomConfigurator.class);

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
	
		//logger.info(request.getUserPrincipal());
		
		logger.info(request.getHeaders().toString());
		
	}

	@Override
	public boolean checkOrigin(String originHeaderValue) {
		//检查origin源
		return super.checkOrigin(originHeaderValue);
	}
	
	
	

}
