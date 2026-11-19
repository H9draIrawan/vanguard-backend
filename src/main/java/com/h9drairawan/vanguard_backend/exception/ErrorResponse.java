package com.h9drairawan.vanguard_backend.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private Integer status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
