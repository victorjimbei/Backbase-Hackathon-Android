package com.vjimbei.backbase_hackathon_android.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Task implements Parcelable{
    private int id;
    private String title;
    private String description;
    private double revenue;
    private long currentMilestoneLimit;
    private String milestoneUnits;
    private String status;
    private List<TaskStatistics> taskStatisticsList;

    public Task() {
    }

    protected Task(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        revenue = in.readDouble();
        currentMilestoneLimit = in.readLong();
        milestoneUnits = in.readString();
        status = in.readString();
        taskStatisticsList = in.createTypedArrayList(TaskStatistics.CREATOR);
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public long getCurrentMilestoneLimit() {
        return currentMilestoneLimit;
    }

    public void setCurrentMilestoneLimit(long currentMilestoneLimit) {
        this.currentMilestoneLimit = currentMilestoneLimit;
    }

    public String getMilestoneUnits() {
        return milestoneUnits;
    }

    public void setMilestoneUnits(String milestoneUnits) {
        this.milestoneUnits = milestoneUnits;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TaskStatistics> getTaskStatisticsList() {
        return taskStatisticsList;
    }

    public void setTaskStatisticsList(List<TaskStatistics> taskStatisticsList) {
        this.taskStatisticsList = taskStatisticsList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeDouble(revenue);
        parcel.writeLong(currentMilestoneLimit);
        parcel.writeString(milestoneUnits);
        parcel.writeString(status);
        parcel.writeTypedList(taskStatisticsList);
    }
}
