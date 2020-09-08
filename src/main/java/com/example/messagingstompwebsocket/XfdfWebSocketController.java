package com.example.messagingstompwebsocket;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class XfdfWebSocketController {
	@MessageMapping("/send-annotation-updates/{sessionId}")
	@SendTo("/receive/receive-annotation-updates/{sessionId}")
	public XfdfMessage annotationUpdates(@DestinationVariable String sessionId, XfdfMessage message) throws Exception {
		return message;
	}
}
