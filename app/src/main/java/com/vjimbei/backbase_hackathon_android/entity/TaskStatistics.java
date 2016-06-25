package com.vjimbei.backbase_hackathon_android.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class TaskStatistics implements Parcelable{

    @DatabaseField(id = true, columnName = "statisticsId")
    private long id;
    @DatabaseField(columnName = "taskId")
    private long taskId;
    @DatabaseField(columnName = "date")
    private long date;
    @DatabaseField(columnName = "milestoneLimit")
    private long milestoneLimit;
    @SerializedName("currentMilestoneValue")
    @DatabaseField(columnName = "milestoneValue")
    private long milestoneValue;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "task")
    private Task task;

    public TaskStatistics() {
    }

    protected TaskStatistics(Parcel in) {
        id = in.readLong();
        taskId = in.readLong();
        date = in.readLong();
        milestoneLimit = in.readLong();
        milestoneValue = in.readLong();
        task = in.readParcelable(Task.class.getClassLoader());
    }

    public static final Creator<TaskStatistics> CREATOR = new Creator<TaskStatistics>() {
        @Override
        public TaskStatistics createFromParcel(Parcel in) {
            return new TaskStatistics(in);
        }

        @Override
        public TaskStatistics[] newArray(int size) {
            return new TaskStatistics[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getMilestoneLimit() {
        return milestoneLimit;
    }

    public void setMilestoneLimit(long milestoneLimit) {
        this.milestoneLimit = milestoneLimit;
    }

    public long getMilestoneValue() {
        return milestoneValue;
    }

    public void setMilestoneValue(long milestoneValue) {
        this.milestoneValue = milestoneValue;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeLong(taskId);
        parcel.writeLong(date);
        parcel.writeLong(milestoneLimit);
        parcel.writeLong(milestoneValue);
        parcel.writeParcelable(task, i);
    }
}
