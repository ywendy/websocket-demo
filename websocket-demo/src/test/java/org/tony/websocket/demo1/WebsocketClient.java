package org.tony.websocket.demo1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;

@ClientEndpoint
public class WebsocketClient {

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Connected to endpoint: " + session.getBasicRemote());
		try {
			session.getBasicRemote().sendPong(ByteBuffer.wrap("mess".getBytes()));
			session.getBasicRemote().sendPing(ByteBuffer.wrap("1".getBytes()));
		} catch (IOException ex) {
		}
	}

	@OnMessage
	public void onMessage(String message) {
		System.out.println(message);
	}

	@OnMessage
	public void onPongMessage(PongMessage message) {
		System.out.println("on PongMessage :" + byteBufferToString(message.getApplicationData()));
	}

	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
	}

	public static String byteBufferToString(ByteBuffer buffer) {
		CharBuffer charBuffer = null;
		try {
			Charset charset = Charset.forName("UTF-8");
			CharsetDecoder decoder = charset.newDecoder();
			charBuffer = decoder.decode(buffer);
			buffer.flip();
			return charBuffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
