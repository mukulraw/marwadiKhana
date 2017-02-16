package com.mrtechs.apps.mk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cartPOJO.Cartheader;
import cartPOJO.cartBean;
import cdeletePOJO.cdeleteBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Cart extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv;
    GridLayoutManager manager;
    TextView checkOut;
    Dataadapter adapter;
    ProgressBar progress;
    List<Cartheader> list;
    TextView clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caard);
        toolbar= (Toolbar) findViewById(R.id.toolbar);

        progress = (ProgressBar)findViewById(R.id.progress);

        checkOut = (TextView)findViewById(R.id.check_out);

        clear = (TextView)findViewById(R.id.clear);

        list = new ArrayList<>();

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Cart.this , Order.class);
                startActivity(intent);


            }
        });


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        toolbar.setNavigationIcon(R.drawable.back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rv = (RecyclerView) findViewById(R.id.recycler);


        manager = new GridLayoutManager(this , 1);
        adapter = new Dataadapter(this , list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress.setVisibility(View.VISIBLE);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://nationproducts.in/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                bean b = (bean)getApplicationContext();

                allAPIs cr = retrofit.create(allAPIs.class);

                Call<cdeleteBean> call = cr.clearCart(b.id);

                call.enqueue(new Callback<cdeleteBean>() {
                    @Override
                    public void onResponse(Call<cdeleteBean> call, Response<cdeleteBean> response) {
                        progress.setVisibility(View.GONE);

                        adapter.setGridData(new ArrayList<Cartheader>());

                    }

                    @Override
                    public void onFailure(Call<cdeleteBean> call, Throwable t) {

                    }
                });

            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        fetch();

    }

    public void fetch()
    {
        progress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nationproducts.in/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bean b = (bean)getApplicationContext();

        allAPIs cr = retrofit.create(allAPIs.class);

        Call<cartBean> call = cr.viewCart(b.id);

        call.enqueue(new Callback<cartBean>() {
            @Override
            public void onResponse(Call<cartBean> call, Response<cartBean> response) {

                adapter.setGridData(response.body().getCartheader());

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<cartBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });

    }


}
