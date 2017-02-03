package com.mrtechs.apps.mk;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Order extends AppCompatActivity {

    static TabLayout tabs;
    static MyViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        pager = (MyViewPager) findViewById(R.id.pager);

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


        pager.setSwipeable(false);


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

        TextView name , contin;

        @Nullable
        @Override
        public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.billing_layout , container , false);

            name = (TextView)view.findViewById(R.id.name);
            name.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

            contin = (TextView)view.findViewById(R.id.contin);

            contin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    View tab1 = tabs.getTabAt(0).getCustomView();

                    TextView icon1 = (TextView)tab1.findViewById(R.id.icon);
                    TextView text1 = (TextView)tab1.findViewById(R.id.text);

                    icon1.setText("1");
                    icon1.setTextColor(Color.GRAY);
                    icon1.setBackground(getResources().getDrawable(R.drawable.circle_gray));

                    text1.setText("Billing");
                    text1.setTextColor(Color.GRAY);




                    View tab2 = tabs.getTabAt(1).getCustomView();

                    TextView icon2 = (TextView)tab2.findViewById(R.id.icon);
                    TextView text2 = (TextView)tab2.findViewById(R.id.text);

                    icon2.setText("2");
                    icon2.setTextColor(Color.WHITE);
                    icon2.setBackground(getResources().getDrawable(R.drawable.circle_accent));


                    text2.setText("Shipping");
                    text2.setTextColor(Color.RED);

                    TabLayout.Tab tab = tabs.getTabAt(1);
                    tab.select();

                    pager.setCurrentItem(1);

                }
            });

            return view;
        }
    }

    public static class Shipping extends Fragment{

        TextView billing;

        TextView contin , back;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.shipping_layout , container , false);


            billing = (TextView)view.findViewById(R.id.billing);
            billing.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

            contin = (TextView)view.findViewById(R.id.contin);
            back = (TextView)view.findViewById(R.id.back);

            contin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    View tab1 = tabs.getTabAt(1).getCustomView();

                    TextView icon1 = (TextView)tab1.findViewById(R.id.icon);
                    TextView text1 = (TextView)tab1.findViewById(R.id.text);

                    icon1.setText("2");
                    icon1.setTextColor(Color.GRAY);
                    icon1.setBackground(getResources().getDrawable(R.drawable.circle_gray));

                    text1.setText("Shipping");
                    text1.setTextColor(Color.GRAY);




                    View tab2 = tabs.getTabAt(2).getCustomView();

                    TextView icon2 = (TextView)tab2.findViewById(R.id.icon);
                    TextView text2 = (TextView)tab2.findViewById(R.id.text);

                    icon2.setText("3");
                    icon2.setTextColor(Color.WHITE);
                    icon2.setBackground(getResources().getDrawable(R.drawable.circle_accent));


                    text2.setText("Payment");
                    text2.setTextColor(Color.RED);

                    TabLayout.Tab tab = tabs.getTabAt(2);
                    tab.select();

                    pager.setCurrentItem(2);

                }
            });


            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    View tab1 = tabs.getTabAt(1).getCustomView();

                    TextView icon1 = (TextView)tab1.findViewById(R.id.icon);
                    TextView text1 = (TextView)tab1.findViewById(R.id.text);

                    icon1.setText("2");
                    icon1.setTextColor(Color.GRAY);
                    icon1.setBackground(getResources().getDrawable(R.drawable.circle_gray));

                    text1.setText("Shipping");
                    text1.setTextColor(Color.GRAY);




                    View tab2 = tabs.getTabAt(0).getCustomView();

                    TextView icon2 = (TextView)tab2.findViewById(R.id.icon);
                    TextView text2 = (TextView)tab2.findViewById(R.id.text);

                    icon2.setText("1");
                    icon2.setTextColor(Color.WHITE);
                    icon2.setBackground(getResources().getDrawable(R.drawable.circle_accent));


                    text2.setText("Billing");
                    text2.setTextColor(Color.RED);

                    TabLayout.Tab tab = tabs.getTabAt(0);
                    tab.select();

                    pager.setCurrentItem(0);

                }
            });


            return view;
        }
    }


    public static class Payment extends Fragment{

        TextView contin , back;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.payment_layout , container , false);


            contin = (TextView)view.findViewById(R.id.contin);
            back = (TextView)view.findViewById(R.id.back);


            contin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    View tab1 = tabs.getTabAt(2).getCustomView();

                    TextView icon1 = (TextView)tab1.findViewById(R.id.icon);
                    TextView text1 = (TextView)tab1.findViewById(R.id.text);

                    icon1.setText("3");
                    icon1.setTextColor(Color.GRAY);
                    icon1.setBackground(getResources().getDrawable(R.drawable.circle_gray));

                    text1.setText("Payment");
                    text1.setTextColor(Color.GRAY);




                    View tab2 = tabs.getTabAt(3).getCustomView();

                    TextView icon2 = (TextView)tab2.findViewById(R.id.icon);
                    TextView text2 = (TextView)tab2.findViewById(R.id.text);

                    icon2.setText("4");
                    icon2.setTextColor(Color.WHITE);
                    icon2.setBackground(getResources().getDrawable(R.drawable.circle_accent));


                    text2.setText("Done");
                    text2.setTextColor(Color.RED);

                    TabLayout.Tab tab = tabs.getTabAt(3);
                    tab.select();

                    pager.setCurrentItem(3);

                }
            });


            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    View tab1 = tabs.getTabAt(2).getCustomView();

                    TextView icon1 = (TextView)tab1.findViewById(R.id.icon);
                    TextView text1 = (TextView)tab1.findViewById(R.id.text);

                    icon1.setText("3");
                    icon1.setTextColor(Color.GRAY);
                    icon1.setBackground(getResources().getDrawable(R.drawable.circle_gray));

                    text1.setText("Payment");
                    text1.setTextColor(Color.GRAY);




                    View tab2 = tabs.getTabAt(1).getCustomView();

                    TextView icon2 = (TextView)tab2.findViewById(R.id.icon);
                    TextView text2 = (TextView)tab2.findViewById(R.id.text);

                    icon2.setText("2");
                    icon2.setTextColor(Color.WHITE);
                    icon2.setBackground(getResources().getDrawable(R.drawable.circle_accent));


                    text2.setText("Shipping");
                    text2.setTextColor(Color.RED);

                    TabLayout.Tab tab = tabs.getTabAt(1);
                    tab.select();

                    pager.setCurrentItem(1);

                }
            });


            return view;
        }
    }


    public static class Done extends Fragment{


        TextView back;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.done_layout , container , false);

            back = (TextView)view.findViewById(R.id.back);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    View tab1 = tabs.getTabAt(3).getCustomView();

                    TextView icon1 = (TextView)tab1.findViewById(R.id.icon);
                    TextView text1 = (TextView)tab1.findViewById(R.id.text);

                    icon1.setText("4");
                    icon1.setTextColor(Color.GRAY);
                    icon1.setBackground(getResources().getDrawable(R.drawable.circle_gray));

                    text1.setText("Done");
                    text1.setTextColor(Color.GRAY);




                    View tab2 = tabs.getTabAt(2).getCustomView();

                    TextView icon2 = (TextView)tab2.findViewById(R.id.icon);
                    TextView text2 = (TextView)tab2.findViewById(R.id.text);

                    icon2.setText("3");
                    icon2.setTextColor(Color.WHITE);
                    icon2.setBackground(getResources().getDrawable(R.drawable.circle_accent));


                    text2.setText("Payment");
                    text2.setTextColor(Color.RED);

                    TabLayout.Tab tab = tabs.getTabAt(2);
                    tab.select();

                    pager.setCurrentItem(2);

                }
            });

            return view;
        }
    }





}
