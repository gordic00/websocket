package com.learn.websocket.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Configuration
public class AppProperties {
    /**
     * Security Allowed Methods.
     */
    public final String[] securityMethods = {
            "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "OPTIONS"
    };

    /**
     * Security Allowed Headers.
     */
    public final String[] securityHeaders = {
            "authorization", "content-type", "x-auth-token", "jwt-token", "permissions"
    };

    @Value("${spring.security.user.name}")
    private String globalUser;

    @Value("${spring.security.user.password}")
    private String globalPassword;

}
