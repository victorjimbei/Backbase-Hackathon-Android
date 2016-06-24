package com.vjimbei.backbase_hackathon_android;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class ScreenManager {
    private FragmentManager fragmentManager;

    public ScreenManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void placeFragment(Fragment fragment, boolean isAddBackTack, int container) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(container, fragment);

        if (isAddBackTack) {
            transaction.addToBackStack(null);
        } else {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        transaction.commit();
    }

    private View.OnClickListener nullListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //not implemented
        }
    };

    private void showSnackBar(View view, String message, int duration, int actionButton, View.OnClickListener actionListener) {
        Snackbar snackBar = Snackbar.make(view, message, duration);
        snackBar.setAction(actionButton, actionListener);
        snackBar.show();
    }

    public void showSnackBar(View view, String message, int actionButton, View.OnClickListener actionListener) {
        showSnackBar(view, message, Snackbar.LENGTH_INDEFINITE, actionButton, actionListener);
    }

    public void showSnackBar(View view, String message) {
        showSnackBar(view, message, Snackbar.LENGTH_INDEFINITE, R.string.snackbar_close, nullListener);
    }

//    public void startNewActivity(Class newActivityClass, Bundle bundle){
//        Intent intent = new Intent(getBaseActivity(), newActivityClass);
//        if (bundle != null){
//            intent.putExtra(EXTRA_BUNDLE, bundle);
//        }
//        getBaseActivity().startActivity(intent);
//    }
}
