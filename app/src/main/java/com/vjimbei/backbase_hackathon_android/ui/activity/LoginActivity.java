package com.vjimbei.backbase_hackathon_android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vjimbei.backbase_hackathon_android.R;

/**
 * Created by vjimbei on 6/24/2016.
 */
public class LoginActivity extends BaseActivity {

    private EditText etEmail;
    private EditText etPassword;
    private TextView etProgress;

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etEmail = (EditText) findViewById(R.id.edit_login_username);
        etPassword = (EditText) findViewById(R.id.edit_login_password);
        etProgress = (TextView) findViewById(R.id.et_login_progress);
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
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

//            if (!NetworkUtils.hasNetworkConnection(this)) {
//                Toast.makeText(this, R.string.no_network, Toast.LENGTH_LONG).show();
//                return;
//            }

            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            startActivity(new Intent(this, MainActivity.class));
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

    public void showProgress(final boolean show) {
        if (show) {
            etProgress.setVisibility(View.VISIBLE);
        } else {
            etPassword.setVisibility(View.GONE);
        }
    }
}
