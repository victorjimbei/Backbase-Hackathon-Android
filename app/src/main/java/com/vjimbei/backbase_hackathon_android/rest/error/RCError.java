package com.vjimbei.backbase_hackathon_android.rest.error;


public class RCError extends RuntimeException {

    public RCError(String detailMessage) {
        super(detailMessage);
    }

    public RCError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

}