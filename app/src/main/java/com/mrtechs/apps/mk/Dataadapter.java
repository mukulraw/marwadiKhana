package com.mrtechs.apps.mk;

import android.content.Context;
import android.content.Intent;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import cartDeletePOJO.deleteCartBean;
import cartPOJO.Cartheader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.media.CamcorderProfile.get;


public class Dataadapter extends RecyclerView.Adapter<Dataadapter.MyviewHolder>{
    Context context;
    private List<Cartheader> list = new ArrayList<>();


    public Dataadapter(Context context , List<Cartheader> list){
        this.context = context;
        this.list = list;
    }


    public void setGridData(List<Cartheader> list)
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
    public void onBindViewHolder(final MyviewHolder holder, final int position) {

        final Cartheader item = list.get(position);

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int number = Integer.parseInt(holder.quantity.getText().toString());

                number++;

                holder.quantity.setText(String.valueOf(number));


            }
        });







        ImageLoader loader = ImageLoader.getInstance();

        loader.displayImage(item.getProductImg() , holder.image);

        holder.price.setText("Rs " + item.getProductPrice());
        holder.sku.setText("SKU: " + item.getProductSku());
        holder.name.setText(item.getProductName());

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int number = Integer.parseInt(holder.quantity.getText().toString());

                number--;

                holder.quantity.setText(String.valueOf(number));


            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                        notifyItemRemoved(position);

                    }

                    @Override
                    public void onFailure(Call<deleteCartBean> call, Throwable t) {

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


        public MyviewHolder(View itemView) {
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
