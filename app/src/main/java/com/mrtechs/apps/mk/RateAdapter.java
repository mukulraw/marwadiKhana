package com.mrtechs.apps.mk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import RatePOJO.Detail;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.ViewHolder>{



    private List<Detail> list = new ArrayList<>();
    private Context context;

    RateAdapter(Context context, List<Detail> list)
    {
        this.list = list;
        this.context = context;
    }

    void setGridData(List<Detail> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rate_list_model , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Detail item = list.get(position);

        float value = Float.parseFloat(item.getRatedata().get(2).getPercant());
        float quality = Float.parseFloat(item.getRatedata().get(1).getPercant());
        float price = Float.parseFloat(item.getRatedata().get(0).getPercant());

        holder.value.setRating(value/20);
        holder.price.setRating(price/20);
        holder.quality.setRating(quality/20);

        holder.subject.setText(item.getTitle());
        holder.name.setText("Review by " + item.getNickname());
        holder.comment.setText(item.getDesc());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView comment , name , subject;
        RatingBar value , quality , price;

        public ViewHolder(View itemView) {
            super(itemView);

            comment = (TextView)itemView.findViewById(R.id.comment);
            name = (TextView)itemView.findViewById(R.id.name);
            subject = (TextView)itemView.findViewById(R.id.subject);

            value = (RatingBar)itemView.findViewById(R.id.value);
            quality = (RatingBar)itemView.findViewById(R.id.quality);
            price = (RatingBar)itemView.findViewById(R.id.price);

        }
    }

}
