package com.vjimbei.backbase_hackathon_android.Mvp;

import com.vjimbei.backbase_hackathon_android.entity.Task;

public interface TaskDetailsMvp {

    public interface View {
        void showProgress();

        void hideProgress();
    }

    public interface Model {
        void updateTask(Task task);
    }

    public interface Presenter {
        void updateTask(Task task);
    }
}
