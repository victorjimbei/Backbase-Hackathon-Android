package com.vjimbei.backbase_hackathon_android.db;

import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.entity.TaskStatistics;

import java.util.List;

public interface DbClient {

    boolean saveOrUpdateTask(Task task);

    boolean saveOrUpdateStatistics(TaskStatistics task);

    boolean saveOrUpdateAllTask(List<Task> list);

    List<Task> getAllTasks();

    List<TaskStatistics> getAllStatistics();

    Task getTaskById(long id);
}
