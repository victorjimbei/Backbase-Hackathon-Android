package com.vjimbei.backbase_hackathon_android.db.loader;

import android.content.Context;

import com.vjimbei.backbase_hackathon_android.db.DbClientImpl;
import com.vjimbei.backbase_hackathon_android.entity.Task;

/**
 * Created by vjimbei on 6/25/2016.
 */
public class TaskLoader extends BaseObjectLoader<Task> {
    public long taskId;

    public TaskLoader(Context context, DbClientImpl dbClient, long taskId) {
        super(context, dbClient);
        this.taskId = taskId;
    }

    @Override
    protected Task getDataFromDB() {
        return getDatabaseClient().getTaskById(taskId);
    }
}
