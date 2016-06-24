package com.vjimbei.backbase_hackathon_android.listeners;

import com.vjimbei.backbase_hackathon_android.entity.User;
import com.vjimbei.backbase_hackathon_android.rest.error.RCApiError;
import com.vjimbei.backbase_hackathon_android.rest.error.RCError;

/**
 * Created by vjimbei on 6/24/2016.
 */
public interface OnLoginUser {
    void onSuccess(User user);

    void onFailed(RCError rcApiError);
}
