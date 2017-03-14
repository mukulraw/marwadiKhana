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
import countPOJO.countBean;
import getRatePOJO.getRateBean;
import orderPOJO.orderBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import reviewPOJO.reviewBean;
import statusPOJO.statusBean;
import wishPOJO.wishBean;
import wishQtyPOJO.wishQtyBean;
import wishlistPOJO.wishlistBean;

public interface allAPIs {

    @Multipart
    @POST("marwadi_app/login.php")
    Call<loginBean> login(@Part("email") String email, @Part("password") String password);

    @GET("marwadi_app/category.php")
    Call<categoryBean> getCategories();

    @GET("marwadi_app/home_banner.php")
    Call<bannerBean> getBanner();

    @Multipart
    @POST("marwadi_app/subcategories.php")
    Call<subCatBean> getSubCategories(@Part("catid") String catId);


    @Multipart
    @POST("marwadi_app/productbycategoryid.php")
    Call<productBean> getProducts(@Part("catid") String catId);

    @Multipart
    @POST("marwadi_app/product_detail.php")
    Call<singleProdBean> getProductDetails(@Part("proid") String productId);

    @Multipart
    @POST("marwadi_app/pro_review.php")
    Call<rateBean> getProductRatings(@Part("proid") String productId);

    @Multipart
    @POST("marwadi_app/add_review.php")
    Call<reviewBean> rateProduct(@Part("proid") String productId , @Part("userid") String userId , @Part("title") String title , @Part("detail") String detail , @Part("username") String userName, @Part("valuerate") String valueRate , @Part("qualityrate") String qualityRate , @Part("pricerate") String priceRate);

    @Multipart
    @POST("marwadi_app/add_wishlist.php")
    Call<wishBean> addWishlist(@Part("proid") String productId , @Part("userid") String userId);

    @Multipart
    @POST("marwadi_app/wishlist.php")
    Call<wishlistBean> wishlist(@Part("userid") String userId);

    @Multipart
    @POST("marwadi_app/wishlist_delete.php")
    Call<wishBean> deleteWishlist(@Part("proid") String productId , @Part("userid") String userId);

    @Multipart
    @POST("marwadi_app/addtocart.php")
    Call<addCartBean> addToCart(@Part("proid") String productId , @Part("userid") String userId , @Part("qty") String quantity , @Part("size") String size , @Part("opid") String opid);

    @Multipart
    @POST("marwadi_app/cart_header.php")
    Call<cartBean> viewCart(@Part("userid") String userId);

    @Multipart
    @POST("marwadi_app/delete_cart.php")
    Call<cdeleteBean> clearCart(@Part("userid") String userId);

    @Multipart
    @POST("marwadi_app/delete_cartbyId.php")
    Call<deleteCartBean> deleteCartItem(@Part("userid") String userId , @Part("proid") String proid);

    @Multipart
    @POST("marwadi_app/update_qty.php")
    Call<qtyBean> updateCart(@Part("userid") String userId , @Part("proid") String proid , @Part("qty") String qty);

    @Multipart
    @POST("marwadi_app/wishlist_update.php")
    Call<wishQtyBean> updateWishlist(@Part("userid") String userId , @Part("proid") String proid , @Part("qty") String qty);

    @Multipart
    @POST("marwadi_app/cart_count.php")
    Call<countBean> getCount(@Part("userid") String userId);

    @Multipart
    @POST("marwadi_app/create_order.php")
    Call<orderBean> createOrder(@Part("userid") String userId);

    @Multipart
    @POST("marwadi_app/order_status.php")
    Call<statusBean> status(@Part("entityid") String entityId , @Part("userid") String userId , @Part("order_id") String orderId , @Part("amount") String amount , @Part("billing_name") String billingName , @Part("billing_address") String billingAddress , @Part("billing_city") String billingCity , @Part("billing_state") String billingState , @Part("billing_zip") String billingZip , @Part("billing_country") String billingCountry , @Part("billing_email") String billingEmail , @Part("dilivery_name") String deliveryName , @Part("dilivery_address") String deliveryAddress , @Part("dilivery_city") String deliveryCity , @Part("dilivery_state") String deliveryState , @Part("dilivery_zip") String deliveryZip ,@Part("dilivery_country") String deliveryCountry , @Part("tracking_id") String trackingId , @Part("order_status") String orderStatus);

    @Multipart
    @POST("marwadi_app/register.php")
    Call<otpConfirmBean> otp(@Part("firstname") String firstName , @Part("middlename") String middleName , @Part("lastname") String lastName , @Part("email") String email , @Part("password") String password , @Part("phone") String phone);

    @Multipart
    @POST("marwadi_app/register.php")
    Call<otpConfirmBean> confirmotp(@Part("firstname") String firstName , @Part("middlename") String middleName , @Part("lastname") String lastName , @Part("email") String email , @Part("password") String password , @Part("phone") String phone , @Part("real_otp") String realOTP , @Part("user_otp") String userOTP);

    @GET("marwadi_app/table_rates.php")
    Call<getRateBean> getRate();

    @Multipart
    @POST("marwadi_app/slaes_update.php")
    Call<String> updateSale(@Part("userid") String userId , @Part("firstname") String first , @Part("lastname") String last , @Part("email") String email);

}