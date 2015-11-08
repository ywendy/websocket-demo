package org.tony.websocket.demo1;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

@ServerEndpoint(value = "/websocket.ws/{machineCode}", configurator = CustomConfigurator.class, encoders = {
		MessageEncoder.class }, decoders = { MessageDecoder.class })
public class WebsocketServerDemo1 {
	private static final Logger logger = LoggerFactory.getLogger(WebsocketServerDemo1.class);

	@OnOpen
	public void onOpen(Session session, @PathParam("machineCode") String machineCode) {
		logger.info("machineCode={}", machineCode);
		logger.info("OPen>>>>session id =" + session.getId());
		// session.setMaxTextMessageBufferSize(32);
		System.out.println(session.getMaxIdleTimeout() + " 最大输入大小：" + session.getMaxTextMessageBufferSize());
		WebsocketSession.put(machineCode, session);

	}

	@OnMessage
	public void onMessage(Session session, Message message, @PathParam("machineCode") String machineCode)
			throws Exception {
		logger.info(" recive message=" + JSON.toJSONString(message));
		logger.info("session id =" + session.getId());

		session.getBasicRemote().sendObject(message);

	}

	@OnMessage
	public void echoPongMessage(PongMessage pm) {
		// 无处理
		logger.info("pong message :" + pm.toString());
	}

	@OnError
	public void onError(Throwable throwable, Session session, @PathParam("machineCode") String machineCode) {
		// System.out.println(">>>>>>>>>>>>>>>>>>>"+session.isOpen());
		// sessionCache.remove(machineCode);
		// logger.error("{}设备异常下线",machineCode,throwable);
		WebsocketSession.remove(machineCode);
		throwable.printStackTrace();

	}

	@OnClose
	public void onClose(Session session, @PathParam("machineCode") String machineCode) {
		// System.out.println(">>>>>>>>>>>>>>>>>>>"+session.isOpen());
		// sessionCache.remove(machineCode);
		logger.info("{}设备正常下线!", machineCode);
		WebsocketSession.remove(machineCode);
	}

}
