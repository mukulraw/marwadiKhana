package com.mrtechs.apps.mk;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EditProfile extends AppCompatActivity {

    EditText first , last , email;

    TextView update;

    ProgressBar progress;

    Toolbar toolbar;
    SharedPreferences pref;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        pref = getSharedPreferences("pree" , MODE_PRIVATE);
        edit = pref.edit();

        progress = (ProgressBar)findViewById(R.id.progress);

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        first = (EditText)findViewById(R.id.first);
        last = (EditText)findViewById(R.id.last);
        email = (EditText)findViewById(R.id.email);

        update = (TextView)findViewById(R.id.change);

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

        update.setOnClickListener(new View.OnClickListener() {
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

                Call<updateProfileBean> call = cr.updateProfile(b.id , first.getText().toString() , last.getText().toString() , email.getText().toString());

                call.enqueue(new Callback<updateProfileBean>() {
                    @Override
                    public void onResponse(Call<updateProfileBean> call, Response<updateProfileBean> response) {


                        edit.remove("username");
                        edit.remove("password");

                        b.username = response.body().getFirstname() + " " +response.body().getLastname();

                        edit.apply();

                        progress.setVisibility(View.GONE);

                        finish();


                    }

                    @Override
                    public void onFailure(Call<updateProfileBean> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                    }
                });

            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();

        progress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://marwadikhana.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final bean b = (bean)getApplicationContext();

        allAPIs cr = retrofit.create(allAPIs.class);

        Call<profileBean> call = cr.viewProfile(b.id);

        call.enqueue(new Callback<profileBean>() {
            @Override
            public void onResponse(Call<profileBean> call, Response<profileBean> response) {

                first.setText(response.body().getFirstname());
                last.setText(response.body().getLastname());
                email.setText(response.body().getEmail());

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<profileBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });

    }


}
