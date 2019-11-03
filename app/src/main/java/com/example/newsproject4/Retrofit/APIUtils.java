package com.example.newsproject4.Retrofit;

public class APIUtils {
    public static final String Base_Url = "http://10.80.253.147:3000/";

    public static DataClient getData(){
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }
}
