package com.mrtechs.apps.mk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import wishlistPOJO.Wishlistt;
import wishlistPOJO.wishlistBean;

public class Wishlist extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView grid;
    List<Wishlistt> list;
    GridLayoutManager manager;
    WishAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        list = new ArrayList<>();
        manager = new GridLayoutManager(this , 1);

        adapter = new WishAdapter(this , list);

        toolbar= (Toolbar) findViewById(R.id.toolbar);

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

        grid = (RecyclerView)findViewById(R.id.grid);

    }


    @Override
    protected void onResume() {
        super.onResume();

        fetch();

    }

    public void fetch()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nationproducts.in/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bean b = (bean)getApplicationContext();

        allAPIs cr = retrofit.create(allAPIs.class);


        Call<wishlistBean> call = cr.wishlist(b.id);

        call.enqueue(new Callback<wishlistBean>() {
            @Override
            public void onResponse(Call<wishlistBean> call, Response<wishlistBean> response) {

                Log.d("asdasdasasdasdSize" , String.valueOf(response.body().getItemDetail().getWishlist().size()));

                list = response.body().getItemDetail().getWishlist();

                Log.d("asdasdasasdasdSize" , String.valueOf(list.size()));

                adapter.setGridData(list);


            }

            @Override
            public void onFailure(Call<wishlistBean> call, Throwable t) {

            }
        });


    }

}
