package com.mrtechs.apps.mk;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import QtyPOJO.qtyBean;
import cartDeletePOJO.deleteCartBean;
import cartPOJO.Cartheader;
import cartPOJO.cartBean;
import cdeletePOJO.cdeleteBean;
import orderPOJO.orderBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import utility.AvenuesParams;
import utility.ServiceUtility;

public class Cart extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv;
    GridLayoutManager manager;
    TextView checkOut;
    Dataadapter adapter;
    ProgressBar progress;
    TextView subTotal , grandTotal;
    List<Cartheader> list;
    TextView clear;
    String amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caard);
        toolbar= (Toolbar) findViewById(R.id.toolbar);

        progress = (ProgressBar)findViewById(R.id.progress);

        checkOut = (TextView)findViewById(R.id.check_out);

        clear = (TextView)findViewById(R.id.clear);

        list = new ArrayList<>();

        subTotal = (TextView)findViewById(R.id.sub_total);
        grandTotal = (TextView)findViewById(R.id.grand_total);

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Cart.this , CreateOrder.class);
                intent.putExtra("amount" , amount);
                startActivity(intent);

            }
        });


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

        rv = (RecyclerView) findViewById(R.id.recycler);


        manager = new GridLayoutManager(this , 1);
        adapter = new Dataadapter(this , list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress.setVisibility(View.VISIBLE);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://marwadikhana.com/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                bean b = (bean)getApplicationContext();

                allAPIs cr = retrofit.create(allAPIs.class);

                Call<cdeleteBean> call = cr.clearCart(b.id);

                call.enqueue(new Callback<cdeleteBean>() {
                    @Override
                    public void onResponse(Call<cdeleteBean> call , Response<cdeleteBean> response) {
                        progress.setVisibility(View.GONE);

                        list.clear();

                        adapter.setGridData(new ArrayList<Cartheader>());

                    }

                    @Override
                    public void onFailure(Call<cdeleteBean> call, Throwable t) {

                    }
                });

            }
        });



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

        Call<cartBean> call = cr.viewCart(b.id);

        call.enqueue(new Callback<cartBean>() {
            @Override
            public void onResponse(Call<cartBean> call, Response<cartBean> response) {



                list = new ArrayList<Cartheader>();

                list = response.body().getCartheader();

                adapter.setGridData(list);

                Log.d("asdasdSize1" , String.valueOf(list.size()));

                if (list.size() == 0)
                {
                    Log.d("asdasdSize2" , String.valueOf(list.size()));
                    checkOut.setVisibility(View.GONE);
                }

                amount = String.valueOf(response.body().getTotal());

                progress.setVisibility(View.GONE);

                subTotal.setText("Sub Total     Rs." + amount);

            }

            @Override
            public void onFailure(Call<cartBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Log.d("asderrorasd" , t.toString());
            }
        });

    }



    class Dataadapter extends RecyclerView.Adapter<Dataadapter.MyviewHolder>{
        Context context;
        private List<Cartheader> list = new ArrayList<>();

        Dataadapter(Context context, List<Cartheader> list){
            this.context = context;
            this.list = list;
        }

        void setGridData(List<Cartheader> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.cart_model, parent, false);

            return new MyviewHolder(itemView);

        }

        @Override
        public void onBindViewHolder(final MyviewHolder holder , final int position) {

            holder.setIsRecyclable(false);

            final Cartheader item = list.get(position);

            double a = Double.parseDouble(item.getProductPrice());

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

                    Call<qtyBean> call = cr.updateCart(b.id , item.getProId() , String.valueOf(number));

                    call.enqueue(new Callback<qtyBean>() {
                        @Override
                        public void onResponse(Call<qtyBean> call, Response<qtyBean> response) {

                            fetch();

                        }

                        @Override
                        public void onFailure(Call<qtyBean> call, Throwable t) {

                        }
                    });


                }
            });


            holder.quantity.setText(String.valueOf(item.getProductQty()));


            ImageLoader loader = ImageLoader.getInstance();

            DisplayImageOptions options = new DisplayImageOptions.Builder().resetViewBeforeLoading(false).cacheOnDisk(true).cacheInMemory(true).build();

            loader.displayImage(item.getProductImg() , holder.image , options);

            Double p1 = Double.parseDouble(item.getProductPrice());

            holder.price.setText("Rs " + String.format("%.2f", p1));
            holder.sku.setText("SKU: " + item.getProductSku());
            holder.name.setText(item.getProductName());

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

                    Call<qtyBean> call = cr.updateCart(b.id , item.getProId() , String.valueOf(number));

                    call.enqueue(new Callback<qtyBean>() {
                        @Override
                        public void onResponse(Call<qtyBean> call, Response<qtyBean> response) {

                            fetch();

                        }

                        @Override
                        public void onFailure(Call<qtyBean> call, Throwable t) {

                        }
                    });


                }
            });


            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Dialog dialog = new Dialog(Cart.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.cart_delete_dialog);
                    dialog.show();

                    TextView yes = (TextView)dialog.findViewById(R.id.yes);
                    TextView no = (TextView)dialog.findViewById(R.id.no);

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

                            Call<deleteCartBean> call = cr.deleteCartItem(b.id , item.getProId());

                            call.enqueue(new Callback<deleteCartBean>() {
                                @Override
                                public void onResponse(Call<deleteCartBean> call, Response<deleteCartBean> response) {

                                    fetch();

                                }

                                @Override
                                public void onFailure(Call<deleteCartBean> call, Throwable t) {

                                }
                            });

                            dialog.dismiss();

                        }
                    });


                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

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

        class MyviewHolder extends RecyclerView.ViewHolder{
            TextView quantity;
            ImageButton plus , minus;
            TextView name , sku , price;
            ImageView image;
            ImageButton delete;


            MyviewHolder(View itemView) {
                super(itemView);
                quantity = (TextView)itemView.findViewById(R.id.quantity);
                plus = (ImageButton) itemView.findViewById(R.id.plus);
                minus = (ImageButton)itemView.findViewById(R.id.minus);

                name = (TextView)itemView.findViewById(R.id.name);
                sku = (TextView)itemView.findViewById(R.id.sku);
                price = (TextView)itemView.findViewById(R.id.price);

                image = (ImageView)itemView.findViewById(R.id.image);

                delete = (ImageButton) itemView.findViewById(R.id.delete);
            }
        }
    }
}
