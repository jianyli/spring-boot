package com.li.support.webSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("")
@Component
public class WebSocketServer {
    Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    private static int onLineCount = 0;
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    private Session session;
    private String userId = "";

    @OnOpen
    public void onOpen(Session session, String userId) {
        this.session = session;
        this.userId = userId;

    }
}
