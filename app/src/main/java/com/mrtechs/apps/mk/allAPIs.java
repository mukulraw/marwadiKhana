package com.mrtechs.apps.mk;

import java.util.ArrayList;

import POJO.bannerBean;
import POJO.categoryBean;
import POJO.loginBean;
import POJO.productBean;
import POJO.subCatBean;
import ProdPOJO.singleProdBean;
import RatePOJO.rateBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface allAPIs {

    @Multipart
    @POST("marwadi/marwadi_app/login.php")
    Call<loginBean> login(@Part("email") String email, @Part("password") String password);

    @GET("marwadi/marwadi_app/category.php")
    Call<categoryBean> getCategories();

    @GET("marwadi/marwadi_app/home_banner.php")
    Call<bannerBean> getBanner();

    @Multipart
    @POST("marwadi/marwadi_app/subcategories.php")
    Call<subCatBean> getSubCategories(@Part("catid") String catId);


    @Multipart
    @POST("marwadi/marwadi_app/productbycategoryid.php")
    Call<productBean> getProducts(@Part("catid") String catId);

    @Multipart
    @POST("marwadi/marwadi_app/product_detail.php")
    Call<singleProdBean> getProductDetails(@Part("proid") String productId);

    @Multipart
    @POST("marwadi/marwadi_app/pro_review.php")
    Call<rateBean> getProductRatings(@Part("proid") String productId);

}
