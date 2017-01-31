package com.mrtechs.apps.mk;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SubCategoryFragment extends Fragment {


    TabLayout tabs;
    ViewPager pager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_category_layout , container , false);

        tabs = (TabLayout)view.findViewById(R.id.tabs);
        pager = (ViewPager)view.findViewById(R.id.pager);


        tabs.addTab(tabs.newTab().setText("CATEGORY NAME"));
        tabs.addTab(tabs.newTab().setText("CATEGORY NAME"));
        tabs.addTab(tabs.newTab().setText("CATEGORY NAME"));
        tabs.addTab(tabs.newTab().setText("CATEGORY NAME"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                pager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        android.support.v4.view.PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());

        pager.setAdapter(adapter);





        return view;
    }


    private class PagerAdapter extends FragmentStatePagerAdapter {

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

        RecyclerView grid;
        GridLayoutManager manager;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.pager_grid , container , false);

            grid = (RecyclerView)view.findViewById(R.id.grid);

            manager = new GridLayoutManager(getContext() , 1);


            CategoryAdapter adapter = new CategoryAdapter(getContext());

            grid.setLayoutManager(manager);
            grid.setAdapter(adapter);

            return view;
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

                View view = inflater.inflate(R.layout.prod_model , parent , false);

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
                }
            }

        }








    }







}
