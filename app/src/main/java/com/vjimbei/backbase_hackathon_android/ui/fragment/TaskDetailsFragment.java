package com.vjimbei.backbase_hackathon_android.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.entity.Task;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailsFragment extends Fragment implements EditTaskFragment.OnTaskUpdated{

    private static final String ARGS_TASK = "args.task.details";
    private static final int DIALOG_FRAGMENT = 010;

    private TextView title;
    private TextView status;
    private TextView description;
    private TextView milestone;
    private TextView revenue;
    private Button edit;
    private Button startStopBtn;

    private Task task;

    public static TaskDetailsFragment newInstance(Task task) {
        TaskDetailsFragment fragment = new TaskDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_TASK, task);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        task = getArguments().getParcelable(ARGS_TASK);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_details, container, false);
        title = (TextView)view.findViewById(R.id.title);
        status = (TextView)view.findViewById(R.id.status);
        milestone = (TextView)view.findViewById(R.id.milestone);
        revenue = (TextView)view.findViewById(R.id.revenue);
        description = (TextView)view.findViewById(R.id.description);
        edit = (Button)view.findViewById(R.id.btn_edit);
        startStopBtn = (Button)view.findViewById(R.id.btn_start_stop);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updateUI(task);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTaskFragment newFragment = EditTaskFragment.newInstance(task);
                newFragment.setTargetFragment(TaskDetailsFragment.this, DIALOG_FRAGMENT);
                newFragment.show(getFragmentManager(), "dialog");
            }
        });

        startStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Clicked Start stop", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void refreshTask(Task task) {
        updateUI(task);
    }

    private void updateUI(Task task){
        if (task != null){
            title.setText(task.getTitle());
            status.setText(task.getStatus());
            description.setText(task.getDescription());
            revenue.setText(String.format(getContext().getString(R.string.format_revenue), task.getRevenue()));
            milestone.setText(String.format(getContext().getString(R.string.format_milestone), task
                    .getCurrentMilestoneLimit(), task.getMilestoneUnits()));
        }
    }
}
