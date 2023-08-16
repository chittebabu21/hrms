package com.cpj.hrms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    @ResponseBody
    public Map<String, String> handleMaxUploadSizeExceeded(MaxUploadSizeExceededException exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "File size limit exceeded");
        return errorResponse;
    }
}
