package com.learn.websocket.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.websocket.config.model.CustomException;
import com.learn.websocket.config.model.FeignErrorModel;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class FeignCustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String errorMessage;
        try (Reader reader = response.body().asReader(StandardCharsets.UTF_8)) {
            String result = IOUtils.toString(reader);
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            FeignErrorModel exceptionMessage = mapper.readValue(result, FeignErrorModel.class);
            errorMessage = exceptionMessage.getMessage();
        } catch (IOException e) {
            return new CustomException(e.getMessage());
        }

        if (response.status() >= 400 && response.status() <= 499) {
            return new CustomException(errorMessage);
        }
        return new Exception("Common Feign Exception");
    }
}
