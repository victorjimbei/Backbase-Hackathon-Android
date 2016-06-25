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

import com.vjimbei.backbase_hackathon_android.Mvp.TasksMvp;
import com.vjimbei.backbase_hackathon_android.Mvp.UserMvp;
import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.entity.User;
import com.vjimbei.backbase_hackathon_android.presenter.TasksPresenter;
import com.vjimbei.backbase_hackathon_android.presenter.UserPresenter;
import com.vjimbei.backbase_hackathon_android.ui.activity.TaskDetailsActivity;
import com.vjimbei.backbase_hackathon_android.ui.adapter.TasksAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vjimbei on 6/24/2016.
 */
public class HomeFragment extends Fragment implements TasksMvp.View, TasksAdapter.OnTaskClicked{

    private RecyclerView tasksRecyclerView;
    private List<Task> taskList;
    private TasksAdapter tasksAdapter;
    private TasksMvp.Presenter presenter;
    private FrameLayout progress;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskList = new ArrayList<>();
        tasksAdapter = new TasksAdapter(getContext(), this);
        presenter = new TasksPresenter(getContext(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_tasks, container, false);
        tasksRecyclerView = (RecyclerView) view.findViewById(R.id.tasks_list);
        progress = (FrameLayout) view.findViewById(R.id.layout_progress);
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
        tasksAdapter.setList(list);
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
        intent.putExtra(AllTasksFragment.EXTRA_TASK, task);
        startActivity(intent);
    }
}
