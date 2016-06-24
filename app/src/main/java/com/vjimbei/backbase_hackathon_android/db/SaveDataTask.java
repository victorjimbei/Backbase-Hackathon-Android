package com.vjimbei.backbase_hackathon_android.db;

import java.util.concurrent.Callable;

public abstract class SaveDataTask<T> implements Callable<Void> {

    private T mData;

    public SaveDataTask(T data) {
        mData = data;
    }

    @Override
    public Void call() throws Exception {
        addData(mData);

        return null;
    }

    public abstract void addData(T data);
}