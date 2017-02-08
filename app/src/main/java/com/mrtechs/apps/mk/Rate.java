package com.mrtechs.apps.mk;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import RatePOJO.Detail;
import RatePOJO.rateBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import reviewPOJO.reviewBean;

public class Rate extends AppCompatActivity {

    Toolbar toolbar;
    TextView name;
    String prodName = "";
    String id = "";
    List<Detail> list;
    GridLayoutManager manager;
    RateAdapter adapter;
    RecyclerView grid;
    TextView addReview;
    ProgressBar progress;
    String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        list = new ArrayList<>();

        progress = (ProgressBar)findViewById(R.id.progress);

        final bean b = (bean)getApplicationContext();

        userId = b.id;

        prodName = getIntent().getExtras().getString("name");
        id = getIntent().getExtras().getString("id");

        manager = new GridLayoutManager(this , 1);

        adapter = new RateAdapter(this , list);

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        addReview = (TextView)findViewById(R.id.add_review);

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



        name.setText("You are reviewing: " + prodName);


        grid = (RecyclerView)findViewById(R.id.grid);

        grid.setLayoutManager(manager);
        grid.setAdapter(adapter);


        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Rate.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.rate_dialog);


                final RatingBar value = (RatingBar)dialog.findViewById(R.id.value_rate);
                final RatingBar quality = (RatingBar)dialog.findViewById(R.id.quality_rate);
                final RatingBar price = (RatingBar)dialog.findViewById(R.id.price_rate);

                final EditText title = (EditText)dialog.findViewById(R.id.title);
                final EditText review = (EditText)dialog.findViewById(R.id.review);

                Button submit = (Button)dialog.findViewById(R.id.submit);

                dialog.show();



                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://nationproducts.in/")
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();


                        allAPIs cr = retrofit.create(allAPIs.class);


                        String val = String.valueOf(value.getRating() * 20);
                        String qual = String.valueOf(quality.getRating() * 20);
                        String pri = String.valueOf(price.getRating() * 20);



                        Call<reviewBean> call = cr.rateProduct(id , userId , title.getText().toString() , review.getText().toString() , b.username , val , qual , pri);

                        call.enqueue(new Callback<reviewBean>() {
                            @Override
                            public void onResponse(Call<reviewBean> call, Response<reviewBean> response) {


                                if (Objects.equals(response.body().getProductReview().get(0).getSuccess(), "1"))
                                {
                                    fetch();
                                }

                                dialog.dismiss();

                            }

                            @Override
                            public void onFailure(Call<reviewBean> call, Throwable t) {

                                dialog.dismiss();

                            }
                        });

                    }
                });


            }
        });





    }


    public void fetch()
    {
        progress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nationproducts.in/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        allAPIs cr = retrofit.create(allAPIs.class);

        Call<rateBean> call = cr.getProductRatings(id);

        call.enqueue(new Callback<rateBean>() {
            @Override
            public void onResponse(Call<rateBean> call, Response<rateBean> response) {

                list = response.body().getDetail();



                adapter.setGridData(list);

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<rateBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        fetch();

    }
}
