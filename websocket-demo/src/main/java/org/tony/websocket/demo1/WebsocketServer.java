/*package org.tony.websocket.demo1;

import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/websocket.ws/{userId}/{machineCode}",configurator=CustomConfigurator.class)
public class WebsocketServer {
	private static final Logger logger = LoggerFactory.getLogger(WebsocketServer.class);
	public static final ConcurrentHashMap<String, Session> sessionCache = new ConcurrentHashMap<>();

	@OnOpen
	public void onOpen(Session session, @PathParam("userId") String userId,
			@PathParam("machineCode") String machineCode) {
		logger.info("userId={},machineCode={}", userId, machineCode);
		logger.info("OPen>>>>session id =" + session.getId());
		session.setMaxTextMessageBufferSize(32);
		System.out.println(session.getMaxIdleTimeout()+" 最大输入大小："+session.getMaxTextMessageBufferSize());
		WebsocketSession.put(machineCode, session);

	}

	@OnMessage
	public void onMessage(Session session, String message, @PathParam("machineCode") String machineCode) {
		logger.info(" recive message=" + message);
		logger.info("session id =" + session.getId());
		WebsocketSession.sendMessageToAll("mchineCode="+machineCode+message);

	}

	@OnError
	public void onError(Throwable throwable, Session session, @PathParam("machineCode") String machineCode) {
		//System.out.println(">>>>>>>>>>>>>>>>>>>"+session.isOpen());
		//sessionCache.remove(machineCode);
		//logger.error("{}设备异常下线",machineCode,throwable);
		WebsocketSession.remove(machineCode);
		throwable.printStackTrace();
		
	}

	@OnClose
	public void onClose(Session session, @PathParam("machineCode") String machineCode) {
		//System.out.println(">>>>>>>>>>>>>>>>>>>"+session.isOpen());
		//sessionCache.remove(machineCode);
		logger.info("{}设备正常下线!",machineCode);
		WebsocketSession.remove(machineCode);
	}

}
*/