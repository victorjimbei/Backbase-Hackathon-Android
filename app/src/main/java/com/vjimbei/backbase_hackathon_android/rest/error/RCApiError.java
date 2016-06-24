package com.vjimbei.backbase_hackathon_android.rest.error;


public class RCApiError extends RCError {

    public RCApiError(String detailMessage) {
        super(detailMessage);
    }

    public RCApiError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
