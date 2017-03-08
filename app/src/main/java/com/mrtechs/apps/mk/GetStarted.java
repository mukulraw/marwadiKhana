package com.mrtechs.apps.mk;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import POJO.Banner;
import POJO.bannerBean;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class GetStarted extends AppCompatActivity {

    //AutoScrollViewPager pager;
    Button getStarted;
    //CircleIndicator indicator;
    //List<Banner> blist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        //pager = (AutoScrollViewPager) findViewById(R.id.pager);
        getStarted = (Button) findViewById(R.id.get_started);
        //indicator = (CircleIndicator) findViewById(R.id.indicator);

        //blist = new ArrayList<>();

        //indicator.setViewPager(pager);

        //pager.startAutoScroll(5000);




                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(intent);
                finish();



    }


    @Override
    protected void onResume() {
        super.onResume();


        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://marwadikhana.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        allAPIs cr = retrofit.create(allAPIs.class);

        Call<bannerBean> call = cr.getBanner();

        call.enqueue(new Callback<bannerBean>() {
            @Override
            public void onResponse(Call<bannerBean> call, Response<bannerBean> response) {

                blist = response.body().getBanner();

                PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager() , blist);

                pager.setAdapter(adapter);

                pager.setOffscreenPageLimit(blist.size()-5);

                indicator.setViewPager(pager);


            }

            @Override
            public void onFailure(Call<bannerBean> call, Throwable t) {

            }
        });
*/


    }

    private class PagerAdapter extends FragmentStatePagerAdapter
    {

        List<Banner> list = new ArrayList<>();


        PagerAdapter(FragmentManager fm , List<Banner> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {

            pages p = new pages();
            Bundle b = new Bundle();
            b.putString("image" , list.get(position).getImgUrl());
            p.setArguments(b);
            return p;

        }

        @Override
        public int getCount() {
            return list.size()-4;
        }
    }


    public static class pages extends Fragment
    {

        String url = "";
        ImageView image;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.pager_images , container , false);
            url = getArguments().getString("image");

            image = (ImageView)view.findViewById(R.id.image);

            ImageLoader loader = ImageLoader.getInstance();

            loader.displayImage("http://" + url , image);

            return view;
        }
    }


}
