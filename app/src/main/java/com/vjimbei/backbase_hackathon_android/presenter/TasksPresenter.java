package com.vjimbei.backbase_hackathon_android.presenter;

import android.content.Context;

import com.vjimbei.backbase_hackathon_android.Mvp.TasksMvp;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.listeners.OnLoadTasks;
import com.vjimbei.backbase_hackathon_android.model.TasksModel;

import java.util.List;

public class TasksPresenter implements TasksMvp.Presenter, OnLoadTasks {

    private TasksMvp.View tasksView;
    private TasksMvp.Model model;

    public TasksPresenter(Context context, TasksMvp.View tasksView) {
        this.tasksView = tasksView;
        model = new TasksModel(context, this);
    }

    @Override
    public void loadTasks() {
        tasksView.showProgress();
        model.fetchTasks();
    }

    @Override
    public void onSuccess(List<Task> list) {
        tasksView.showList(list);
        tasksView.hideProgress();
    }

    @Override
    public void onFailed() {
        tasksView.hideProgress();
    }
}
