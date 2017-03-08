package com.mrtechs.apps.mk;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import POJO.loginBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Login extends AppCompatActivity {

    EditText email , password;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    Button login;

    TextView create;

    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("pree" , MODE_PRIVATE);
        edit = pref.edit();

        toast = Toast.makeText(this , null , Toast.LENGTH_SHORT);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        login = (Button)findViewById(R.id.login);

        create = (TextView)findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this , Register.class);
                startActivity(intent);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String e = email.getText().toString();
                final String p = password.getText().toString();

                if (e.length()>0)
                {

                    if (p.length()>0)
                    {

                        final Dialog mOverlayDialog = new Dialog(Login.this);

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

                        Call<loginBean> call = cr.login(e , p);

                        call.enqueue(new Callback<loginBean>() {
                            @Override
                            public void onResponse(Call<loginBean> call, Response<loginBean> response) {

                                if (Objects.equals(response.body().getStatus(), "Login Successfull"))
                                {
                                    bean b = (bean)getApplicationContext();

                                    b.id = response.body().getUserid();
                                    b.username = response.body().getFullname();

                                    edit.putString("username" , e);
                                    edit.putString("password" , p);
                                    edit.apply();

                                    Intent intent = new Intent(Login.this , GetStarted.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else if (Objects.equals(response.body().getStatus(), "Login Detail Invalid"))
                                {
                                    toast.setText("Invalid details");
                                    toast.show();
                                    email.setError("Invalid details");
                                    password.setError("Invalid details");
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
                        toast.setText("Invalid password");
                        password.setError("This field is required");
                        toast.show();
                    }

                }else
                {
                    toast.setText("Invalid email");
                    email.setError("This field is required");
                    toast.show();
                }


            }
        });

    }


}
