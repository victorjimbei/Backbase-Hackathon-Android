package com.vjimbei.backbase_hackathon_android.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task implements Parcelable {
    @DatabaseField(id = true, columnName = "taskId")
    private long id;
    @DatabaseField(columnName = "userId")
    private long userId;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "description")
    private String description;
    @DatabaseField(columnName = "revenue")
    private double revenue;
    @DatabaseField(columnName = "currentMilestoneValue")
    private long currentMilestoneValue;
    @DatabaseField(columnName = "milestoneUnit")
    private String milestoneUnits;
    @DatabaseField(columnName = "milestoneLimit")
    private long milestoneLimit;
    @DatabaseField(columnName = "status")
    private String status;
    @DatabaseField(columnName = "goodSide")
    private String goodSide;
    @ForeignCollectionField(foreignFieldName = "task", columnName = "statistics")
    private Collection<TaskStatistics> taskStatisticsList;
    @DatabaseField(columnName = "isLoadedToServer")
    private boolean isLoadedToServer;

    public Task() {
    }

    protected Task(Parcel in) {
        id = in.readLong();
        userId = in.readLong();
        milestoneLimit = in.readLong();
        title = in.readString();
        description = in.readString();
        revenue = in.readDouble();
        currentMilestoneValue = in.readLong();
        milestoneUnits = in.readString();
        status = in.readString();
        goodSide = in.readString();
        taskStatisticsList = in.readArrayList(TaskStatistics.class.getClassLoader());
        isLoadedToServer = in.readByte() != 0;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getCurrentMilestoneValue() {
        return currentMilestoneValue;
    }

    public void setCurrentMilestoneValue(long currentMilestoneValue) {
        this.currentMilestoneValue = currentMilestoneValue;
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


    public void setTaskStatisticsList(List<TaskStatistics> taskStatisticsList) {
        this.taskStatisticsList = taskStatisticsList;
    }

    public Collection<TaskStatistics> getTaskStatisticsList() {
        return taskStatisticsList;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getGoodSide() {
        return goodSide;
    }

    public void setGoodSide(String goodSide) {
        this.goodSide = goodSide;
    }

    public boolean isLoadedToServer() {
        return isLoadedToServer;
    }

    public void setIsLoadedToServer(boolean isLoadedToServer) {
        this.isLoadedToServer = isLoadedToServer;
    }

    public void setTaskStatisticsList(Collection<TaskStatistics> taskStatisticsList) {
        this.taskStatisticsList = taskStatisticsList;
    }

    public long getMilestoneLimit() {
        return milestoneLimit;
    }

    public void setMilestoneLimit(long milestoneLimit) {
        this.milestoneLimit = milestoneLimit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeLong(userId);
        parcel.writeLong(milestoneLimit);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeDouble(revenue);
        parcel.writeLong(currentMilestoneValue);
        parcel.writeString(milestoneUnits);
        parcel.writeString(status);
        parcel.writeString(goodSide);
        parcel.writeByte((byte) (isLoadedToServer ? 1 : 0));
        List<TaskStatistics> tempList = new ArrayList(taskStatisticsList);
        parcel.writeList(tempList);
    }
}
