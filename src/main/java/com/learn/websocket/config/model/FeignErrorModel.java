package com.learn.websocket.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
public class FeignErrorModel {
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
