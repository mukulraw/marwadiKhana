package com.mrtechs.apps.mk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import POJO.Banner;
import POJO.productBean;
import ProdPOJO.Product;
import ProdPOJO.Productimg;
import ProdPOJO.attribute;
import ProdPOJO.singleProdBean;
import addCartPOJO.addCartBean;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import wishPOJO.wishBean;

public class SingleProductFragment extends Fragment {


    //String id = "";
    ViewPager image;
    TextView price;
    TextView category;
    TextView description;
    TextView name;
    Spinner spinner;
    RecyclerView grid;
    List<POJO.Product> list;
    LinearLayoutManager manager;
    String prodName = "";
    ProgressBar progress;
    Button add;
    LinearLayout rate;
    String id = "";
    TextView wishlist;
    CircleIndicator indicator;

    ImageButton minus , plus;
    TextView quantity;

    String size = "";

    String opid = "";

    boolean isDropDown = false;

    List<String> l2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.single_prod_layout , container , false);

        add = (Button) view.findViewById(R.id.add);
        image = (ViewPager) view.findViewById(R.id.image);
        price = (TextView)view.findViewById(R.id.price);
        category = (TextView)view.findViewById(R.id.category);
        description = (TextView)view.findViewById(R.id.description);
        grid = (RecyclerView)view.findViewById(R.id.grid);
        name = (TextView)view.findViewById(R.id.name);
        rate = (LinearLayout)view.findViewById(R.id.rate);
        indicator = (CircleIndicator)view.findViewById(R.id.indicator);
        wishlist = (TextView)view.findViewById(R.id.wishlist);
        progress = (ProgressBar)view.findViewById(R.id.progress);
        spinner = (Spinner)view.findViewById(R.id.spinner);

        minus = (ImageButton) view.findViewById(R.id.minus);
        plus = (ImageButton)view.findViewById(R.id.plus);
        quantity = (TextView)view.findViewById(R.id.quantity);

        l2 = new ArrayList<String>();

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String q = quantity.getText().toString();

                int q1 = Integer.parseInt(q);

                if (q1!=1)
                {
                    q1--;
                }

                quantity.setText(String.valueOf(q1));

            }
        });


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String q = quantity.getText().toString();

                int q1 = Integer.parseInt(q);

                    q1++;

                quantity.setText(String.valueOf(q1));

            }
        });


