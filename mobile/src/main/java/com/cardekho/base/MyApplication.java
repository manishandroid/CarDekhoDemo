package com.cardekho.base;

import android.app.Application;

import com.cardekho.utils.GlobalVariables;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class MyApplication extends Application
{
    private static MyApplication instance;
    private RestAdapter restAdapter;
    public static MyApplication getInstance()
    {
        return instance;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
      instance = this;
    }

    public RestAdapter getRestAdapter() {
        return (restAdapter == null) ? setRestAdaptor() : restAdapter;
    }

    public RestAdapter setRestAdaptor() {

        RequestInterceptor requestInterceptor = new RequestInterceptor()
        {
            @Override
            public void intercept(RequestFacade request)
            {
                request.addHeader("App-Name", "CarDekhoDemo");
            }
        };

        restAdapter = new RestAdapter.Builder().setEndpoint(GlobalVariables.BASE_URL_CONSTANT)
                .setRequestInterceptor(requestInterceptor).setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient())).build();

        return restAdapter;
    }
}
