package com.vjimbei.backbase_hackathon_android.db;

import com.vjimbei.backbase_hackathon_android.entity.Task;

import java.util.List;

public interface DbClient {

    boolean saveOrUpdateTask(Task task);

    boolean saveOrUpdateAllTask(List<Task> list);

    List<Task> getAllTasks();

    Task getTaskById(long id);
}
