package com.vjimbei.backbase_hackathon_android.ui.view;

import android.support.v7.widget.CardView;
import android.widget.TextView;

import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.entity.Account;

/**
 * Created by vjimbei on 6/25/2016.
 */
public class AccountViewHolder {

    private CardView cardView;
    private Account account;

    public AccountViewHolder(CardView cardView, Account account) {
        this.cardView = cardView;
        this.account = account;
    }

    public void configureView() {
        TextView bankName = (TextView) cardView.findViewById(R.id.tv_bank_name);
        TextView accountType = (TextView) cardView.findViewById(R.id.tv_account_type);
        TextView amount = (TextView) cardView.findViewById(R.id.tv_amount);
        String bankNameString = account.getBankName();
        if (bankNameString != null && !bankNameString.isEmpty()) {
            bankName.setText(account.getBankName());
        } else {
            bankName.setText("No bank name");
        }

        accountType.setText(account.getAccountType());
        amount.setText(cardView.getContext().getString(R.string.amount_formatter, account.getAmount()));
    }
}
