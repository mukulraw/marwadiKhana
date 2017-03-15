package com.mrtechs.apps.mk;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ChangePassword extends AppCompatActivity {

    Toolbar toolbar;

    TextView change;

    ProgressBar progress;

    EditText oldpass , newpass;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        pref = getSharedPreferences("pree" , MODE_PRIVATE);
        edit = pref.edit();

        change = (TextView)findViewById(R.id.change);
        newpass = (EditText)findViewById(R.id.newpass);
        oldpass = (EditText)findViewById(R.id.oldpass);

        progress = (ProgressBar)findViewById(R.id.progress);

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



        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress.setVisibility(View.VISIBLE);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://marwadikhana.com/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final bean b = (bean)getApplicationContext();

                allAPIs cr = retrofit.create(allAPIs.class);


                Call<passwordBean> call = cr.changePassword(b.id , oldpass.getText().toString() , newpass.getText().toString());

                call.enqueue(new Callback<passwordBean>() {
                    @Override
                    public void onResponse(Call<passwordBean> call, Response<passwordBean> response) {

                        edit.remove("username");
                        edit.remove("password");
                        edit.apply();

                        Toast.makeText(ChangePassword.this , response.body().getStatus() , Toast.LENGTH_SHORT).show();

                        finish();

                    }

                    @Override
                    public void onFailure(Call<passwordBean> call, Throwable t) {

                    }
                });


            }
        });



    }
}
