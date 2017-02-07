package com.mrtechs.apps.mk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import RatePOJO.Detail;
import RatePOJO.rateBean;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Rate extends AppCompatActivity {

    Toolbar toolbar;
    TextView name;
    String prodName = "";
    List<Detail> list;
    GridLayoutManager manager;
    RateAdapter adapter;
    RecyclerView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        list = new ArrayList<>();

        manager = new GridLayoutManager(this , 1);

        adapter = new RateAdapter(this , list);

        toolbar = (Toolbar)findViewById(R.id.toolbar);

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

        name = (TextView)findViewById(R.id.name);

        prodName = getIntent().getStringExtra("name");

        name.setText("You are reviewing: " + prodName);


        grid = (RecyclerView)findViewById(R.id.grid);

        grid.setLayoutManager(manager);
        grid.setAdapter(adapter);





    }


    @Override
    protected void onResume() {
        super.onResume();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nationproducts.in/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        allAPIs cr = retrofit.create(allAPIs.class);

        Call<rateBean> call = cr.getProductRatings()

    }
}
