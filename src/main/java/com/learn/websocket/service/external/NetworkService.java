package com.learn.websocket.service.external;

import com.learn.websocket.config.FeignClientConfig;
import com.learn.websocket.model.request.UserExportRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "network",
        url = "${external.service.network}",
        configuration = {FeignClientConfig.class})
public interface NetworkService {

    @PostMapping("/validate")
    ResponseEntity<Boolean> validate(
            @RequestBody UserExportRequest request
    );

}
