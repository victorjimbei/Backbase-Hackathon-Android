package com.vjimbei.backbase_hackathon_android.db.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.vjimbei.backbase_hackathon_android.db.DbClientImpl;

import java.sql.SQLException;

/**
 * Created by vjimbei on 6/25/2016.
 */
public abstract class ORMLiteLoader<T> extends AsyncTaskLoader<T> {
    private static final String TAG = "ORMLiteLoader";

    protected T result;
    protected DbClientImpl dbClient;
    protected long taskId;

    public ORMLiteLoader(Context context) {
        super(context);
    }

    public ORMLiteLoader(Context context, DbClientImpl dbClient) {
        super(context);
        this.dbClient = dbClient;
    }

    @Override
    public T loadInBackground() {
        try {
            return getDataFromDb();
        } catch (SQLException e) {
            Log.i(TAG, e.getLocalizedMessage(), e);
        }
        return null;
    }

    protected abstract T getDataFromDb() throws SQLException;

    @Override
    protected void onStartLoading() {
        if (result != null) {
            deliverResult(result);
        }
        if (takeContentChanged() || result == null) {
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
        result = null;
    }
}
