package com.vjimbei.backbase_hackathon_android.Mvp;

import com.vjimbei.backbase_hackathon_android.entity.User;

/**
 * Created by vjimbei on 6/24/2016.
 */
public interface UserMvp {
    public interface View {
        void showUser(User user);

        void showProgress();

        void hideProgress();
    }

    public interface Presenter {
        void loadUser(long userId);
    }

    public interface Model {
        void loginUser(long userId);
    }
}
