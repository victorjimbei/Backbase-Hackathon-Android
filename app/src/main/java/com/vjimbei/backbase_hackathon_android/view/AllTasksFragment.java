package com.vjimbei.backbase_hackathon_android.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.view.adapter.DividerItemDecorator;
import com.vjimbei.backbase_hackathon_android.view.adapter.TasksAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllTasksFragment extends Fragment {

    private RecyclerView tasksRecyclerView;
    private List<Task> taskList;
    private TasksAdapter tasksAdapter;

    public AllTasksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskList = new ArrayList<>();
        tasksAdapter = new TasksAdapter(getContext());
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
    }

    private void setUpRecyclerview(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tasksRecyclerView.setLayoutManager(layoutManager);
        tasksRecyclerView.addItemDecoration(new DividerItemDecorator(getContext()));
        tasksAdapter.setList(taskList);
        tasksRecyclerView.setAdapter(tasksAdapter);
    }

}