/*
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext() , Rate.class);

                Bundle b = new Bundle();

                b.putString("id" , id);
                b.putString("name" , prodName);

                intent.putExtras(b);

                startActivity(intent);

            }
        });
*/


        list = new ArrayList<>();

        manager = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false);


        id = getArguments().getString("id");


        final CategoryAdapter adapter = new CategoryAdapter(getContext() , list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nationproducts.in/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        allAPIs cr = retrofit.create(allAPIs.class);

        Call<singleProdBean> call = cr.getProductDetails(id);

        call.enqueue(new Callback<singleProdBean>() {
            @Override
            public void onResponse(Call<singleProdBean> call, Response<singleProdBean> response) {

                Product item = response.body().getProduct().get(0);

                name.setText(item.getProductName());

                prodName = item.getProductName();

                category.setText(item.getCatname());

                description.setText(item.getProductDescription());

                id = response.body().getProduct().get(0).getProId();

                PagerAdapter adapter = new PagerAdapter(getChildFragmentManager() , item.getProductMultiimg().getProductimg());

                image.setOffscreenPageLimit(item.getProductMultiimg().getProductimg().size() - 1);

                opid = item.getProductAttribute().getAttributeData().getAttributeId();

                image.setAdapter(adapter);
                indicator.setViewPager(image);

                //loader.displayImage(item.getProductImg() , image);

                price.setText("Rs " + item.getProductPrice());


                List<attribute> l = item.getProductAttribute().getValueData().getProductattribute();

                Log.d("asdasdasd" , item.getProId());

                l2.clear();

                l2.add(item.getProductAttribute().getAttributeData().getAttributeTitle());

                for (int i = 0 ; i < l.size() ; i++)
                {
                    l2.add(l.get(i).getAttributeValue());
                }

                if (l2.size()>0)
                {
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext() , R.layout.spinner_model , l2);
                    spinner.setAdapter(adapter1);
                    isDropDown = true;
                }
                else
                {
                    spinner.setVisibility(View.GONE);
                    isDropDown = false;
                }







            }

            @Override
            public void onFailure(Call<singleProdBean> call, Throwable t) {

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (isDropDown)
                {

                    if (size.length()>0)
                    {
                        progress.setVisibility(View.VISIBLE);

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://nationproducts.in/")
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        bean b = (bean)getContext().getApplicationContext();

                        allAPIs cr = retrofit.create(allAPIs.class);

                        Call<addCartBean> call = cr.addToCart(id , b.id , quantity.getText().toString() , size , opid);

                        call.enqueue(new Callback<addCartBean>() {
                            @Override
                            public void onResponse(Call<addCartBean> call, Response<addCartBean> response) {

                                progress.setVisibility(View.GONE);
                                Toast.makeText(getContext() , "Added successfully" , Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<addCartBean> call, Throwable t) {
                                progress.setVisibility(View.GONE);
                            }
                        });

                    }
                    else
                    {
                        Toast.makeText(getContext() , "Please select a size" , Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    progress.setVisibility(View.VISIBLE);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://nationproducts.in/")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    bean b = (bean)getContext().getApplicationContext();

                    allAPIs cr = retrofit.create(allAPIs.class);

                    Call<addCartBean> call = cr.addToCart(id , b.id , quantity.getText().toString() , "" , "");

                    call.enqueue(new Callback<addCartBean>() {
                        @Override
                        public void onResponse(Call<addCartBean> call, Response<addCartBean> response) {


                            progress.setVisibility(View.GONE);
                            Toast.makeText(getContext() , "Added successfully" , Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<addCartBean> call, Throwable t) {
                            progress.setVisibility(View.GONE);
                        }
                    });

                }



            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position>0)
                {
                    size = l2.get(position);
                }
                else
                {
                    size = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progress.setVisibility(View.VISIBLE);


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://nationproducts.in/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                bean b = (bean)getContext().getApplicationContext();

                allAPIs cr = retrofit.create(allAPIs.class);

                Call<wishBean> call = cr.addWishlist(id , b.id);


                call.enqueue(new Callback<wishBean>() {
                    @Override
                    public void onResponse(Call<wishBean> call, Response<wishBean> response) {
                        if (Objects.equals(response.body().getProductWishlist().get(0).getSuccess(), "1"))
                        {
                            progress.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<wishBean> call, Throwable t) {

                    }
                });



            }
        });



        Call<productBean> call2 = cr.getProducts(id);

        call2.enqueue(new Callback<productBean>() {
            @Override
            public void onResponse(Call<productBean> call, Response<productBean> response) {


                list = response.body().getProduct();

                adapter.setGridData(list);

            }

            @Override
            public void onFailure(Call<productBean> call, Throwable t) {

            }
        });



        return view;
    }


    private class PagerAdapter extends FragmentStatePagerAdapter
    {

        List<Productimg> list = new ArrayList<>();


        PagerAdapter(FragmentManager fm , List<Productimg> list) {
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
            return list.size();
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

            loader.displayImage(url , image);

            return view;
        }
    }


    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
    {
        Context context;
        List<POJO.Product> list = new ArrayList<>();

        public CategoryAdapter(Context context , List<POJO.Product> list)
        {
            this.context = context;
            this.list = list;
        }


        public void setGridData(List<POJO.Product> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.single_grid_model , parent , false);

            WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            Display d = wm.getDefaultDisplay();

            view.setMinimumWidth(d.getWidth()/3);


            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.setIsRecyclable(false);

            POJO.Product item = list.get(position);

            ImageLoader loader = ImageLoader.getInstance();

            loader.displayImage(item.getProductImg() , holder.image);

            holder.name.setText(item.getProductName());

            holder.price.setText("Rs. " + item.getProductPrice());

            holder.size.setText("SKU: "+item.getProductQty());



        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{


            ImageView image;
            TextView name , price , size;


            public ViewHolder(View itemView)
            {
                super(itemView);

                image = (ImageView)itemView.findViewById(R.id.image);
                name = (TextView)itemView.findViewById(R.id.name);
                price = (TextView)itemView.findViewById(R.id.price);
                size = (TextView)itemView.findViewById(R.id.size);


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
