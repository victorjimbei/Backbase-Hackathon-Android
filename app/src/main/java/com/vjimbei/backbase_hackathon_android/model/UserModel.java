package com.vjimbei.backbase_hackathon_android.model;

import com.vjimbei.backbase_hackathon_android.Mvp.UserMvp;
import com.vjimbei.backbase_hackathon_android.entity.User;
import com.vjimbei.backbase_hackathon_android.listeners.OnLoginUser;
import com.vjimbei.backbase_hackathon_android.rest.ApiProvider;
import com.vjimbei.backbase_hackathon_android.rest.error.RCApiError;
import com.vjimbei.backbase_hackathon_android.rest.error.RCNetworkError;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vjimbei on 6/24/2016.
 */
public class UserModel implements UserMvp.Model {
    private ApiProvider apiProvider;
    private OnLoginUser loginUser;

    public UserModel(OnLoginUser loginUser) {
        this.loginUser = loginUser;
        this.apiProvider = new ApiProvider();
    }

    @Override
    public void loginUser(long userId) {
        Call<User> call = apiProvider.getApiService().getUserById(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    loginUser.onSuccess(response.body());
                } else {
                    loginUser.onFailed(new RCApiError(response.message()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if (t instanceof IOException) {
                    loginUser.onFailed(new RCNetworkError(t.getLocalizedMessage(), t));
                } else {
                    loginUser.onFailed(new RCApiError(t.getLocalizedMessage(), t));
                }
            }
        });
    }
}
