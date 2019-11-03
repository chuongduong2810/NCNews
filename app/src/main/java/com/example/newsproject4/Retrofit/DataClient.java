package com.example.newsproject4.Retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataClient {

    @FormUrlEncoded
    @POST("user")
    Call<String> InsertData(
            //@Field("key") String key,
            @Field("uid") String uid,
            @Field("userEmail") String userEmail,
            @Field("userName") String userName);

}
