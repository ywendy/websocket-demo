package org.tony.websocket.demo1;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class MessageEncoder implements Encoder.Text<Message> {
	static final Logger logger = LoggerFactory.getLogger(MessageEncoder.class);

	@Override
	public void init(EndpointConfig config) {
		logger.info("MessageEncoder - init method called");
		
	}

	@Override
	public void destroy() {
		logger.info("MessageEncoder - destroy method called");
		
	}

	@Override
	public String encode(Message object) throws EncodeException {

		return JSON.toJSONString(object);
	}



}
