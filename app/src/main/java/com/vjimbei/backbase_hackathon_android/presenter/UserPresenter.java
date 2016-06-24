package com.vjimbei.backbase_hackathon_android.presenter;

import com.vjimbei.backbase_hackathon_android.Mvp.UserMvp;
import com.vjimbei.backbase_hackathon_android.entity.User;
import com.vjimbei.backbase_hackathon_android.listeners.OnLoginUser;
import com.vjimbei.backbase_hackathon_android.model.UserModel;
import com.vjimbei.backbase_hackathon_android.rest.error.RCError;

/**
 * Created by vjimbei on 6/24/2016.
 */
public class UserPresenter implements UserMvp.Presenter, OnLoginUser {

    private UserMvp.View userView;
    private UserMvp.Model model;

    public UserPresenter(UserMvp.View userView) {
        this.userView = userView;
        model = new UserModel(this);
    }

    @Override
    public void loadUser(long userId) {
        userView.showProgress();
        model.loginUser(userId);
    }

    @Override
    public void onSuccess(User user) {
        userView.showUser(user);
        userView.hideProgress();
    }

    @Override
    public void onFailed(RCError rcApiError) {
        userView.hideProgress();
    }


}
