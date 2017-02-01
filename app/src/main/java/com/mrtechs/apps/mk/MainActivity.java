package com.mrtechs.apps.mk;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    static LinearLayout layoutToReplace;
    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        layoutToReplace = (LinearLayout)findViewById(R.id.layout_to_replace);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        } catch (NullPointerException e1) {
            e1.printStackTrace();
        }


        drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open , R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        CategoryFragment frag = new CategoryFragment();
        ft.replace(R.id.layout_to_replace , frag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.commit();


    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main , menu);





        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.cart)
        {
            Intent intent = new Intent(getApplicationContext() , Cart.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);



    }

    public static class CategoryFragment extends Fragment {

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

                            FragmentManager fm = ((MainActivity)context).getSupportFragmentManager();

                            FragmentTransaction ft = fm.beginTransaction();

                            SubCategoryFragment frag = new SubCategoryFragment();

                            ft.replace(R.id.layout_to_replace , frag);
                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                            ft.addToBackStack(null);
                            ft.commit();



                        }
                    });

                }



            }

        }







    }



    public static class SubCategoryFragment extends Fragment {


        TabLayout tabs;
        ViewPager pager;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.sub_category_layout , container , false);

            tabs = (TabLayout)view.findViewById(R.id.tabs);
            pager = (ViewPager)view.findViewById(R.id.pager);


            tabs.addTab(tabs.newTab().setText("CATEGORY"));
            tabs.addTab(tabs.newTab().setText("CATEGORY"));
            tabs.addTab(tabs.newTab().setText("CATEGORY"));
            tabs.addTab(tabs.newTab().setText("CATEGORY"));








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

            PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());

            pager.setAdapter(adapter);




            tabs.setupWithViewPager(pager);

            tabs.getTabAt(0).setText("CATEGORY");
            tabs.getTabAt(1).setText("CATEGORY");
            tabs.getTabAt(2).setText("CATEGORY");
            tabs.getTabAt(3).setText("CATEGORY");



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

                        itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                FragmentManager fm = ((MainActivity)context).getSupportFragmentManager();

                                FragmentTransaction ft = fm.beginTransaction();

                                SingleProductFragment frag = new SingleProductFragment();

                                ft.replace(R.id.layout_to_replace , frag);
                                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                                ft.addToBackStack(null);
                                ft.commit();

                            }
                        });

                    }
                }

            }








        }







    }




}
