package com.vjimbei.backbase_hackathon_android.ui.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.vjimbei.backbase_hackathon_android.Mvp.AccountsMvp;
import com.vjimbei.backbase_hackathon_android.PhoneUnlockedReceiver;
import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.entity.Account;
import com.vjimbei.backbase_hackathon_android.entity.AccountTypeEnum;
import com.vjimbei.backbase_hackathon_android.entity.User;
import com.vjimbei.backbase_hackathon_android.ui.fragment.AllTasksFragment;
import com.vjimbei.backbase_hackathon_android.ui.view.AccountViewHolder;

import java.util.List;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends BaseActivity implements AccountsMvp.View {
    public static final String USER_KEY = "user_key";
    private PhoneUnlockedReceiver receiver;

    private AccountViewHolder primaryAccountHolder;
    private AccountViewHolder savingsAccountHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CardView primaryAccountView = (CardView) findViewById(R.id.primary_account_container);
        CardView savingsAccountView = (CardView) findViewById(R.id.savings_account_container);

        setSupportActionBar(toolbar);

        User user = getIntent().getParcelableExtra(USER_KEY);
        if (user != null) {
            Account primary = user.getMainAccount();
            Account savings = user.getSavingAccount();
            primary.setAccountType(AccountTypeEnum.MAIN.name());
            savings.setAccountType(AccountTypeEnum.SAVING.name());
            primaryAccountHolder = new AccountViewHolder(primaryAccountView, primary);
            savingsAccountHolder = new AccountViewHolder(savingsAccountView, savings);
            primaryAccountHolder.configureView();
            savingsAccountHolder.configureView();
        }

        receiver = new PhoneUnlockedReceiver();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, AllTasksFragment.newInstance(true)).commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter("android.intent.action.USER_PRESENT"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void onViewAll(View view) {
        startActivity(new Intent(this, AllTasksActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showAccounts(List<Account> list) {

    }


}
