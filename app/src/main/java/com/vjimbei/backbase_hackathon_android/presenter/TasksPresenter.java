package com.vjimbei.backbase_hackathon_android.presenter;

import com.vjimbei.backbase_hackathon_android.Mvp.TasksMvp;
import com.vjimbei.backbase_hackathon_android.model.TasksModel;

public class TasksPresenter implements TasksMvp.Presenter {

    private TasksMvp.View tasksView;
    private TasksMvp.Model model;

    public TasksPresenter(TasksMvp.View tasksView) {
        this.tasksView = tasksView;
        model = new TasksModel();
    }

    @Override
    public void loadTasks() {

    }
}
