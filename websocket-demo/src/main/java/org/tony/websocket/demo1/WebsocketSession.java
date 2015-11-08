package org.tony.websocket.demo1;

import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WebsocketSession {

	private static final ConcurrentHashMap<String, Session> sessionCache = new ConcurrentHashMap<>();

	private final static Logger logger = LoggerFactory.getLogger(WebsocketSession.class);

	public static void put(String machineCode, Session session) {
		if (session != null) {
			sessionCache.put(machineCode, session);
		}

	}

	public static Session get(String machineCode) {
		return sessionCache.get(machineCode);
	}

	public static void remove(String machineCode) {
	
		sessionCache.remove(machineCode);
	}

	public static boolean sendMessage(String message, String machineCode) {
		Session session = sessionCache.get(machineCode);
		if (session != null && session.isOpen()) {
			try {
				session.getBasicRemote().sendText(message);
				return true;
			} catch (Exception e) {
				logger.error("send message error!", e);

			}
		}
		return false;
	}

	public static boolean sendMessageToAll(String message) {

		Set<Entry<String, Session>> entrySet = sessionCache.entrySet();
		for (Entry<String, Session> entry : entrySet) {
			Session session = entry.getValue();
			if (session != null && session.isOpen()) {
				try {
					session.getBasicRemote().sendText(message);
				} catch (Exception e) {
					logger.error("send message error!", e);
					sessionCache.remove(entry.getKey());

				}
			}
		}

		return true;
	}

}
