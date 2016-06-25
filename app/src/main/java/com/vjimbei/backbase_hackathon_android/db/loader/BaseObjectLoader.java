package com.vjimbei.backbase_hackathon_android.db.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.vjimbei.backbase_hackathon_android.db.DbClient;

public abstract class BaseObjectLoader<T> extends AsyncTaskLoader<T> {

    private DbClient databaseClient;

    private T data;

    public BaseObjectLoader(Context context, DbClient dbClient) {
        super(context);
        this.databaseClient = dbClient;
    }

    @Override
    public T loadInBackground() {
        return getDataFromDB();
    }

    protected abstract T getDataFromDB();

    @Override
    public void deliverResult(T data) {
        if (isReset()) {
            release();
        }
        this.data = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (this.data != null) {
            deliverResult(this.data);
        }
        if (takeContentChanged() || this.data == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (this.data != null) {
            release();
        }
    }

    private void release() {
        this.data = null;
    }

    public DbClient getDatabaseClient() {
        return databaseClient;
    }
}
