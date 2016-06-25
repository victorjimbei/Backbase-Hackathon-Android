package com.vjimbei.backbase_hackathon_android.model;

import com.vjimbei.backbase_hackathon_android.Mvp.TasksMvp;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.entity.TaskStatistics;
import com.vjimbei.backbase_hackathon_android.listeners.OnLoadTasks;
import com.vjimbei.backbase_hackathon_android.ui.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TasksModel implements TasksMvp.Model {

    private DateUtils dateUtils;
    private OnLoadTasks loadTasksListener;

    public TasksModel(OnLoadTasks loadTasksListener) {
        dateUtils = new DateUtils();
        this.loadTasksListener = loadTasksListener;
    }

    @Override
    public void fetchTasks() {
//todo if response from server is empty list show default tasks
        loadTasksListener.onSuccess(getFirstTimeTasks());
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
        task.setId(1);
        task.setTitle("Running");
        task.setDescription("Running is healthy");
        task.setMilestoneUnits("Steps");
        task.setStatus("Not Started");
        task.setCurrentMilestoneValue(0);
        task.setRevenue(5);
        TaskStatistics taskStatistics = new TaskStatistics();
        taskStatistics.setId(11);
        taskStatistics.setMilestoneLimit(3300);
        taskStatistics.setMilestoneValue(0);
        taskStatistics.setDate(dateUtils.getDateAsString(new Date(System.currentTimeMillis())));
        List<TaskStatistics> taskStatisticses = new ArrayList<>();
        taskStatisticses.add(taskStatistics);
        task.setTaskStatisticsList(taskStatisticses);
        return task;
    }

    private Task createScreenLockTask() {
        Task task = new Task();
        task.setId(2);
        task.setTitle("Phone Unlocking");
        task.setDescription("Try to not unlock your phone today!");
        task.setMilestoneUnits("Unlocks");
        task.setStatus("Not Started");
        task.setCurrentMilestoneValue(0);
        task.setRevenue(5);
        TaskStatistics taskStatistics = new TaskStatistics();
        taskStatistics.setId(21);
        taskStatistics.setMilestoneLimit(50);
        taskStatistics.setMilestoneValue(0);
        taskStatistics.setDate(dateUtils.getDateAsString(new Date(System.currentTimeMillis())));
        List<TaskStatistics> taskStatisticses = new ArrayList<>();
        taskStatisticses.add(taskStatistics);
        task.setTaskStatisticsList(taskStatisticses);
        return task;
    }

    private Task createPhoneUsageTimeTask() {
        Task task = new Task();
        task.setId(3);
        task.setTitle("Phone Usage Time");
        task.setDescription("No facebook today. Can you do this?");
        task.setMilestoneUnits("Minutes");
        task.setStatus("Not Started");
        task.setCurrentMilestoneValue(0);
        task.setRevenue(5);
        TaskStatistics taskStatistics = new TaskStatistics();
        taskStatistics.setId(31);
        taskStatistics.setMilestoneLimit(60);
        taskStatistics.setMilestoneValue(0);
        taskStatistics.setDate(dateUtils.getDateAsString(new Date(System.currentTimeMillis())));
        List<TaskStatistics> taskStatisticses = new ArrayList<>();
        taskStatisticses.add(taskStatistics);
        task.setTaskStatisticsList(taskStatisticses);
        return task;
    }
}
