package com.springboot.blog.springbootblogrestapi.payload;

import lombok.Getter;

import java.util.Date;

public class ErrorDetails {
    @Getter
    private Date timeStamp;
    @Getter
    private String message;
    @Getter
    private String details;

    public ErrorDetails(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }
}
