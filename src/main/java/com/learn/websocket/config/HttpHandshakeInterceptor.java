package com.learn.websocket.config;

import com.learn.websocket.model.request.UserExportRequest;
import com.learn.websocket.service.external.NetworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class HttpHandshakeInterceptor implements HandshakeInterceptor {
    private final NetworkService networkService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String data = extractData(request);
        return validate(data);
    }

    private boolean validate(String data) {
        try {
            String[] parts = data.split("&username=");
            UserExportRequest request = new UserExportRequest();
            request.setToken(parts[0]);
            request.setUsername(parts[1]);
            return Boolean.TRUE.equals(networkService.validate(request).getBody());
        } catch (Exception ignored) {
            return false;
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // Do nothing after the handshake
    }

    private String extractData(ServerHttpRequest request) {
        String data = request.getURI().getQuery();
        return (data != null && !data.isEmpty()) ? data.substring(6) : "";
    }
}
