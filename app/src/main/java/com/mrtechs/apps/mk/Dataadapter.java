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

import cartPOJO.Cartheader;

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
    public void onBindViewHolder(final MyviewHolder holder, int position) {

        Cartheader item = list.get(position);

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


        public MyviewHolder(View itemView) {
            super(itemView);

            quantity = (TextView)itemView.findViewById(R.id.quantity);
            plus = (ImageButton) itemView.findViewById(R.id.plus);
            minus = (ImageButton)itemView.findViewById(R.id.minus);

            name = (TextView)itemView.findViewById(R.id.name);
            sku = (TextView)itemView.findViewById(R.id.sku);
            price = (TextView)itemView.findViewById(R.id.price);

            image = (ImageView)itemView.findViewById(R.id.image);

        }
    }


}
