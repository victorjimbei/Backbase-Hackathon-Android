package com.vjimbei.backbase_hackathon_android.presenter;

import com.vjimbei.backbase_hackathon_android.Mvp.AccountsMvp;
import com.vjimbei.backbase_hackathon_android.entity.Account;
import com.vjimbei.backbase_hackathon_android.listeners.OnLoadAccounts;
import com.vjimbei.backbase_hackathon_android.model.AccountsModel;

import java.util.List;

/**
 * Created by vjimbei on 6/24/2016.
 */
public class AccountPresenter implements AccountsMvp.Presenter, OnLoadAccounts {
    private AccountsMvp.View accountsView;
    private AccountsMvp.Model model;

    public AccountPresenter(AccountsMvp.View accountsView) {
        this.accountsView = accountsView;
        model = new AccountsModel(this);
    }

    @Override
    public void loadAccounts() {
        accountsView.showProgress();
        model.fetchAccounts();
    }

    @Override
    public void onSuccess(List<Account> list) {
        accountsView.showAccounts(list);
        accountsView.hideProgress();
    }

    @Override
    public void onFailed() {
        accountsView.hideProgress();
    }
}
