package com.mrtechs.apps.mk;

import android.content.Intent;
import android.provider.MediaStore;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import getRatePOJO.getRateBean;
import orderPOJO.orderBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import utility.AvenuesParams;

public class CreateOrder extends AppCompatActivity {

    Toolbar toolbar;

    Button place;

    ProgressBar progress;

    RadioButton standard;

    RadioGroup radio;

    String rate = "0";

    String getRate = "";

    String max;

    String amount;

    String maxValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        amount = getIntent().getStringExtra("amount");

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        place = (Button)findViewById(R.id.place);
        progress = (ProgressBar)findViewById(R.id.progress);
        radio = (RadioGroup)findViewById(R.id.radio);
        standard = (RadioButton)findViewById(R.id.standard);

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


        progress.setVisibility(View.VISIBLE);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://marwadikhana.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        allAPIs cr = retrofit.create(allAPIs.class);

        Call<getRateBean> call = cr.getRate();

        call.enqueue(new Callback<getRateBean>() {
            @Override
            public void onResponse(Call<getRateBean> call, Response<getRateBean> response) {

                getRate = response.body().getRates().get(0).getPackegPrice();
                max = response.body().getRates().get(1).getConditionValue();
                maxValue = response.body().getRates().get(1).getPackegPrice();

                String stan = standard.getText().toString();

                Double p1 = Double.parseDouble(getRate);
                Double p2 = Double.parseDouble(maxValue);



                double a = Double.parseDouble(amount);
                double m = Double.parseDouble(max);
                double mv = Double.parseDouble(maxValue);



                getRate = String.valueOf(p1);

                if (a > m)
                {
                    standard.setText(stan + String.format("%.2f", mv));
                }
                else
                {
                    standard.setText(stan + String.format("%.2f", p1));
                }



                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<getRateBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });







        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = radio.getCheckedRadioButtonId();

                if (id == R.id.standard)
                {
                    rate = getRate;



                    progress.setVisibility(View.VISIBLE);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://marwadikhana.com/")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    bean b = (bean)getApplicationContext();

                    allAPIs cr = retrofit.create(allAPIs.class);


                    Call<orderBean> call = cr.createOrder(b.id);

                    Log.d("asdID" , b.id);

                    call.enqueue(new Callback<orderBean>() {
                        @Override
                        public void onResponse(Call<orderBean> call, Response<orderBean> response) {
                            Intent intent = new Intent(CreateOrder.this , Middle.class);

                            intent.putExtra(AvenuesParams.ACCESS_CODE , "AVSB69EB46CH38BSHC");
                            //intent.putExtra(AvenuesParams.ACCESS_CODE, "4YRUXLSRO20O8NIH");
                            intent.putExtra(AvenuesParams.MERCHANT_ID , "108560");
                            //intent.putExtra(AvenuesParams.MERCHANT_ID, "2");
                            intent.putExtra(AvenuesParams.ORDER_ID , response.body().getCartorder().get(0).getOrderId());
                            intent.putExtra(AvenuesParams.CURRENCY , "INR");
                            //intent.putExtra(AvenuesParams.AMOUNT , "1");

                            double r = Double.parseDouble(rate);
                            double a = Double.parseDouble(amount);
                            double m = Double.parseDouble(max);
                            double mv = Double.parseDouble(maxValue);

                            double t;

                            if (a > m)
                            {
                                t = mv + a;
                            }
                            else
                            {
                                t = r + a;
                            }





                            intent.putExtra(AvenuesParams.AMOUNT , String.valueOf(t));
                            intent.putExtra(AvenuesParams.REDIRECT_URL , "http://marwadikhana.com/merchant/ccavResponseHandler.php");
                            //intent.putExtra(AvenuesParams.REDIRECT_URL, "http://122.182.6.216/merchant/ccavResponseHandler.jsp");
                            intent.putExtra(AvenuesParams.CANCEL_URL , "http://marwadikhana.com/merchant/ccavResponseHandler.php");
                            //intent.putExtra(AvenuesParams.CANCEL_URL, "http://122.182.6.216/merchant/ccavResponseHandler.jsp");
                            intent.putExtra(AvenuesParams.RSA_KEY_URL , "http://marwadikhana.com/merchant/GetRSA.php");
                            intent.putExtra("entity" , response.body().getCartorder().get(0).getEntityId());
                            intent.putExtra("type" , "standard");

                            //intent.putExtra(AvenuesParams.RSA_KEY_URL, "http://122.182.6.216/merchant/GetRSA.jsp");


                            progress.setVisibility(View.GONE);

                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<orderBean> call, Throwable t) {
                            progress.setVisibility(View.GONE);
                            Log.d("asdasdasd" , t.toString());
                        }
                    });


                }
                else if (id == R.id.express)
                {
                    rate = "200";

                    progress.setVisibility(View.VISIBLE);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://marwadikhana.com/")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    bean b = (bean)getApplicationContext();

                    allAPIs cr = retrofit.create(allAPIs.class);


                    Call<orderBean> call = cr.createOrder(b.id);

                    call.enqueue(new Callback<orderBean>() {
                        @Override
                        public void onResponse(Call<orderBean> call, Response<orderBean> response) {
                            Intent intent = new Intent(CreateOrder.this , Middle.class);

                            intent.putExtra(AvenuesParams.ACCESS_CODE , "AVSB69EB46CH38BSHC");
                            //intent.putExtra(AvenuesParams.ACCESS_CODE, "4YRUXLSRO20O8NIH");
                            intent.putExtra(AvenuesParams.MERCHANT_ID , "108560");
                            //intent.putExtra(AvenuesParams.MERCHANT_ID, "2");
                            intent.putExtra(AvenuesParams.ORDER_ID , response.body().getCartorder().get(0).getOrderId());
                            intent.putExtra(AvenuesParams.CURRENCY , "INR");
                            //intent.putExtra(AvenuesParams.AMOUNT , "1");

                            int r = Integer.parseInt(rate);
                            int a = Integer.parseInt(amount);

                            int t = r + a;

                            intent.putExtra(AvenuesParams.AMOUNT , String.valueOf(t));
                            intent.putExtra(AvenuesParams.REDIRECT_URL , "http://marwadikhana.com/merchant/ccavResponseHandler.php");
                            //intent.putExtra(AvenuesParams.REDIRECT_URL, "http://122.182.6.216/merchant/ccavResponseHandler.jsp");
                            intent.putExtra(AvenuesParams.CANCEL_URL , "http://marwadikhana.com/merchant/ccavResponseHandler.php");
                            //intent.putExtra(AvenuesParams.CANCEL_URL, "http://122.182.6.216/merchant/ccavResponseHandler.jsp");
                            intent.putExtra(AvenuesParams.RSA_KEY_URL , "http://marwadikhana.com/merchant/GetRSA.php");
                            intent.putExtra("entity" , response.body().getCartorder().get(0).getEntityId());
                            intent.putExtra("type" , "express");

                            //intent.putExtra(AvenuesParams.RSA_KEY_URL, "http://122.182.6.216/merchant/GetRSA.jsp");


                            progress.setVisibility(View.GONE);

                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<orderBean> call, Throwable t) {
                            progress.setVisibility(View.GONE);
                        }
                    });


                }
                /*else if (id == R.id.cod)
                {
                    progress.setVisibility(View.VISIBLE);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://marwadikhana.com/")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    bean b = (bean)getApplicationContext();

                    allAPIs cr = retrofit.create(allAPIs.class);


                    Call<orderBean> call = cr.createOrder(b.id);

                    call.enqueue(new Callback<orderBean>() {
                        @Override
                        public void onResponse(Call<orderBean> call, Response<orderBean> response) {
                            Intent intent = new Intent(CreateOrder.this , CODClass.class);

                            intent.putExtra(AvenuesParams.ORDER_ID , response.body().getCartorder().get(0).getOrderId());

                            intent.putExtra("entity" , response.body().getCartorder().get(0).getEntityId());

                            progress.setVisibility(View.GONE);

                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<orderBean> call, Throwable t) {
                            progress.setVisibility(View.GONE);
                        }
                    });
                }*/

            }
        });






    }
}
