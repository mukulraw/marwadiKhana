package com.mrtechs.apps.mk;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.view.Window;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Register extends AppCompatActivity {

    EditText first , last , email , phone , password;
    Button register;
    TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        log = (TextView)findViewById(R.id.create);

        first = (EditText)findViewById(R.id.first);
        last = (EditText)findViewById(R.id.last);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);
        password = (EditText)findViewById(R.id.password);

        register = (Button)findViewById(R.id.login);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String f = first.getText().toString();
                String l = last.getText().toString();
                String e = email.getText().toString();
                String p = phone.getText().toString();
                String pa = password.getText().toString();


                if (f.length() > 0)
                {

                    if (l.length() > 0)
                    {

                        if (e.length() > 0)
                        {

                            if (p.length() > 0)
                            {

                                if (pa.length() > 0)
                                {

                                    final Dialog mOverlayDialog = new Dialog(Register.this);

                                    mOverlayDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    mOverlayDialog.setContentView(R.layout.progress_dialog);
                                    mOverlayDialog.setCancelable(false);
                                    mOverlayDialog.show();



                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl("http://marwadikhana.com/")
                                            .addConverterFactory(ScalarsConverterFactory.create())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    bean b = (bean)getApplicationContext();

                                    allAPIs cr = retrofit.create(allAPIs.class);

                                    Call<OTPbEAN> call = cr.otp(f , "" , l , e , pa , p);

                                    call.enqueue(new Callback<OTPbEAN>() {
                                        @Override
                                        public void onResponse(Call<OTPbEAN> call, Response<OTPbEAN> response) {

                                            Intent intent = new Intent(Register.this , OTP.class);
                                            intent.putExtra("otp" , String.valueOf(response.body().getOtp()));
                                            intent.putExtra("first" , response.body().getFirstname());
                                            intent.putExtra("last" , response.body().getLastname());
                                            intent.putExtra("email" , response.body().getEmail());
                                            intent.putExtra("phone" , response.body().getPhone());
                                            intent.putExtra("password" , response.body().getPassword());

                                            mOverlayDialog.dismiss();

                                            startActivity(intent);

                                            finish();

                                            Toast.makeText(Register.this , "An OTP has been sent to your Mobile number" , Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onFailure(Call<OTPbEAN> call, Throwable t) {

                                        }
                                    });





                                }
                                else
                                {
                                    Toast.makeText(Register.this , "Invalid Password" , Toast.LENGTH_SHORT).show();
                                }


                            }
                            else
                            {
                                Toast.makeText(Register.this , "Invalid Phone" , Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(Register.this , "Invalid Email" , Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(Register.this , "Invalid last name" , Toast.LENGTH_SHORT).show();
                    }


                }
                else
                {
                    Toast.makeText(Register.this , "Invalid first name" , Toast.LENGTH_SHORT).show();
                }


            }
        });




    }
}
