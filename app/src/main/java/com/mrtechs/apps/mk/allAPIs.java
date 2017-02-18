package com.mrtechs.apps.mk;

import POJO.bannerBean;
import POJO.categoryBean;
import POJO.loginBean;
import POJO.productBean;
import POJO.subCatBean;
import ProdPOJO.singleProdBean;
import QtyPOJO.qtyBean;
import RatePOJO.rateBean;
import addCartPOJO.addCartBean;
import cartDeletePOJO.deleteCartBean;
import cartPOJO.cartBean;
import cdeletePOJO.cdeleteBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import reviewPOJO.reviewBean;
import wishPOJO.wishBean;
import wishQtyPOJO.wishQtyBean;
import wishlistPOJO.wishlistBean;

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

    @Multipart
    @POST("marwadi/marwadi_app/add_review.php")
    Call<reviewBean> rateProduct(@Part("proid") String productId , @Part("userid") String userId , @Part("title") String title , @Part("detail") String detail , @Part("username") String userName, @Part("valuerate") String valueRate , @Part("qualityrate") String qualityRate , @Part("pricerate") String priceRate);

    @Multipart
    @POST("marwadi/marwadi_app/add_wishlist.php")
    Call<wishBean> addWishlist(@Part("proid") String productId , @Part("userid") String userId);

    @Multipart
    @POST("marwadi/marwadi_app/wishlist.php")
    Call<wishlistBean> wishlist(@Part("userid") String userId);

    @Multipart
    @POST("marwadi/marwadi_app/wishlist_delete.php")
    Call<wishBean> deleteWishlist(@Part("proid") String productId , @Part("userid") String userId);

    @Multipart
    @POST("marwadi/marwadi_app/addtocart.php")
    Call<addCartBean> addToCart(@Part("proid") String productId , @Part("userid") String userId , @Part("qty") String quantity , @Part("size") String size , @Part("opid") String opid);

    @Multipart
    @POST("marwadi/marwadi_app/cart_header.php")
    Call<cartBean> viewCart(@Part("userid") String userId);

    @Multipart
    @POST("marwadi/marwadi_app/delete_cart.php")
    Call<cdeleteBean> clearCart(@Part("userid") String userId);

    @Multipart
    @POST("marwadi/marwadi_app/delete_cartbyId.php")
    Call<deleteCartBean> deleteCartItem(@Part("userid") String userId , @Part("proid") String proid);

    @Multipart
    @POST("marwadi/marwadi_app/update_qty.php")
    Call<qtyBean> updateCart(@Part("userid") String userId , @Part("proid") String proid , @Part("qty") String qty);

    @Multipart
    @POST("marwadi/marwadi_app/wishlist_update.php")
    Call<wishQtyBean> updateWishlist(@Part("userid") String userId , @Part("proid") String proid , @Part("qty") String qty);
}
