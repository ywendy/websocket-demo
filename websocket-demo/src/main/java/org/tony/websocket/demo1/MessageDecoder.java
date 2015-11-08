package org.tony.websocket.demo1;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class MessageDecoder implements Decoder.Text<Message> {
	static final Logger logger = LoggerFactory.getLogger(MessageDecoder.class);

	@Override
	public void init(EndpointConfig config) {
		logger.info("MessageDecoder -init method called");

	}

	@Override
	public void destroy() {
		logger.info("MessageDecoder - destroy method called");

	}

	@Override
	public Message decode(String jsonString) throws DecodeException {
		logger.info("decode:" + jsonString);
		return JSON.parseObject(jsonString, Message.class);
	}

	@Override
	public boolean willDecode(String str) {
		try {
			// Check if incoming message is valid JSON
			if (str != null && !"".equals(str.trim())) {
				JSON.parseObject(str);
				return true;
			}

		} catch (Exception e) {
			logger.error("incoming message --{}--is invalid!", str, e);
		}
		return false;
	}

}
