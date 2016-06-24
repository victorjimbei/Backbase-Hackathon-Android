package com.vjimbei.backbase_hackathon_android.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vjimbei on 6/24/2016.
 */
public class Account implements Parcelable {

    @SerializedName("id")
    private long id;
    private long userId;
    @SerializedName("amount")
    private double amount;
    private String bankName;
    private String accountType;

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.userId);
        dest.writeDouble(this.amount);
        dest.writeString(this.bankName);
        dest.writeString(this.accountType);
    }

    protected Account(Parcel in) {
        this.id = in.readLong();
        this.userId = in.readLong();
        this.amount = in.readDouble();
        this.bankName = in.readString();
        this.accountType = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel source) {
            return new Account(source);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
}
