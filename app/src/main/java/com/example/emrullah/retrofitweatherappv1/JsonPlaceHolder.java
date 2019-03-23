package com.example.emrullah.retrofitweatherappv1;

import org.w3c.dom.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolder {

    @GET("data/2.5/weather")
    Call<Response> getCityWeather(@Query("q") String cityQuery,@Query("appid") String appid );


}
