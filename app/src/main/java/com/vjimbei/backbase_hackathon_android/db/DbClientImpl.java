package com.vjimbei.backbase_hackathon_android.db;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.entity.TaskStatistics;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DbClientImpl implements DbClient {

    private DatabaseHelper databaseHelper;

    public DbClientImpl(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }


    @Override
    public boolean saveOrUpdateTask(Task task) {
        try {
            databaseHelper.getTasksDao().callBatchTasks(new SaveTask(task));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveOrUpdateStatistics(TaskStatistics task) {
        try {
            databaseHelper.getTasksDao().callBatchTasks(new SaveStatisticsTask(task));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveOrUpdateAllTask(List<Task> list) {
        try {
            databaseHelper.getTasksDao().callBatchTasks(new SaveListTask(list));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> list;
        try {
            list = databaseHelper.getTasksDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            list = Collections.emptyList();
        }
        return list;
    }

    @Override
    public List<TaskStatistics> getAllStatistics() {
        List<TaskStatistics> list;
        try {
            list = databaseHelper.getTaskStatisticsDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            list = Collections.emptyList();
        }
        return list;
    }

    @Override
    public Task getTaskById(long id) {
        try {
            QueryBuilder<Task, Integer> queryBuilder = databaseHelper.getTasksDao().queryBuilder();
            queryBuilder.where().eq("taskId", id);
            return databaseHelper.getTasksDao().queryForFirst(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public class SaveListTask extends SaveDataListTask<Task> {

        public SaveListTask(List<Task> data) {
            super(data);
        }

        @Override
        public void addData(Task data) {
            saveTask(data);
        }
    }

    public class SaveTask extends SaveDataTask<Task> {

        public SaveTask(Task data) {
            super(data);
        }

        @Override
        public void addData(Task data) {
            saveTask(data);
        }
    }

    public class SaveStatisticsTask extends SaveDataTask<TaskStatistics> {

        public SaveStatisticsTask(TaskStatistics data) {
            super(data);
        }

        @Override
        public void addData(TaskStatistics data) {
            try {
                databaseHelper.getTaskStatisticsDao().createOrUpdate(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveTask(Task data){
        try {
            databaseHelper.getTasksDao().createOrUpdate(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
