package com.vjimbei.backbase_hackathon_android.model;

import com.vjimbei.backbase_hackathon_android.Mvp.EditTaskMvp;
import com.vjimbei.backbase_hackathon_android.entity.Task;

public class EditTaskModel implements EditTaskMvp.Model {

    private OnTaskUpdated onTaskUpdated;

    public interface OnTaskUpdated{
        void onSuccess(Task task);

        void onFailed();
    }

    public EditTaskModel(OnTaskUpdated onTaskUpdated) {
        this.onTaskUpdated = onTaskUpdated;
    }

    @Override
    public void updateTask(Task task) {
        //todo update task in db and on backend
        onTaskUpdated.onSuccess(task);//change this
    }
}
