package com.learn.websocket.model.request;

import lombok.Data;

@Data
public class UserExportRequest {
    private String token;
    private String username;
}
