package com.vjimbei.backbase_hackathon_android.presenter;

import android.content.Context;

import com.vjimbei.backbase_hackathon_android.Mvp.TaskDetailsMvp;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.entity.TaskStatistics;
import com.vjimbei.backbase_hackathon_android.model.TaskDetailsModel;
import com.vjimbei.backbase_hackathon_android.rest.error.RCError;

public class TaskDetailsPresenter implements TaskDetailsMvp.Presenter, TaskDetailsModel.OnTaskUpdateListener {

    private TaskDetailsMvp.View view;
    private TaskDetailsMvp.Model model;

    public TaskDetailsPresenter(Context context, TaskDetailsMvp.View view) {
        this.view = view;
        model = new TaskDetailsModel(context, this);
    }

    @Override
    public void updateTask(Task task) {
        view.showProgress();
        model.updateTask(task);
    }

    @Override
    public void sendData(TaskStatistics statistics) {
        view.showProgress();
        model.updateStatistics(statistics);

    }

    @Override
    public void onSuccessUpdated(TaskStatistics statistics) {
        view.updateStatisticsUI(statistics);
        view.hideProgress();
    }

    @Override
    public void onFailedToUpdate(RCError error) {

        view.hideProgress();
    }

    @Override
    public void onTaskUpdatedSuccess(Task task) {
        view.hideProgress();
        view.updateTask(task);
    }
}
