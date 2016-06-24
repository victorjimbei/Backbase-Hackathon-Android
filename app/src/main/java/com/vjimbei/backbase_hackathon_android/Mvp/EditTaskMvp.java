package com.vjimbei.backbase_hackathon_android.Mvp;

import com.vjimbei.backbase_hackathon_android.entity.Task;

public interface EditTaskMvp {

    public interface View{
        void reloadTask(Task task);

        void showProgress();

        void hideProgress();
    }

    public interface Model{
        void updateTask(Task task);
    }

    public interface Presenter{
        void saveTask(Task task);
    }
}
