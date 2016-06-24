package com.vjimbei.backbase_hackathon_android.Mvp;

public interface TasksMvp {

    public interface View{

    }

    public interface Presenter{
        void loadTasks();
    }

    public interface Model{
        void fetchTasks();
    }
}
