package com.vjimbei.backbase_hackathon_android.rest;

import com.vjimbei.backbase_hackathon_android.entity.Account;
import com.vjimbei.backbase_hackathon_android.entity.Plan;
import com.vjimbei.backbase_hackathon_android.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/user")
    Call<List<User>> getAllUsers();

    @GET("/user/{id}")
    Call<User> getUserById(@Path("id") long id);

    @GET("/user/{id}/mainAccount")
    Call<Account> getUserMainAccount(@Path("id") long id);

    @GET("/user/{id}/savingAccount")
    Call<Account> getUserSavingAccount(@Path("id") long id);

    @GET("/user/{id}/currentPlan")
    Call<Plan> getUserCurrentPlan(@Path("id") long id);



}
