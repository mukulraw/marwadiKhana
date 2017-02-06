package com.mrtechs.apps.mk;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import POJO.productBean;
import ProdPOJO.Product;
import ProdPOJO.singleProdBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SingleProductFragment extends Fragment {


    String id = "";
    ImageView image;
    TextView price;
    TextView category;
    TextView description;
    TextView name;
    RecyclerView grid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.single_prod_layout , container , false);

        image = (ImageView)view.findViewById(R.id.image);
        price = (TextView)view.findViewById(R.id.price);
        category = (TextView)view.findViewById(R.id.category);
        description = (TextView)view.findViewById(R.id.description);
        grid = (RecyclerView)view.findViewById(R.id.grid);
        name = (TextView)view.findViewById(R.id.name);


        id = getArguments().getString("id");


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

                ImageLoader loader = ImageLoader.getInstance();

                loader.displayImage(item.getProductImg() , image);

                price.setText(item.getProductPrice());

            }

            @Override
            public void onFailure(Call<singleProdBean> call, Throwable t) {

            }
        });




        Call<productBean> call2 = cr.getProducts(id);

        call2.enqueue(new Callback<productBean>() {
            @Override
            public void onResponse(Call<productBean> call, Response<productBean> response) {



            }

            @Override
            public void onFailure(Call<productBean> call, Throwable t) {

            }
        });



        return view;
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

            View view = inflater.inflate(R.layout.prod_model , parent , false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.setIsRecyclable(false);

            POJO.Product item = list.get(position);

            ImageLoader loader = ImageLoader.getInstance();

            loader.displayImage(item.getProductImg() , holder.image);

            holder.name.setText(item.getProductName());

            holder.price.setText(item.getProductPrice());

            holder.quantity.setText("SKU: "+item.getProductSku());

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
