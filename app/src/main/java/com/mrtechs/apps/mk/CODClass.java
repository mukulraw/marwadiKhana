package com.mrtechs.apps.mk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import codPOJO.codBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CODClass extends AppCompatActivity {

    String orderId;
    String entityId;


    EditText bFname , bLname , bAddress , bCity , bState , bZip , bCountry , bEmail;
    EditText sName , sAddress , sCity , sState , sZip , sCountry;

    CheckBox check;

    LinearLayout hide;

    ProgressBar progress;

    boolean isShip = false;

    Button place;


    Toolbar toolbar;

    String type;

    String amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codclass);

        orderId = getIntent().getStringExtra("AvenuesParams.ORDER_ID");
        entityId = getIntent().getStringExtra("entity");
        type = getIntent().getStringExtra("type");
        amount = getIntent().getStringExtra("amount");

        bFname = (EditText)findViewById(R.id.bname);
        bLname = (EditText)findViewById(R.id.blname);
        bAddress = (EditText)findViewById(R.id.baddress);
        bCity = (EditText)findViewById(R.id.bcity);
        bState = (EditText)findViewById(R.id.bstate);
        bZip = (EditText)findViewById(R.id.bzip);
        bCountry = (EditText)findViewById(R.id.bcountry);
        bEmail = (EditText)findViewById(R.id.bemail);
        sName = (EditText)findViewById(R.id.sname);
        sAddress = (EditText)findViewById(R.id.saddress);
        sCity = (EditText)findViewById(R.id.scity);
        sState = (EditText)findViewById(R.id.sstate);
        sZip = (EditText)findViewById(R.id.szip);
        sCountry = (EditText)findViewById(R.id.scountry);

        progress = (ProgressBar)findViewById(R.id.progress);

        check = (CheckBox)findViewById(R.id.check);

        hide = (LinearLayout)findViewById(R.id.shipping);

        place = (Button)findViewById(R.id.place);


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


        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {

                    hide.setVisibility(View.VISIBLE);
                    isShip = true;

                }
                else
                {
                    hide.setVisibility(View.GONE);
                    isShip = false;
                }

            }
        });


        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isShip)
                {


                    String bfname = bFname.getText().toString();
                    String blname = bLname.getText().toString();
                    String baddress = bAddress.getText().toString();
                    String bcity = bCity.getText().toString();
                    String bstate = bState.getText().toString();
                    String bzip = bZip.getText().toString();
                    String bcountry = bCountry.getText().toString();
                    String bemail = bEmail.getText().toString();

                    if (bfname.length()>0)
                    {
                        if (blname.length()>0)
                        {
                            if (baddress.length()>0)
                            {
                                if (bcity.length()>0)
                                {
                                    if (bstate.length()>0)
                                    {
                                        if (bzip.length()>0)
                                        {
                                            if (bcountry.length()>0)
                                            {
                                                if (bemail.length()>0)
                                                {

                                                    Log.d("asdasd" , "bClicked");

                                                    progress.setVisibility(View.VISIBLE);

                                                    Retrofit retrofit = new Retrofit.Builder()
                                                            .baseUrl("http://marwadikhana.com/")
                                                            .addConverterFactory(ScalarsConverterFactory.create())
                                                            .addConverterFactory(GsonConverterFactory.create())
                                                            .build();

                                                    bean b = (bean)getApplicationContext();

                                                    allAPIs cr = retrofit.create(allAPIs.class);


                                                    Call<codBean> call = cr.cod(entityId , b.id , orderId , bfname , blname , baddress , bcity , bstate , bzip , bcountry , bemail , bfname , baddress , bcity , bstate , bzip , bcountry , type , amount);

                                                    call.enqueue(new Callback<codBean>() {
                                                        @Override
                                                        public void onResponse(Call<codBean> call, Response<codBean> response) {

                                                            Intent intent = new Intent(CODClass.this , CodStatus.class);
                                                            intent.putExtra("status" , response.body().getOrderStatus().get(0).getStatus());

                                                            progress.setVisibility(View.GONE);

                                                            startActivity(intent);


                                                            finish();

                                                        }

                                                        @Override
                                                        public void onFailure(Call<codBean> call, Throwable t) {
                                                            progress.setVisibility(View.GONE);
                                                        }
                                                    });


                                                }
                                                else
                                                {
                                                    bEmail.setError("Invalid detail");
                                                }
                                            }
                                            else
                                            {
                                                bCountry.setError("Invalid detail");
                                            }
                                        }
                                        else
                                        {
                                            bZip.setError("Invalid detail");
                                        }
                                    }
                                    else
                                    {
                                        bState.setError("Invalid detail");
                                    }
                                }
                                else
                                {
                                    bCity.setError("Invalid detail");
                                }
                            }
                            else
                            {
                                bAddress.setError("Invalid detail");
                            }

                        }
                        else
                        {
                            bLname.setError("Invalid detail");
                        }
                    }
                    else
                    {
                        bFname.setError("Invalid detail");
                    }


                }
                else
                {

                    String bfname = bFname.getText().toString();
                    String blname = bLname.getText().toString();
                    String baddress = bAddress.getText().toString();
                    String bcity = bCity.getText().toString();
                    String bstate = bState.getText().toString();
                    String bzip = bZip.getText().toString();
                    String bcountry = bCountry.getText().toString();
                    String bemail = bEmail.getText().toString();

                    String sname = sName.getText().toString();
                    String saddress = sAddress.getText().toString();
                    String scity = sCity.getText().toString();
                    String sstate = sState.getText().toString();
                    String szip = sZip.getText().toString();
                    String scountry = sCountry.getText().toString();


                    if (bfname.length()>0)
                    {
                        if (blname.length()>0)
                        {
                            if (baddress.length()>0)
                            {
                                if (bcity.length()>0)
                                {
                                    if (bstate.length()>0)
                                    {
                                        if (bzip.length()>0)
                                        {
                                            if (bcountry.length()>0)
                                            {
                                                if (bemail.length()>0)
                                                {
                                                    if (sname.length()>0)
                                                    {
                                                        if (saddress.length()>0)
                                                        {
                                                            if (scity.length()>0)
                                                            {
                                                                if (sstate.length()>0)
                                                                {
                                                                    if (szip.length()>0)
                                                                    {
                                                                        if (scountry.length()>0)
                                                                        {

                                                                            progress.setVisibility(View.VISIBLE);

                                                                            Retrofit retrofit = new Retrofit.Builder()
                                                                                    .baseUrl("http://marwadikhana.com/")
                                                                                    .addConverterFactory(ScalarsConverterFactory.create())
                                                                                    .addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();

                                                                            bean b = (bean)getApplicationContext();

                                                                            allAPIs cr = retrofit.create(allAPIs.class);


                                                                            Call<codBean> call = cr.cod(entityId , b.id , orderId , bfname , blname , baddress , bcity , bstate , bzip , bcountry , bemail , sname , saddress , scity , sstate , szip , scountry , type , amount);

                                                                            call.enqueue(new Callback<codBean>() {
                                                                                @Override
                                                                                public void onResponse(Call<codBean> call, Response<codBean> response) {

                                                                                    Intent intent = new Intent(CODClass.this , CodStatus.class);
                                                                                    intent.putExtra("status" , response.body().getOrderStatus().get(0).getStatus());

                                                                                    progress.setVisibility(View.GONE);

                                                                                    startActivity(intent);
                                                                                    finish();
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Call<codBean> call, Throwable t) {
                                                                                    progress.setVisibility(View.GONE);
                                                                                }
                                                                            });

                                                                        }
                                                                        else
                                                                        {
                                                                            sCountry.setError("Invalid detail");
                                                                        }
                                                                    }
                                                                    else
                                                                    {
                                                                        sZip.setError("Invalid detail");
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    sState.setError("Invalid detail");
                                                                }
                                                            }
                                                            else
                                                            {
                                                                sCity.setError("Invalid detail");
                                                            }
                                                        }
                                                        else
                                                        {
                                                            sAddress.setError("Invalid detail");
                                                        }
                                                    }
                                                    else
                                                    {
                                                        sName.setError("Invalid detail");
                                                    }
                                                }
                                                else
                                                {
                                                    bEmail.setError("Invalid detail");
                                                }
                                            }
                                            else
                                            {
                                                bCountry.setError("Invalid detail");
                                            }
                                        }
                                        else
                                        {
                                            bZip.setError("Invalid detail");
                                        }
                                    }
                                    else
                                    {
                                        bState.setError("Invalid detail");
                                    }
                                }
                                else
                                {
                                    bCity.setError("Invalid detail");
                                }
                            }
                            else
                            {
                                bAddress.setError("Invalid detail");
                            }

                        }
                        else
                        {
                            bLname.setError("Invalid detail");
                        }
                    }
                    else
                    {
                        bFname.setError("Invalid detail");
                    }



                }


            }
        });


    }
}
