package com.vjimbei.backbase_hackathon_android.Mvp;

import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.entity.TaskStatistics;

public interface TaskDetailsMvp {

    public interface View {
        void showProgress();

        void hideProgress();

        void updateStatisticsUI(TaskStatistics statistics);

        void updateTask(Task task);
    }

    public interface Model {
        void updateTask(Task task);

        void updateStatistics(TaskStatistics statistics);
    }

    public interface Presenter {
        void updateTask(Task task);

        void sendData(TaskStatistics statistics);
    }
}
