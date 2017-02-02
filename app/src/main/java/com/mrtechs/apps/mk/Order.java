package com.mrtechs.apps.mk;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Order extends AppCompatActivity {

    TabLayout tabs;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        pager = (ViewPager) findViewById(R.id.pager);

        tabs = (TabLayout) findViewById(R.id.tabs);

        tabs.addTab(tabs.newTab().setText("Billing"));
        tabs.addTab(tabs.newTab().setText("Shipping"));
        tabs.addTab(tabs.newTab().setText("Payment"));
        tabs.addTab(tabs.newTab().setText("Done"));


        View tab1 = (View) LayoutInflater.from(this).inflate(R.layout.tab_icon_model , null);

        TextView icon1 = (TextView)tab1.findViewById(R.id.icon);
        TextView text1 = (TextView)tab1.findViewById(R.id.text);

        icon1.setText("1");
        icon1.setTextColor(Color.WHITE);
        icon1.setBackground(getResources().getDrawable(R.drawable.circle_accent));

        text1.setText("Billing");
        text1.setTextColor(Color.RED);


        try
        {
            tabs.getTabAt(0).setCustomView(tab1);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }



        View tab2 = (View) LayoutInflater.from(this).inflate(R.layout.tab_icon_model , null);

        TextView icon2 = (TextView)tab2.findViewById(R.id.icon);
        TextView text2 = (TextView)tab2.findViewById(R.id.text);

        icon2.setText("2");
        icon2.setTextColor(Color.GRAY);
        icon2.setBackground(getResources().getDrawable(R.drawable.circle_gray));


        text2.setText("Shipping");
        text2.setTextColor(Color.GRAY);


        try
        {
            tabs.getTabAt(1).setCustomView(tab2);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }



        View tab3 = (View) LayoutInflater.from(this).inflate(R.layout.tab_icon_model , null);

        TextView icon3 = (TextView)tab3.findViewById(R.id.icon);
        TextView text3 = (TextView)tab3.findViewById(R.id.text);

        icon3.setText("3");
        icon3.setTextColor(Color.GRAY);
        icon3.setBackground(getResources().getDrawable(R.drawable.circle_gray));


        text3.setText("Payment");
        text3.setTextColor(Color.GRAY);


        try
        {
            tabs.getTabAt(2).setCustomView(tab3);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }



        View tab4 = (View) LayoutInflater.from(this).inflate(R.layout.tab_icon_model , null);

        TextView icon4 = (TextView)tab4.findViewById(R.id.icon);
        TextView text4 = (TextView)tab4.findViewById(R.id.text);

        icon4.setText("4");
        icon4.setTextColor(Color.GRAY);
        icon4.setBackground(getResources().getDrawable(R.drawable.circle_gray));



        text4.setText("Done");
        text4.setTextColor(Color.GRAY);


        try
        {
            tabs.getTabAt(3).setCustomView(tab4);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }



        LinearLayout tabStrip = ((LinearLayout)tabs.getChildAt(0));
        tabStrip.setEnabled(false);
        for(int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setClickable(false);
        }


        FragAdapter adapter = new FragAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);



    }


    public class FragAdapter extends FragmentStatePagerAdapter{


        public FragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new Billing();

                case 1:
                    return new Shipping();

                case 2:
                    return new Payment();

                case 3:
                    return new Done();

            }



            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }


    public static class Billing extends Fragment{



    }

    public static class Shipping extends Fragment{



    }


    public static class Payment extends Fragment{



    }


    public static class Done extends Fragment{



    }





}
