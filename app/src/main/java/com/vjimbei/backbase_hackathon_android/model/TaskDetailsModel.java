package com.vjimbei.backbase_hackathon_android.model;

import android.content.Context;

import com.vjimbei.backbase_hackathon_android.Mvp.TaskDetailsMvp;
import com.vjimbei.backbase_hackathon_android.db.DbClient;
import com.vjimbei.backbase_hackathon_android.db.DbClientImpl;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.entity.TaskStatistics;
import com.vjimbei.backbase_hackathon_android.rest.ApiProvider;

import retrofit2.Call;

public class TaskDetailsModel implements TaskDetailsMvp.Model {

    private ApiProvider apiProvider;
    private DbClient dbClient;
    private OnTaskUpdateListener listener;

    public interface OnTaskUpdateListener {
        void onSuccessUpdated();
        void onFailedToUpdate();
    }

    public TaskDetailsModel(Context context, OnTaskUpdateListener listener) {
        apiProvider = new ApiProvider();
        dbClient = new DbClientImpl(context);
        this.listener = listener;
    }

    @Override
    public void updateTask(Task task) {
        dbClient.saveOrUpdateTask(task);//todo integrate api
        listener.onSuccessUpdated();
    }

    @Override
    public void updateStatistics(TaskStatistics statistics) {

    }
}
