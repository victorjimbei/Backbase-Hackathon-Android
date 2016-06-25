package com.vjimbei.backbase_hackathon_android.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.vjimbei.backbase_hackathon_android.Mvp.TasksMvp;
import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.entity.TaskStatusEnum;
import com.vjimbei.backbase_hackathon_android.presenter.TasksPresenter;
import com.vjimbei.backbase_hackathon_android.ui.activity.TaskDetailsActivity;
import com.vjimbei.backbase_hackathon_android.ui.adapter.TasksAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllTasksFragment extends Fragment implements TasksMvp.View, TasksAdapter.OnTaskClicked {

    public static final String EXTRA_TASK = "put.extra.task";
    public static final String EXTRA_ACTIVE_TASKS = "active_tasks";

    private RecyclerView tasksRecyclerView;
    private List<Task> taskList;
    private TasksAdapter tasksAdapter;
    private TasksMvp.Presenter presenter;
    private FrameLayout progress;
    private boolean sortActive;
    private TextView empty;

    public static AllTasksFragment newInstance(boolean activeTasks) {

        Bundle args = new Bundle();
        args.putBoolean(EXTRA_ACTIVE_TASKS, activeTasks);
        AllTasksFragment fragment = new AllTasksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskList = new ArrayList<>();
        tasksAdapter = new TasksAdapter(getContext(), this);
        presenter = new TasksPresenter(getContext(), this);
        sortActive = getArguments().getBoolean(EXTRA_ACTIVE_TASKS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_tasks, container, false);
        tasksRecyclerView = (RecyclerView) view.findViewById(R.id.tasks_list);
        progress = (FrameLayout) view.findViewById(R.id.layout_progress);
        empty = (TextView) view.findViewById(R.id.emptyView);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerview();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadTasks();
    }

    private void setUpRecyclerview() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tasksRecyclerView.setLayoutManager(layoutManager);
        tasksAdapter.setList(taskList);
        tasksRecyclerView.setAdapter(tasksAdapter);
    }

    @Override
    public void showList(List<Task> list) {
        List<Task> sortedList = filterList(list);
        if (sortedList.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
        } else {
            empty.setVisibility(View.GONE);
        }
        tasksAdapter.setList(filterList(list));
    }

    private List<Task> filterList(List<Task> list) {
        List<Task> sortedList = new ArrayList<>();
        for (Task task : list) {
            if (sortActive) {
                if (task.getStatus().equals(TaskStatusEnum.STARTED.name())) {
                    sortedList.add(task);
                }
            } else {
//                if (task.getStatus().equals(TaskStatusEnum.NOTSTARTED.name())) {
//                    sortedList.add(task);
//                }
                return list;
            }
        }
        return sortedList;
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onClickedTask(Task task) {
        Intent intent = new Intent(getActivity(), TaskDetailsActivity.class);
        intent.putExtra(EXTRA_TASK, task);
        startActivity(intent);
    }
}
