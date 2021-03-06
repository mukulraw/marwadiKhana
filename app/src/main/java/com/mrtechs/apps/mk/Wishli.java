package com.mrtechs.apps.mk;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import QtyPOJO.qtyBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import wishPOJO.wishBean;
import wishQtyPOJO.wishQtyBean;
import wishlistPOJO.Wishlist;
import wishlistPOJO.wishlistBean;

public class Wishli extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView grid;
    List<Wishlist> list;
    GridLayoutManager manager;
    WishAdapter adapter;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        progress = (ProgressBar)findViewById(R.id.progress);

        list = new ArrayList<>();
        manager = new GridLayoutManager(this , 1);

        adapter = new WishAdapter(this , list);

        toolbar= (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        grid = (RecyclerView)findViewById(R.id.grid);

        grid.setLayoutManager(manager);
        grid.setAdapter(adapter);

    }


    @Override
    protected void onResume() {
        super.onResume();



        fetch();

    }

    public void fetch()
    {
        if (progress.getVisibility() == View.GONE)
        {
            progress.setVisibility(View.VISIBLE);
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://marwadikhana.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bean b = (bean)getApplicationContext();

        allAPIs cr = retrofit.create(allAPIs.class);


        Call<wishlistBean> call = cr.wishlist(b.id);

        call.enqueue(new Callback<wishlistBean>() {
            @Override
            public void onResponse(Call<wishlistBean> call, Response<wishlistBean> response) {

                list = response.body().getItemDetail().getWishlist();

                adapter.setGridData(list);

                progress.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<wishlistBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }

    class WishAdapter extends RecyclerView.Adapter<WishAdapter.ViewHolder> {

        private List<Wishlist> list = new ArrayList<>();
        private Context context;


        WishAdapter(Context context, List<Wishlist> list)
        {
            this.context = context;
            this.list = list;
        }


        void setGridData(List<Wishlist> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.wishlist_item_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            final Wishlist item = list.get(position);

            ImageLoader loader = ImageLoader.getInstance();
            loader.displayImage(item.getProductImg() , holder.image);

            double quan = Double.parseDouble(item.getProQty());

            int q = (int)quan;

            holder.quantity.setText(String.valueOf(q));


            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int number = Integer.parseInt(holder.quantity.getText().toString());

                    number++;

                    holder.quantity.setText(String.valueOf(number));

                    progress.setVisibility(View.VISIBLE);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://marwadikhana.com/")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    bean b = (bean)context.getApplicationContext();

                    allAPIs cr = retrofit.create(allAPIs.class);

                    Call<wishQtyBean> call = cr.updateWishlist(b.id , item.getProId() , String.valueOf(number));

                    call.enqueue(new Callback<wishQtyBean>() {
                        @Override
                        public void onResponse(Call<wishQtyBean> call, Response<wishQtyBean> response) {

                            fetch();

                        }

                        @Override
                        public void onFailure(Call<wishQtyBean> call, Throwable t) {

                        }
                    });


                }
            });


            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int number = Integer.parseInt(holder.quantity.getText().toString());

                    number--;

                    holder.quantity.setText(String.valueOf(number));

                    progress.setVisibility(View.VISIBLE);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://marwadikhana.com/")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    bean b = (bean)context.getApplicationContext();

                    allAPIs cr = retrofit.create(allAPIs.class);

                    Call<wishQtyBean> call = cr.updateWishlist(b.id , item.getProId() , String.valueOf(number));

                    call.enqueue(new Callback<wishQtyBean>() {
                        @Override
                        public void onResponse(Call<wishQtyBean> call, Response<wishQtyBean> response) {

                            fetch();

                        }

                        @Override
                        public void onFailure(Call<wishQtyBean> call, Throwable t) {

                        }
                    });


                }
            });


            holder.name.setText(item.getProName());

            Double p1 = Double.parseDouble(item.getProductPrice());

            holder.price.setText(String.format("%.2f", p1));

            holder.description.setText(item.getProductShortdescription());

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final Dialog dialog = new Dialog(Wishli.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.cart_delete_dialog);
                    dialog.show();

                    TextView yes = (TextView)dialog.findViewById(R.id.yes);
                    TextView no = (TextView)dialog.findViewById(R.id.no);

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();

                        }
                    });

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            progress.setVisibility(View.VISIBLE);

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://marwadikhana.com/")
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            bean b = (bean)context.getApplicationContext();

                            allAPIs cr = retrofit.create(allAPIs.class);

                            Call<wishBean> call = cr.deleteWishlist(item.getProId() , b.id);

                            call.enqueue(new Callback<wishBean>() {
                                @Override
                                public void onResponse(Call<wishBean> call, Response<wishBean> response) {

                                    fetch();
                                    notifyItemRemoved(position);

                                }

                                @Override
                                public void onFailure(Call<wishBean> call, Throwable t) {
                                    progress.setVisibility(View.GONE);
                                }
                            });

                            dialog.dismiss();

                        }
                    });



                }
            });


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            ImageButton delete;
            ImageButton plus , minus;
            TextView description , quantity , price , name;
            ImageView image;

            ViewHolder(View itemView) {
                super(itemView);

                delete = (ImageButton)itemView.findViewById(R.id.delete);
                description = (TextView)itemView.findViewById(R.id.description);
                quantity = (TextView)itemView.findViewById(R.id.quantity);
                price = (TextView)itemView.findViewById(R.id.price);
                name = (TextView)itemView.findViewById(R.id.name);
                image = (ImageView)itemView.findViewById(R.id.image);
                plus = (ImageButton) itemView.findViewById(R.id.plus);
                minus = (ImageButton)itemView.findViewById(R.id.minus);

            }
        }

    }

}
