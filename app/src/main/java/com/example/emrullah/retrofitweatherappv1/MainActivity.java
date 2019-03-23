package com.example.emrullah.retrofitweatherappv1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.*;

public class MainActivity extends AppCompatActivity {
    Main main;
    @BindView(R.id.city_name_text)
    TextView _cityName;
    @BindView(R.id.refresh_image)
    ImageView _refreshImage;
    @BindView(R.id.location_text)
    EditText _locationText;
    @BindView(R.id.location_text2)
    TextView _locationText2;


    private JsonPlaceHolder jsonPlaceHolderApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolder.class);

        main = new Main();
        getCityWeather();

    }

    @OnClick(R.id.refresh_image)
    public void getCityWeather() {

        Call<com.example.emrullah.retrofitweatherappv1.Response> call = jsonPlaceHolderApi.getCityWeather("Berlin", "4928191948d001ae09f9b3ffed0188e4");
        call.enqueue(new Callback<com.example.emrullah.retrofitweatherappv1.Response>() {
            @Override
            public void onResponse(Call<com.example.emrullah.retrofitweatherappv1.Response> call, Response<com.example.emrullah.retrofitweatherappv1.Response> response) {
                main = response.body().main;
                String k = main.getTemp().toString();
                String content="";

                content=k;
                _locationText2.setText(content);
            }

            @Override
            public void onFailure(Call<com.example.emrullah.retrofitweatherappv1.Response> call, Throwable t) {

            }
        });
    }
}
