package com.company.firesale.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
//        .setHandshakeHandler(new DefaultHandshakeHandler() {
//
//            public boolean beforeHandshake(
//                    ServerHttpRequest request,
//                    ServerHttpResponse response,
//                    WebSocketHandler wsHandler,
//                    Map attributes) throws Exception {
//
//                if (request instanceof ServletServerHttpRequest) {
//                    ServletServerHttpRequest servletRequest
//                            = (ServletServerHttpRequest) request;
//                    HttpSession session = servletRequest
//                            .getServletRequest().getSession();
//                    attributes.put("sessionId", session.getId());
//                }
//                return true;
//            }}).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/auctionBids", "/notifications", "/queue");
//        registry.enableSimpleBroker("/notifications");
//        registry.setUserDestinationPrefix("/user");
    }
}
