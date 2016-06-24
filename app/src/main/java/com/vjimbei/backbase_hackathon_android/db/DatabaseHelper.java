package com.vjimbei.backbase_hackathon_android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.entity.TaskStatistics;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "tasks_db.sqlite";
    private static final int DB_VERSION = 1;

    private Dao<Task, Integer> tasksDao;
    private Dao<TaskStatistics, Integer> taskStatisticsDao;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Task.class);
            TableUtils.createTable(connectionSource, TaskStatistics.class);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Task.class, true);
            TableUtils.dropTable(connectionSource, TaskStatistics.class, true);

            onCreate(database, connectionSource);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public Dao<Task, Integer> getTasksDao() throws SQLException{
        if (tasksDao == null){
            tasksDao = getDao(Task.class);
        }
        return tasksDao;
    }

    public Dao<TaskStatistics, Integer> getTaskStatisticsDao() throws SQLException{
        if (taskStatisticsDao == null){
            taskStatisticsDao = getDao(TaskStatistics.class);
        }
        return taskStatisticsDao;
    }

}