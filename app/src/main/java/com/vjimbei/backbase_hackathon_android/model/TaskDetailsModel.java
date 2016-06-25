package com.vjimbei.backbase_hackathon_android.model;

import android.content.Context;
import android.util.Log;

import com.vjimbei.backbase_hackathon_android.Mvp.TaskDetailsMvp;
import com.vjimbei.backbase_hackathon_android.db.DbClient;
import com.vjimbei.backbase_hackathon_android.db.DbClientImpl;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.entity.TaskStatistics;
import com.vjimbei.backbase_hackathon_android.rest.ApiProvider;
import com.vjimbei.backbase_hackathon_android.rest.error.RCApiError;
import com.vjimbei.backbase_hackathon_android.rest.error.RCError;
import com.vjimbei.backbase_hackathon_android.rest.error.RCNetworkError;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskDetailsModel implements TaskDetailsMvp.Model {

    private ApiProvider apiProvider;
    private DbClient dbClient;
    private OnTaskUpdateListener listener;

    public interface OnTaskUpdateListener {
        void onSuccessUpdated(TaskStatistics statistics);

        void onFailedToUpdate(RCError error);

        void onTaskUpdatedSuccess(Task task);
    }

    public TaskDetailsModel(Context context, OnTaskUpdateListener listener) {
        apiProvider = new ApiProvider();
        dbClient = new DbClientImpl(context);
        this.listener = listener;
    }

    @Override
    public void updateTask(Task task) {
              dbClient.saveOrUpdateTask(task);//todo integrate api
    }

    @Override
    public void updateStatistics(TaskStatistics statistics) {
        dbClient.saveOrUpdateStatistics(statistics);

        listener.onSuccessUpdated(statistics);

        Call<TaskStatistics> call = apiProvider.getApiService().createTask(statistics);
        call.enqueue(new Callback<TaskStatistics>() {
            @Override
            public void onResponse(Call<TaskStatistics> call, Response<TaskStatistics> response) {
                if (response.isSuccessful()) {
                    listener.onSuccessUpdated(response.body());
                } else {
                    listener.onFailedToUpdate(new RCApiError(response.message()));
                }
            }

            @Override
            public void onFailure(Call<TaskStatistics> call, Throwable t) {
                Log.d("test", "nu prea");
                if (t instanceof IOException) {
                    listener.onFailedToUpdate(new RCNetworkError(t.getLocalizedMessage(), t));
                } else {
                    listener.onFailedToUpdate(new RCApiError(t.getLocalizedMessage(), t));
                }
                return;
            }
        });

    }
}
