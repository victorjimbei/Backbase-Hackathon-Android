package com.vjimbei.backbase_hackathon_android.model;

import com.vjimbei.backbase_hackathon_android.Mvp.AccountsMvp;
import com.vjimbei.backbase_hackathon_android.entity.Account;
import com.vjimbei.backbase_hackathon_android.entity.AccountType;
import com.vjimbei.backbase_hackathon_android.listeners.OnLoadAccounts;
import com.vjimbei.backbase_hackathon_android.ui.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vjimbei on 6/24/2016.
 */
public class AccountsModel implements AccountsMvp.Model {
    private DateUtils dateUtils;
    private OnLoadAccounts loadAccountsListener;

    public AccountsModel(OnLoadAccounts loadAccountsListener) {
        this.loadAccountsListener = loadAccountsListener;
    }

    @Override
    public void fetchAccounts() {
        loadAccountsListener.onSuccess(getMockAccountsList());
    }

    private List<Account> getMockAccountsList() {
        Account account = new Account();
        account.setAccountType(AccountType.MAIN.name());
        account.setAmount(15000d);
        account.setBankName("ING Bank");
        account.setUserId(2123423L);

        Account savingsAccount = new Account();
        savingsAccount.setAccountType(AccountType.SAVING.name());
        savingsAccount.setAmount(0d);
        savingsAccount.setBankName("Raiffeisen Bank");
        account.setUserId(2123423L);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        return accounts;
    }
}
