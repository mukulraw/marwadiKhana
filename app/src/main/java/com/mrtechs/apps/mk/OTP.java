package com.mrtechs.apps.mk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class OTP extends AppCompatActivity {

    String otp , first , last , email , phone , password;

    EditText userotp;

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        otp = getIntent().getStringExtra("otp");
        first = getIntent().getStringExtra("first");
        last = getIntent().getStringExtra("last");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        password = getIntent().getStringExtra("password");

        register = (Button)findViewById(R.id.register);
        userotp = (EditText)findViewById(R.id.otp);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://marwadikhana.com/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                bean b = (bean)getApplicationContext();

                allAPIs cr = retrofit.create(allAPIs.class);

                Call<otpConfirmBean> call = cr.confirmotp(first , "" , last , email , password , phone , otp , userotp.getText().toString());

                call.enqueue(new Callback<otpConfirmBean>() {
                    @Override
                    public void onResponse(Call<otpConfirmBean> call, Response<otpConfirmBean> response) {
                        Toast.makeText(OTP.this , response.body().getStatus() , Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<otpConfirmBean> call, Throwable t) {

                    }
                });

            }
        });




    }
}
