package com.vjimbei.backbase_hackathon_android.rest.error;

public class RCNetworkError extends RCError {

    public RCNetworkError(String detailMessage) {
        super(detailMessage);
    }

    public RCNetworkError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
