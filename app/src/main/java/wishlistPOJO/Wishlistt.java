package wishlistPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wishlistt {


    @SerializedName("wishlistpro_id")
    @Expose
    private String wishlistproId;
    @SerializedName("wishliststore_id")
    @Expose
    private String wishliststoreId;
    @SerializedName("pro_id")
    @Expose
    private String proId;
    @SerializedName("pro_name")
    @Expose
    private String proName;
    @SerializedName("pro_qty")
    @Expose
    private String proQty;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("product_saleprice")
    @Expose
    private String productSaleprice;
    @SerializedName("product_shortdescription")
    @Expose
    private String productShortdescription;
    @SerializedName("product_img")
    @Expose
    private String productImg;

    public String getWishlistproId() {
        return wishlistproId;
    }

    public void setWishlistproId(String wishlistproId) {
        this.wishlistproId = wishlistproId;
    }

    public String getWishliststoreId() {
        return wishliststoreId;
    }

    public void setWishliststoreId(String wishliststoreId) {
        this.wishliststoreId = wishliststoreId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProQty() {
        return proQty;
    }

    public void setProQty(String proQty) {
        this.proQty = proQty;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSaleprice() {
        return productSaleprice;
    }

    public void setProductSaleprice(String productSaleprice) {
        this.productSaleprice = productSaleprice;
    }

    public String getProductShortdescription() {
        return productShortdescription;
    }

    public void setProductShortdescription(String productShortdescription) {
        this.productShortdescription = productShortdescription;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }


}
