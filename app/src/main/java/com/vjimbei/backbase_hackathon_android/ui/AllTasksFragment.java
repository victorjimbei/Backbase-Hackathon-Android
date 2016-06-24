package com.vjimbei.backbase_hackathon_android.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vjimbei.backbase_hackathon_android.Mvp.TasksMvp;
import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.presenter.TasksPresenter;
import com.vjimbei.backbase_hackathon_android.ui.adapter.DividerItemDecorator;
import com.vjimbei.backbase_hackathon_android.ui.adapter.TasksAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllTasksFragment extends Fragment implements TasksMvp.View{

    private RecyclerView tasksRecyclerView;
    private List<Task> taskList;
    private TasksAdapter tasksAdapter;
    private TasksMvp.Presenter presenter;

    public AllTasksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskList = new ArrayList<>();
        tasksAdapter = new TasksAdapter(getContext());
        presenter = new TasksPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_tasks, container, false);
        tasksRecyclerView = (RecyclerView) view.findViewById(R.id.tasks_list);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerview();
        presenter.loadTasks();
    }

    private void setUpRecyclerview(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tasksRecyclerView.setLayoutManager(layoutManager);
        tasksRecyclerView.addItemDecoration(new DividerItemDecorator(getContext()));
        tasksAdapter.setList(taskList);
        tasksRecyclerView.setAdapter(tasksAdapter);
    }

    @Override
    public void showList(List<Task> list) {
        tasksAdapter.setList(list);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
