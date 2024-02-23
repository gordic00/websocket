package com.learn.websocket.config;

import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.form.spring.SpringFormEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class FeignClientConfig {
    private final AppProperties appProperties;

    @Bean
    public Encoder multipartFormEncoder() {
        return new SpringFormEncoder(
                new SpringEncoder(
                        () -> new HttpMessageConverters(new RestTemplate().getMessageConverters())));
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignCustomErrorDecoder();
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(
                appProperties.getGlobalUser(), appProperties.getGlobalPassword());
    }

}
