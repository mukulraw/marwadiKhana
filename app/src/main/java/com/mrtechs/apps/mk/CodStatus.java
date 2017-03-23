package com.mrtechs.apps.mk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CodStatus extends AppCompatActivity {

    TextView statu;

    ProgressBar progress;

    String message;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cod_status);

        statu = (TextView)findViewById(R.id.status);

        message = getIntent().getStringExtra("status");

        Toast.makeText(CodStatus.this , message , Toast.LENGTH_SHORT).show();

        statu.setText(message);


    }
}
