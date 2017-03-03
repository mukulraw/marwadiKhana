package com.mrtechs.apps.mk;

import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import statusPOJO.statusBean;


public class StatusActivity extends ActionBarActivity {

    String html;
    String entity;
    String order;

    String order_id , amount , billing_name , billing_address , billing_city , billing_state , billing_zip , billing_country , billing_email , delivery_name , delivery_address ,
    delivery_city , delivery_state , delivery_zip , delivery_country , tracking_id , status;

    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        progress = (ProgressBar)findViewById(R.id.progress);

        Intent mainIntent = getIntent();

        html = mainIntent.getStringExtra("transStatus");
        order = mainIntent.getStringExtra("order");
        entity = mainIntent.getStringExtra("entity");

        Document doc = Jsoup.parse(html);

        Elements trElements = doc.getElementsByTag("tr");

        order_id = trElements.get(0).getElementsByTag("td").get(1).toString();
        amount = trElements.get(10).getElementsByTag("td").get(1).toString();
        billing_name = trElements.get(11).getElementsByTag("td").get(1).toString();
        billing_address = trElements.get(12).getElementsByTag("td").get(1).toString();
        billing_city = trElements.get(13).getElementsByTag("td").get(1).toString();
        billing_state = trElements.get(14).getElementsByTag("td").get(1).toString();
        billing_zip = trElements.get(15).getElementsByTag("td").get(1).toString();
        billing_country = trElements.get(16).getElementsByTag("td").get(1).toString();
        billing_email = trElements.get(18).getElementsByTag("td").get(1).toString();
        delivery_name = trElements.get(19).getElementsByTag("td").get(1).toString();
        delivery_address = trElements.get(20).getElementsByTag("td").get(1).toString();
        delivery_city = trElements.get(21).getElementsByTag("td").get(1).toString();
        delivery_state = trElements.get(22).getElementsByTag("td").get(1).toString();
        delivery_zip = trElements.get(23).getElementsByTag("td").get(1).toString();
        delivery_country = trElements.get(24).getElementsByTag("td").get(1).toString();
        tracking_id = trElements.get(1).getElementsByTag("td").get(1).toString();
        status = trElements.get(3).getElementsByTag("td").get(1).toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://marwadikhana.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bean b = (bean)getApplicationContext();

        allAPIs cr = retrofit.create(allAPIs.class);


        Call<statusBean> call = cr.status(entity , b.id , order_id , amount , billing_name , billing_address , billing_city , billing_state , billing_zip , billing_country , billing_email , delivery_name , delivery_address , delivery_city , delivery_state , delivery_zip , delivery_country , tracking_id , status);


        call.enqueue(new Callback<statusBean>() {
            @Override
            public void onResponse(Call<statusBean> call, Response<statusBean> response) {
                progress.setVisibility(View.GONE);
                Toast.makeText(StatusActivity.this , response.body().getOrderStatus().get(0).getStatus() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<statusBean> call, Throwable t) {
                Toast.makeText(StatusActivity.this , "An error occurred" , Toast.LENGTH_SHORT).show();


            }
        });


    }
}
