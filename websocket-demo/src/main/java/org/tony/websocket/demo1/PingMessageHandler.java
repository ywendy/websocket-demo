package org.tony.websocket.demo1;

import javax.websocket.MessageHandler;
import javax.websocket.PongMessage;

public class PingMessageHandler implements MessageHandler.Whole<PongMessage>{

	@Override
	public void onMessage(PongMessage message) {
		System.err.println("this is ping message!");
		
	}

}
