package com.vjimbei.backbase_hackathon_android.Mvp;

import com.vjimbei.backbase_hackathon_android.entity.Account;

import java.util.List;

/**
 * Created by vjimbei on 6/24/2016.
 */
public interface AccountsMvp {
    public interface View {

        void showProgress();

        void hideProgress();

        void showAccounts(List<Account> list);
    }

    public interface Presenter {
        void loadAccounts();
    }

    public interface Model {
        void fetchAccounts();
    }
}
