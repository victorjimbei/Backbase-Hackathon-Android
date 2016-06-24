package com.vjimbei.backbase_hackathon_android.ui;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.vjimbei.backbase_hackathon_android.Mvp.EditTaskMvp;
import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.presenter.EditTaskPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditTaskFragment extends DialogFragment implements EditTaskMvp.View {

    private static final String ARG_TASK = "arg.edit.task";

    private Task task;
    private EditText inputMilestone;
    private EditText inputRevenue;
    private Button save;
    private Button cancel;
    private FrameLayout progress;

    private EditTaskMvp.Presenter presenter;
    private OnTaskUpdated onTaskUpdated;

    public interface OnTaskUpdated {
        void refreshTask(Task task);
    }

    static EditTaskFragment newInstance(Task task) {
        EditTaskFragment f = new EditTaskFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TASK, task);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        task = getArguments().getParcelable(ARG_TASK);
        presenter = new EditTaskPresenter(this);
        try {
            onTaskUpdated = (OnTaskUpdated) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement DialogClickListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_task, container, false);
        inputMilestone = (EditText) v.findViewById(R.id.input_edit_threshold);
        inputRevenue = (EditText) v.findViewById(R.id.input_edit_revenue);
        save = (Button) v.findViewById(R.id.btn_save);
        cancel = (Button) v.findViewById(R.id.btn_cancel);
        progress = (FrameLayout)v.findViewById(R.id.layout_progress);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (task != null) {
            inputMilestone.setText(String.valueOf(task.getCurrentMilestoneLimit()));
            inputRevenue.setText(String.valueOf(task.getRevenue()));
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setRevenue(inputRevenue.getText().toString().isEmpty() ? 0 : Double.parseDouble(inputRevenue.getText()
                        .toString()));
                task.setCurrentMilestoneLimit(inputMilestone.getText().toString().isEmpty() ? 0 : Integer.parseInt(inputMilestone.getText()
                        .toString()));
                presenter.saveTask(task);
                EditTaskFragment.this.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTaskFragment.this.dismiss();
            }
        });
    }

    @Override
    public void reloadTask(Task task) {
        onTaskUpdated.refreshTask(task);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }
}
