package com.vjimbei.backbase_hackathon_android.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Plan implements Parcelable {

    @SerializedName("id")
    private long id;
    @SerializedName("targetedAmount")
    private double targetedAmount;
    @SerializedName("currentProgress")
    private double currentProgress;

    public Plan() {
    }

    protected Plan(Parcel in) {
        id = in.readLong();
        targetedAmount = in.readDouble();
        currentProgress = in.readDouble();
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTargetedAmount() {
        return targetedAmount;
    }

    public void setTargetedAmount(double targetedAmount) {
        this.targetedAmount = targetedAmount;
    }

    public double getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(double currentProgress) {
        this.currentProgress = currentProgress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeDouble(targetedAmount);
        parcel.writeDouble(currentProgress);
    }
}
