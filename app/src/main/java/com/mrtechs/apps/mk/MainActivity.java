package com.mrtechs.apps.mk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import countPOJO.countBean;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    static LinearLayout layoutToReplace;
    DrawerLayout drawer;

    public static TextView countt;

    TextView name;
    TextView home , wish , cart , logout;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("pree" , MODE_PRIVATE);
        edit = pref.edit();

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        layoutToReplace = (LinearLayout)findViewById(R.id.layout_to_replace);


        name = (TextView)findViewById(R.id.name);
        home = (TextView)findViewById(R.id.home);
        wish = (TextView)findViewById(R.id.wishlist);
        cart = (TextView)findViewById(R.id.cart);
        logout = (TextView)findViewById(R.id.log_out);





        bean b = (bean)getApplicationContext();

        name.setText("Hello, " + b.username);

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


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , Cart.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext() , Wishli.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this , Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                edit.remove("username");
                edit.remove("password");
                edit.apply();

                startActivity(i);
                finish();

            }
        });



        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        CategoryFragment frag = new CategoryFragment();
        ft.replace(R.id.layout_to_replace , frag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main , menu);



        View view = (View)menu.findItem(R.id.cart).getActionView();

        countt = ( TextView )view. findViewById(R.id.co);

        updateCount();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext() , Cart.class);
                startActivity(intent);

            }
        });

        //updateCount();

        return true;
    }


    public void updateCount()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://marwadikhana.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bean b = (bean)getApplicationContext();

        allAPIs cr = retrofit.create(allAPIs.class);

        Call<countBean> call = cr.getCount(b.id);

        call.enqueue(new Callback<countBean>() {
            @Override
            public void onResponse(Call<countBean> call, Response<countBean> response) {


                countt.setText(String.valueOf(response.body().getCarttotal().get(0).getTotalCount()));


            }

            @Override
            public void onFailure(Call<countBean> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


      /*  if (item.getItemId() == R.id.cart)
        {
            Intent intent = new Intent(getApplicationContext() , Cart.class);
            startActivity(intent);
        }*/


        return super.onOptionsItemSelected(item);



    }

    @Override
    protected void onResume() {
        super.onResume();

        updateCount();

    }

    /*public static class CategoryFragment extends Fragment
    {

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

    */



    public static class SubCategoryFragment extends Fragment {


        TabLayout tabs;
        ViewPager pager;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.sub_category_layout , container , false);

            tabs = (TabLayout)view.findViewById(R.id.tabs);
            pager = (ViewPager)view.findViewById(R.id.pager);


            tabs.addTab(tabs.newTab().setText("SUB-CATEGORY"));
            tabs.addTab(tabs.newTab().setText("SUB-CATEGORY"));
            tabs.addTab(tabs.newTab().setText("SUB-CATEGORY"));
            tabs.addTab(tabs.newTab().setText("SUB-CATEGORY"));








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

            tabs.getTabAt(0).setText("SUB-CATEGORY");
            tabs.getTabAt(1).setText("SUB-CATEGORY");
            tabs.getTabAt(2).setText("SUB-CATEGORY");
            tabs.getTabAt(3).setText("SUB-CATEGORY");



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
