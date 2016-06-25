package com.vjimbei.backbase_hackathon_android.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {

    private static final String ENDPOINT = "http://rewardingchallenge.cfapps.io/";

    Retrofit.Builder builder = new Retrofit.Builder();

    Gson gson = new GsonBuilder().setLenient().create();

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(logging);

    public ApiService getApiService() {
        return builder.baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
                .create(ApiService.class);
    }
}
