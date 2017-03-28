package com.mrtechs.apps.mk;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import POJO.Banner;
import POJO.Category;
import POJO.bannerBean;
import POJO.categoryBean;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CategoryFragment extends Fragment {

    AutoScrollViewPager pager;
    CircleIndicator indicator;
    RecyclerView grid;
    GridLayoutManager manager;
    List<Category> list;
    CategoryAdapter adapter1;
    List<Banner> blist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_layout, container, false);

        pager = (AutoScrollViewPager) view.findViewById(R.id.pager);
        indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        grid = (RecyclerView) view.findViewById(R.id.grid);
        manager = new GridLayoutManager(getContext(), 2);


        blist = new ArrayList<>();
        list = new ArrayList<>();

        adapter1 = new CategoryAdapter(getContext(), list);
        grid.setAdapter(adapter1);
        grid.setLayoutManager(manager);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://marwadikhana.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        allAPIs cr = retrofit.create(allAPIs.class);

        Call<categoryBean> call = cr.getCategories();
        Call<bannerBean> call2 = cr.getBanner();

        call.enqueue(new Callback<categoryBean>() {
            @Override
            public void onResponse(Call<categoryBean> call, Response<categoryBean> response) {


                list = response.body().getCategory();
                adapter1.setGridData(list);


            }

            @Override
            public void onFailure(Call<categoryBean> call, Throwable t) {

            }
        });


        call2.enqueue(new Callback<bannerBean>() {
            @Override
            public void onResponse(Call<bannerBean> call, Response<bannerBean> response) {

                try {
                    blist = response.body().getBanner();

                    PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(), blist);

                    pager.setAdapter(adapter);
                    pager.setOffscreenPageLimit(blist.size() - 5);

                    indicator.setViewPager(pager);

                    pager.startAutoScroll(5000);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<bannerBean> call, Throwable t) {

            }
        });


    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        List<Banner> list = new ArrayList<>();


        PagerAdapter(FragmentManager fm, List<Banner> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {

            pages p = new pages();
            Bundle b = new Bundle();
            b.putString("image", list.get(position).getImgUrl());
            p.setArguments(b);
            return p;

        }

        @Override
        public int getCount() {
            return list.size() - 4;
        }
    }


    public static class pages extends Fragment {

        String url = "";
        ImageView image;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.pager_images, container, false);
            url = getArguments().getString("image");

            image = (ImageView) view.findViewById(R.id.image);

            ImageLoader loader = ImageLoader.getInstance();

            loader.displayImage("http://" + url, image);

            return view;
        }
    }


    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
        Context context;
        List<Category> list = new ArrayList<>();

        public CategoryAdapter(Context context, List<Category> list) {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<Category> list) {
            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.grid_item_model, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.setIsRecyclable(false);

            Category item = list.get(position);

            holder.name.setText(item.getCatName());

            DisplayImageOptions options = new DisplayImageOptions.Builder().resetViewBeforeLoading(false).cacheOnDisk(true).cacheInMemory(true).build();

            ImageLoader loader = ImageLoader.getInstance();

            if (item.getCatImage().length() > 0) {
                loader.displayImage(item.getCatImage(), holder.image, options);
            }


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {


            ImageView image;
            TextView name;


            public ViewHolder(View itemView) {
                super(itemView);

                image = (ImageView) itemView.findViewById(R.id.image);
                name = (TextView) itemView.findViewById(R.id.name);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        FragmentManager fm = ((MainActivity) context).getSupportFragmentManager();

                        FragmentTransaction ft = fm.beginTransaction();

                        SubCategoryFragment frag = new SubCategoryFragment();

                        Bundle b = new Bundle();
                        b.putString("id", list.get(getAdapterPosition()).getCatId());
                        b.putString("image", list.get(getAdapterPosition()).getCatImage());
                        b.putString("name", list.get(getAdapterPosition()).getCatName());

                        frag.setArguments(b);

                        ft.replace(R.id.layout_to_replace, frag);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        ft.addToBackStack(null);
                        ft.commit();


                    }
                });

            }


        }

    }


}
