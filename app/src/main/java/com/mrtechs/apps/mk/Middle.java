package com.mrtechs.apps.mk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import orderPOJO.orderBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import utility.AvenuesParams;

public class Middle extends AppCompatActivity {

    String access_code;
    String merchant_id;
    String order_id;
    String currency;
    String amount;
    String redirect_url;
    String cancel;
    String rsa_key_url;

    Button place;

    ProgressBar progress;

    RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle);

        progress = (ProgressBar)findViewById(R.id.progress);

        access_code = getIntent().getStringExtra(AvenuesParams.ACCESS_CODE);
        merchant_id = getIntent().getStringExtra(AvenuesParams.MERCHANT_ID);
        order_id = getIntent().getStringExtra(AvenuesParams.ORDER_ID);
        currency = getIntent().getStringExtra(AvenuesParams.CURRENCY);
        amount = getIntent().getStringExtra(AvenuesParams.AMOUNT);
        redirect_url = getIntent().getStringExtra(AvenuesParams.REDIRECT_URL);
        cancel = getIntent().getStringExtra(AvenuesParams.CANCEL_URL);
        rsa_key_url = getIntent().getStringExtra(AvenuesParams.RSA_KEY_URL);

        place = (Button)findViewById(R.id.place);

        radio = (RadioGroup)findViewById(R.id.radio);


        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int id = radio.getCheckedRadioButtonId();

                if (id == R.id.online)
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

                    Log.d("asdID" , b.id);

                    call.enqueue(new Callback<orderBean>() {
                        @Override
                        public void onResponse(Call<orderBean> call, Response<orderBean> response) {
                            Intent intent = new Intent(Middle.this , WebViewActivity.class);

                            intent.putExtra(AvenuesParams.ACCESS_CODE , "AVSB69EB46CH38BSHC");
                            //intent.putExtra(AvenuesParams.ACCESS_CODE, "4YRUXLSRO20O8NIH");
                            intent.putExtra(AvenuesParams.MERCHANT_ID , "108560");
                            //intent.putExtra(AvenuesParams.MERCHANT_ID, "2");
                            intent.putExtra(AvenuesParams.ORDER_ID , response.body().getCartorder().get(0).getOrderId());
                            intent.putExtra(AvenuesParams.CURRENCY , "INR");
                            //intent.putExtra(AvenuesParams.AMOUNT , "1");

                            intent.putExtra(AvenuesParams.AMOUNT , amount);
                            intent.putExtra(AvenuesParams.REDIRECT_URL , "http://marwadikhana.com/merchant/ccavResponseHandler.php");
                            //intent.putExtra(AvenuesParams.REDIRECT_URL, "http://122.182.6.216/merchant/ccavResponseHandler.jsp");
                            intent.putExtra(AvenuesParams.CANCEL_URL , "http://marwadikhana.com/merchant/ccavResponseHandler.php");
                            //intent.putExtra(AvenuesParams.CANCEL_URL, "http://122.182.6.216/merchant/ccavResponseHandler.jsp");
                            intent.putExtra(AvenuesParams.RSA_KEY_URL , "http://marwadikhana.com/merchant/GetRSA.php");
                            intent.putExtra("entity" , response.body().getCartorder().get(0).getEntityId());


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
                else if (id == R.id.cod)
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
                            Intent intent = new Intent(Middle.this , CODClass.class);

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

                }


            }
        });


    }
}
