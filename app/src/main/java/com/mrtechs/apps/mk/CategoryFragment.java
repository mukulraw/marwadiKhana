package com.mrtechs.apps.mk;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.relex.circleindicator.CircleIndicator;

public class CategoryFragment extends Fragment {

    ViewPager pager;
    CircleIndicator indicator;
    RecyclerView grid;
    GridLayoutManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_layout , container , false);

        pager = (ViewPager)view.findViewById(R.id.pager);
        indicator = (CircleIndicator)view.findViewById(R.id.indicator);
        grid = (RecyclerView)view.findViewById(R.id.grid);
        manager = new GridLayoutManager(getContext() , 2);



        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());

        pager.setAdapter(adapter);
        indicator.setViewPager(pager);

        CategoryAdapter adapter1 = new CategoryAdapter(getContext());
        grid.setAdapter(adapter1);
        grid.setLayoutManager(manager);


        return view;
    }


    private class PagerAdapter extends FragmentStatePagerAdapter
    {

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


    public static class pages extends Fragment
    {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


            return inflater.inflate(R.layout.pager_images , container , false);
        }
    }


    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
    {
        Context context;

        public CategoryAdapter(Context context)
        {
            this.context = context;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.grid_item_model , parent , false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class ViewHolder extends RecyclerView.ViewHolder{


            public ViewHolder(View itemView) {
                super(itemView);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                    }
                });

            }



        }

    }







}
