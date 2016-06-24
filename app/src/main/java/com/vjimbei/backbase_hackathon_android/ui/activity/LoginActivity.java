package com.vjimbei.backbase_hackathon_android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vjimbei.backbase_hackathon_android.Mvp.UserMvp;
import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.entity.User;
import com.vjimbei.backbase_hackathon_android.presenter.UserPresenter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vjimbei on 6/24/2016.
 */
public class LoginActivity extends BaseActivity implements UserMvp.View {

    private EditText etEmail;
    private EditText etPassword;
    private TextView etProgress;
    private UserPresenter userPresenter;
    private Map<String, Long> usersMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etEmail = (EditText) findViewById(R.id.edit_login_username);
        etPassword = (EditText) findViewById(R.id.edit_login_password);
        etProgress = (TextView) findViewById(R.id.et_login_progress);
        userPresenter = new UserPresenter(this);
        usersMap = new LinkedHashMap<>();
        usersMap.put("milosbiljanovic@gmail.com", 1L);
        usersMap.put("damiralibegovic@gmail.com", 2L);
        usersMap.put("aleksandarzivkovic@gmail.com", 3L);
        etEmail.setText("milosbiljanovic@gmail.com");
        etPassword.setText("test123");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_log_in;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin(View view) {

        // Reset errors.
        etEmail.setError(null);
        etPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            etPassword.setError(getString(R.string.error_invalid_password));
            focusView = etPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.error_field_required));
            focusView = etEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            etEmail.setError(getString(R.string.error_invalid_email));
            focusView = etEmail;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            userPresenter.loadUser(usersMap.get(email));
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void showUser(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.USER_KEY, user);
        startActivity(intent);
        finish();
    }

    @Override
    public void showProgress() {
        etProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        etProgress.setVisibility(View.GONE);
    }
}
