package com.mrtechs.apps.mk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CODClass extends AppCompatActivity {

    String orderId;
    String entityId;


    EditText bFname , bLname , bAddress , bCity , bState , bZip , bCountry , bEmail;
    EditText sName , sAddress , sCity , sState , sZip , sCountry;

    CheckBox check;

    LinearLayout hide;

    Button place;


    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codclass);

        orderId = getIntent().getStringExtra("AvenuesParams.ORDER_ID");
        entityId = getIntent().getStringExtra("entity");

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

                }
                else
                {
                    hide.setVisibility(View.GONE);
                }

            }
        });


    }
}
