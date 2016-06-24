package com.vjimbei.backbase_hackathon_android.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.ScreenManager;
import com.vjimbei.backbase_hackathon_android.ui.fragment.AllTasksFragment;

/**
 * Created by vjimbei on 6/25/2016.
 */
public class AllTasksActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("All tasks");

        ScreenManager screenManager = new ScreenManager(getSupportFragmentManager());

        screenManager.placeFragment(AllTasksFragment.newInstance(), false, R.id.fragment_container);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_details;
    }
}
