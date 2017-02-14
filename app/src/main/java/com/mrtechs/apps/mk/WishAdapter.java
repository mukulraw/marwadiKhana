package com.mrtechs.apps.mk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.FloatProperty;
import android.util.Log;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import wishPOJO.wishBean;
import wishlistPOJO.*;

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
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Wishlist item = list.get(position);

        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(item.getProductImg() , holder.image);

        holder.name.setText(item.getProName());
        holder.price.setText(item.getProductPrice());
        holder.description.setText(item.getProductShortdescription());

        Float quan = Float.parseFloat(item.getProQty());

        int q = Float.floatToIntBits(quan);

        Log.d("asdasdasd" , String.valueOf(q));

        holder.quantity.setText(String.valueOf(q));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://nationproducts.in/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                bean b = (bean)context.getApplicationContext();

                allAPIs cr = retrofit.create(allAPIs.class);

                Call<wishBean> call = cr.deleteWishlist(item.getProId() , b.id);

                call.enqueue(new Callback<wishBean>() {
                    @Override
                    public void onResponse(Call<wishBean> call, Response<wishBean> response) {



                    }

                    @Override
                    public void onFailure(Call<wishBean> call, Throwable t) {

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
        }
    }

}
