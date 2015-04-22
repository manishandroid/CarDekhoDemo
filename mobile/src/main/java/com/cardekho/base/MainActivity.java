package com.cardekho.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cardekho.R;
import com.cardekho.model.DataModel;
import com.cardekho.utils.GlobalVariables;

import retrofit.RetrofitError;
import retrofit.cardekho.BaseActivity;
import retrofit.cardekho.MyCallback;
import retrofit.cardekho.RestCallback;
import retrofit.cardekho.RestService;
import retrofit.client.Response;


public class MainActivity extends BaseActivity implements RestCallback, View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbarView();
        initializeViews();
    }

    private void initializeViews()
    {
        ((Button)findViewById(R.id.btn_getData)).setOnClickListener(this);
    }

    private void setupToolbarView()
    {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setTitle("Car Dekho");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }


    @Override
    public void onFailure(RetrofitError e, GlobalVariables.SERVICE_MODE mode) {
        System.out.println("==> fail "+ e.getMessage());
    }

    @Override
    public void onSuccess(Object model, Response response, GlobalVariables.SERVICE_MODE mode) {
        switch(mode)
        {
            case GET_DATA:
                DataModel dataModel = (DataModel) model;
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                replaceFragment(dataModel);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_getData:
                callAPI();
                break;
        }
    }

    private void callAPI()
    {
        getRestAdaptor().create(RestService.class).getCityList(new MyCallback<DataModel>(this, this, true, null, "Loading...", GlobalVariables.SERVICE_MODE.GET_DATA));
    }

    private void replaceFragment(DataModel model)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, MapFragment.newInstance(model)).commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }


}
