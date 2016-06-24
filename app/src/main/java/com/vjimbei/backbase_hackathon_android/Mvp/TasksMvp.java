package com.vjimbei.backbase_hackathon_android.Mvp;

import com.vjimbei.backbase_hackathon_android.entity.Task;

import java.util.List;

public interface TasksMvp {

    public interface View{
        void showList(List<Task> list);

        void showProgress();

        void hideProgress();
    }

    public interface Presenter{
        void loadTasks();
    }

    public interface Model{
        void fetchTasks();
    }
}
