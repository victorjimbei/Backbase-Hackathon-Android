package com.vjimbei.backbase_hackathon_android.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User implements Parcelable{

    @SerializedName("id")
    private long id;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("profileImg")
    private String profileImg;
    @SerializedName("mainAccount")
    private Account mainAccount;
    @SerializedName("savingAccount")
    private Account savingAccount;
    @SerializedName("currentPlan")
    private List<Plan> completedPLans;

    public User() {
    }

    protected User(Parcel in) {
        id = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        password = in.readString();
        username = in.readString();
        profileImg = in.readString();
        mainAccount = in.readParcelable(Account.class.getClassLoader());
        savingAccount = in.readParcelable(Account.class.getClassLoader());
        completedPLans = in.readArrayList(Plan.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public Account getMainAccount() {
        return mainAccount;
    }

    public void setMainAccount(Account mainAccount) {
        this.mainAccount = mainAccount;
    }

    public Account getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(Account savingAccount) {
        this.savingAccount = savingAccount;
    }

    public List<Plan> getCompletedPLans() {
        return completedPLans;
    }

    public void setCompletedPLans(List<Plan> completedPLans) {
        this.completedPLans = completedPLans;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(profileImg);
        parcel.writeString(username);
        parcel.writeParcelable(mainAccount, i);
        parcel.writeParcelable(savingAccount, i);
        parcel.writeList(completedPLans);
    }
}
