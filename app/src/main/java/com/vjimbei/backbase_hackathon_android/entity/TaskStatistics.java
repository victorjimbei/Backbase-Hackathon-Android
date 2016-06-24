package com.vjimbei.backbase_hackathon_android.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class TaskStatistics implements Parcelable{

    private long id;
    private String date;
    private long milestoneLimit;
    private long milestoneValue;

    public TaskStatistics() {
    }

    protected TaskStatistics(Parcel in) {
        id = in.readLong();
        date = in.readString();
        milestoneLimit = in.readLong();
        milestoneValue = in.readLong();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(date);
        parcel.writeLong(milestoneLimit);
        parcel.writeLong(milestoneValue);
    }
}
