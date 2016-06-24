package com.vjimbei.backbase_hackathon_android.listeners;

import com.vjimbei.backbase_hackathon_android.entity.Task;

import java.util.List;

public interface OnLoadTasks {
    void onSuccess(List<Task> list);
    void onFailed();
}
