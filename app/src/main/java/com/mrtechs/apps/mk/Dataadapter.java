package com.mrtechs.apps.mk;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.StringTokenizer;

import static android.media.CamcorderProfile.get;


public class Dataadapter extends RecyclerView.Adapter<Dataadapter.MyviewHolder>{
    Context context;


    public Dataadapter(Context context){
        this.context=context;
    }



    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.cart_model, parent, false);

        return new MyviewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyviewHolder holder, int position) {



        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int number = Integer.parseInt(holder.quantity.getText().toString());

                number++;

                holder.quantity.setText(String.valueOf(number));


            }
        });


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
        return 7;
    }

    class MyviewHolder extends RecyclerView.ViewHolder{

        TextView quantity;
        ImageButton plus , minus;


        public MyviewHolder(View itemView) {
            super(itemView);

            quantity = (TextView)itemView.findViewById(R.id.quantity);
            plus = (ImageButton) itemView.findViewById(R.id.plus);
            minus = (ImageButton)itemView.findViewById(R.id.minus);

        }
    }


}
