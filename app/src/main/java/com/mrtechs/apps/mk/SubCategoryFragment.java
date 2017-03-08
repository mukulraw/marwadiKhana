package com.mrtechs.apps.mk;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.print.PrintHelper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import POJO.Product;
import POJO.Subcategory;
import POJO.productBean;
import POJO.subCatBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SubCategoryFragment extends Fragment {


    TabLayout tabs;
    ViewPager pager;
    List<Subcategory> list;



    String id = "";
    String image = "";
    ImageView banner;
    TextView name;
    ImageLoader loader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_category_layout , container , false);

        tabs = (TabLayout)view.findViewById(R.id.tabs);
        pager = (ViewPager)view.findViewById(R.id.pager);

        id = getArguments().getString("id");
        image = getArguments().getString("image");

        banner = (ImageView)view.findViewById(R.id.banner);

        name = (TextView)view.findViewById(R.id.name);

        name.setText(getArguments().getString("name"));

        list = new ArrayList<>();

        loader = ImageLoader.getInstance();

        loader.displayImage(image , banner);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://marwadikhana.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        allAPIs cr = retrofit.create(allAPIs.class);

        Call<subCatBean> call = cr.getSubCategories(id);



        call.enqueue(new Callback<subCatBean>() {
            @Override
            public void onResponse(Call<subCatBean> call, Response<subCatBean> response) {


                list = response.body().getSubcategory();

                for (int i = 0 ; i < list.size() ; i++)
                {
                    tabs.addTab(tabs.newTab().setText(list.get(i).getSubcatName()));
                }


                PagerAdapterr adapter = new PagerAdapterr(getChildFragmentManager() , list);

                pager.setAdapter(adapter);

                tabs.setupWithViewPager(pager);

                for (int i = 0 ; i < list.size() ; i++)
                {
                    try {
                        tabs.getTabAt(i).setText(list.get(i).getSubcatName());
                    }catch (NullPointerException e)
                    {
                        e.printStackTrace();
                    }
                }

                pager.setOffscreenPageLimit(list.size()-1);


            }

            @Override
            public void onFailure(Call<subCatBean> call, Throwable t) {

            }
        });









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








        return view;
    }




    private class PagerAdapterr extends FragmentStatePagerAdapter {

        List<Subcategory> list = new ArrayList<>();


        PagerAdapterr(FragmentManager fm , List<Subcategory> list) {
            super(fm);
            this.list = list;
        }


        @Override
        public Fragment getItem(int position) {
            pages p = new pages();
            Bundle b = new Bundle();

            b.putString("id" , list.get(position).getSubcatId());
            p.setArguments(b);

            return p;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


    public static class pages extends Fragment{

        RecyclerView grid;
        GridLayoutManager manager;

        List<Product> list;

        String id = "";

        CategoryAdapter adapter;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.pager_grid , container , false);

            id = getArguments().getString("id");



            list = new ArrayList<>();

            grid = (RecyclerView)view.findViewById(R.id.grid);

            manager = new GridLayoutManager(getContext() , 1);

            adapter = new CategoryAdapter(getContext() , list);

            grid.setLayoutManager(manager);
            grid.setAdapter(adapter);



            return view;
        }


        @Override
        public void onResume() {
            super.onResume();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://marwadikhana.com/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            allAPIs cr = retrofit.create(allAPIs.class);

            Call<productBean> call = cr.getProducts(id);


            Log.d("asdasdasd" , id);

            call.enqueue(new Callback<productBean>() {
                @Override
                public void onResponse(Call<productBean> call, Response<productBean> response) {

                    list = response.body().getProduct();

                    adapter.setGridData(response.body().getProduct());



                }

                @Override
                public void onFailure(Call<productBean> call, Throwable t) {

                }
            });



        }

        private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
        {
            Context context;
            List<Product> list = new ArrayList<>();

            public CategoryAdapter(Context context , List<Product> list)
            {
                this.context = context;
                this.list = list;
            }


            public void setGridData(List<Product> list)
            {
                this.list = list;
                notifyDataSetChanged();
            }


            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View view = inflater.inflate(R.layout.prod_model , parent , false);



                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {

                holder.setIsRecyclable(false);

                Product item = list.get(position);

                holder.rating.setRating(Float.parseFloat(item.getProRating()) / 20);

                //String htmlText = "<html><body><font color=\"#808080\"> %s </font></body></Html>";

                DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                        .cacheOnDisc(true).resetViewBeforeLoading(true).build();

                ImageLoader loader = ImageLoader.getInstance();

                loader.displayImage(item.getProductImg(), holder.image, options);

                holder.name.setText(item.getProductName());

                Double p1 = Double.parseDouble(item.getProductPrice());




                holder.price.setText("Rs " + String.format("%.2f", p1));

                holder.quantity.setText("SKU: " + item.getProductSku());

            }

            @Override
            public int getItemCount() {
                return list.size();
            }

            class ViewHolder extends RecyclerView.ViewHolder{


                ImageView image;
                TextView name , price , quantity;
                RatingBar rating;


                public ViewHolder(View itemView)
                {
                    super(itemView);

                    image = (ImageView)itemView.findViewById(R.id.image);
                    name = (TextView)itemView.findViewById(R.id.name);
                    price = (TextView)itemView.findViewById(R.id.price);
                    quantity = (TextView)itemView.findViewById(R.id.quantity);
                    rating = (RatingBar)itemView.findViewById(R.id.rating);

                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            FragmentManager fm = ((MainActivity)context).getSupportFragmentManager();

                            FragmentTransaction ft = fm.beginTransaction();

                            SingleProductFragment frag = new SingleProductFragment();

                            Bundle b = new Bundle();

                            b.putString("id" , list.get(getAdapterPosition()).getProId());

                            frag.setArguments(b);

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
