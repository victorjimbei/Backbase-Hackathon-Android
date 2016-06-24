package com.vjimbei.backbase_hackathon_android.listeners;

import com.vjimbei.backbase_hackathon_android.entity.Account;

import java.util.List;

/**
 * Created by vjimbei on 6/24/2016.
 */
public interface OnLoadAccounts {
    void onSuccess(List<Account> list);

    void onFailed();
}
