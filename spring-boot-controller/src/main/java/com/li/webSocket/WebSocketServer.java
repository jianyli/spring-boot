package com.li.webSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("webSocket/{userId}")
@Component
public class WebSocketServer {
    static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    private static int onLineCount = 0;
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    private Session session;
    private String userId = "";

    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;

        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
        } else {
            addOnLineCount();
        }
        webSocketMap.put(userId, this);
        logger.info(userId + "接入webSocket，当前人数：" + getOnLineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            logger.error("用户：" + userId + ",网络异常");
        }
    }
    @OnClose
    public void close() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            subOnLineCount();
        }
        logger.info(userId + "退出，当前人数：" + getOnLineCount());
    }
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info(userId + "发送消息，消息报文："+ message);
        if (StringUtils.isNotBlank(message)) {
            try {
                JSONObject jsonObject = JSON.parseObject(message);
                jsonObject.put("fromUserId", userId);
                String toUserId = jsonObject.getString("toUserId");

                if (StringUtils.isNotBlank(toUserId) && webSocketMap.containsKey(toUserId)) {
                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
                } else {
                    logger.info("请求的userId：" + toUserId + "不在服务器中");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("用户：" + userId + "错误，原因：" + error.getMessage());
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
        logger.info("发送消息到用户Id" + userId + ",消息：" + message);
        if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
        }
    }

    public static synchronized int getOnLineCount() {
        return onLineCount;
    }

    public static synchronized void addOnLineCount() {
        WebSocketServer.onLineCount++;
    }

    public static void subOnLineCount() {
        WebSocketServer.onLineCount--;
    }
}
