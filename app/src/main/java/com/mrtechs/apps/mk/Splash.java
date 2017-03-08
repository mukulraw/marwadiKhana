package com.mrtechs.apps.mk;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import POJO.loginBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Splash extends AppCompatActivity {

    Timer t;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pref = getSharedPreferences("pree" , MODE_PRIVATE);

        toast = Toast.makeText(Splash.this , null , Toast.LENGTH_SHORT);

        final String user = pref.getString("username" , "");
        final String pass = pref.getString("password" , "");


        if (user.length()>0 && pass.length()>0)
        {

            final Dialog mOverlayDialog = new Dialog(Splash.this);

            mOverlayDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mOverlayDialog.setContentView(R.layout.progress_dialog);
            mOverlayDialog.setCancelable(false);
            mOverlayDialog.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://marwadikhana.com/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            allAPIs cr = retrofit.create(allAPIs.class);

            Call<loginBean> call = cr.login(user , pass);

            call.enqueue(new Callback<loginBean>() {
                @Override
                public void onResponse(Call<loginBean> call, Response<loginBean> response) {

                    if (Objects.equals(response.body().getStatus(), "Login Successfull"))
                    {
                        bean b = (bean)getApplicationContext();

                        b.id = response.body().getUserid();
                        b.username = response.body().getFullname();


                        Intent intent = new Intent(Splash.this , GetStarted.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (Objects.equals(response.body().getStatus(), "Login Detail Invalid"))
                    {
                        toast.setText("Invalid details");
                        toast.show();
                    }


                    mOverlayDialog.dismiss();

                }

                @Override
                public void onFailure(Call<loginBean> call, Throwable t) {

                    mOverlayDialog.dismiss();

                }
            });

        }
        else
        {

            t = new Timer();

            t.schedule(new TimerTask() {
                @Override
                public void run() {

                    Intent intent = new Intent(Splash.this , Login.class);
                    startActivity(intent);
                    finish();



                }
            } , 1500);
        }



    }
}
