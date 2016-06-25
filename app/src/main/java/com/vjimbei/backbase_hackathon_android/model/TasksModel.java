package com.vjimbei.backbase_hackathon_android.model;

import android.content.Context;

import com.vjimbei.backbase_hackathon_android.Mvp.TasksMvp;
import com.vjimbei.backbase_hackathon_android.db.DbClient;
import com.vjimbei.backbase_hackathon_android.db.DbClientImpl;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.entity.TaskStatusEnum;
import com.vjimbei.backbase_hackathon_android.listeners.OnLoadTasks;
import com.vjimbei.backbase_hackathon_android.ui.utils.ApplicationPreferences;
import com.vjimbei.backbase_hackathon_android.ui.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class TasksModel implements TasksMvp.Model {

    private DateUtils dateUtils;
    private DbClient dbClient;
    private OnLoadTasks loadTasksListener;
    private ApplicationPreferences applicationPreferences;

    public TasksModel(Context context, OnLoadTasks loadTasksListener) {
        dateUtils = new DateUtils();
        dbClient = new DbClientImpl(context);
        this.loadTasksListener = loadTasksListener;
        applicationPreferences = new ApplicationPreferences(context);
    }

    @Override
    public void fetchTasks() {
        if (applicationPreferences.isFirstTimeCreatedTasks()){
            loadTasksListener.onSuccess(dbClient.getAllTasks());
        }else {
            applicationPreferences.setFirstTimeCreateTask(true);
            dbClient.saveOrUpdateAllTask(getFirstTimeTasks());
            loadTasksListener.onSuccess(getFirstTimeTasks());
        }
    }

    private List<Task> getFirstTimeTasks() {
        List<Task> list = new ArrayList<>();

        Task t1 = createRunningTask();
        Task t2 = createScreenLockTask();
        Task t3 = createPhoneUsageTimeTask();

        list.add(t1);
        list.add(t2);
        list.add(t3);

        return list;
    }

    private Task createRunningTask() {
        Task task = new Task();
        task.setId(System.currentTimeMillis());
        task.setTitle("Running");
        task.setUserId(1);
        task.setDescription("Running is healthy");
        task.setMilestoneUnits("Steps");
        task.setGoodSide("Right");
        task.setCurrentMilestoneValue(0);
        task.setStatus(TaskStatusEnum.NOTSTARTED.name());
        task.setRevenue(5);
        task.setMilestoneLimit(160);
        return task;
    }

    private Task createScreenLockTask() {
        Task task = new Task();
        task.setId(System.currentTimeMillis());
        task.setUserId(1);
        task.setTitle("Phone Unlocking");
        task.setDescription("Try to not unlock your phone today!");
        task.setMilestoneUnits("Unlocks");
        task.setGoodSide("Left");
        task.setCurrentMilestoneValue(0);
        task.setStatus(TaskStatusEnum.NOTSTARTED.name());
        task.setRevenue(5);
        task.setMilestoneLimit(50);
        return task;
    }

    private Task createPhoneUsageTimeTask() {
        Task task = new Task();
        task.setId(System.currentTimeMillis());
        task.setUserId(1);
        task.setTitle("Phone Usage Time");
        task.setDescription("No facebook today. Can you do this?");
        task.setMilestoneUnits("Minutes");
        task.setGoodSide("Left");
        task.setCurrentMilestoneValue(0);
        task.setStatus(TaskStatusEnum.NOTSTARTED.name());
        task.setRevenue(5);
        task.setMilestoneLimit(60);
        return task;
    }
}
