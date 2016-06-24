package com.vjimbei.backbase_hackathon_android.db;

import java.util.List;
import java.util.concurrent.Callable;

public abstract class SaveDataListTask<T> implements Callable<Void> {

    private List<T> mData;

    public SaveDataListTask(List<T> data) {
        mData = data;
    }

    @Override
    public Void call() throws Exception {
        for (T data : mData) {
            addData(data);
        }
        return null;
    }

    public abstract void addData(T data);
}
