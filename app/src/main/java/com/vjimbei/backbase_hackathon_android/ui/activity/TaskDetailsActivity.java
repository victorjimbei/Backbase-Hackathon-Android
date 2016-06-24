package com.vjimbei.backbase_hackathon_android.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.ScreenManager;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.ui.AllTasksFragment;
import com.vjimbei.backbase_hackathon_android.ui.TaskDetailsFragment;

public class TaskDetailsActivity extends AppCompatActivity {

    private ScreenManager screenManager;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        screenManager = new ScreenManager(getSupportFragmentManager());
        task = getIntent().getParcelableExtra(AllTasksFragment.EXTRA_TASK);

        screenManager.placeFragment(TaskDetailsFragment.newInstance(task), false, R.id.fragment_container);
    }

}
