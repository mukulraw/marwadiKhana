package com.mrtechs.apps.mk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import wishlistPOJO.*;

public class WishAdapter extends RecyclerView.Adapter<WishAdapter.ViewHolder> {

    private List<Wishlistt> list = new ArrayList<>();
    private Context context;


    public WishAdapter(Context context , List<Wishlistt> list)
    {
        this.context = context;
        this.list = list;
    }


    public void setGridData(List<Wishlistt> list)
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
    public void onBindViewHolder(ViewHolder holder, int position) {

        Wishlistt item = list.get(position);

        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(item.getProductImg() , holder.image);

        holder.name.setText(item.getProName());
        holder.price.setText(item.getProductPrice());
        holder.description.setText(item.getProductShortdescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageButton delete;
        TextView description , quantity , price , name;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            delete = (ImageButton)itemView.findViewById(R.id.delete);
            description = (TextView)itemView.findViewById(R.id.description);
            quantity = (TextView)itemView.findViewById(R.id.quantity);
            price = (TextView)itemView.findViewById(R.id.price);
            name = (TextView)itemView.findViewById(R.id.name);
            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }

}
