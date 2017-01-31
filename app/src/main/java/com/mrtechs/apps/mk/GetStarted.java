package com.mrtechs.apps.mk;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.relex.circleindicator.CircleIndicator;

public class GetStarted extends AppCompatActivity {

    ViewPager pager;
    Button getStarted;
    CircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        pager = (ViewPager) findViewById(R.id.pager);
        getStarted = (Button) findViewById(R.id.get_started);
        indicator = (CircleIndicator) findViewById(R.id.indicator);


        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);
        indicator.setViewPager(pager);




        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }


    private class PagerAdapter extends FragmentStatePagerAdapter{

        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new pages();
        }

        @Override
        public int getCount() {
            return 4;
        }
    }


    public static class pages extends Fragment{

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


            return inflater.inflate(R.layout.pager_images , container , false);
        }
    }


}
