package ProdPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("pro_id")
    @Expose
    private String proId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("product_shortdescription")
    @Expose
    private String productShortdescription;
    @SerializedName("catid")
    @Expose
    private String catid;
    @SerializedName("catname")
    @Expose
    private String catname;
    @SerializedName("subcatid")
    @Expose
    private String subcatid;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("product_tag")
    @Expose
    private Producttag productTag;
    @SerializedName("product_attribute")
    @Expose
    private ProductAttribute productAttribute;
    @SerializedName("product_saleprice")
    @Expose
    private String productSaleprice;
    @SerializedName("product_qty")
    @Expose
    private String productQty;
    @SerializedName("product_img")
    @Expose
    private String productImg;
    @SerializedName("product_multiimg")
    @Expose
    private ProductMultiimg productMultiimg;
    @SerializedName("product_sku")
    @Expose
    private String productSku;
    @SerializedName("product_stock")
    @Expose
    private String productStock;
    @SerializedName("pro_rating")
    @Expose
    private String proRating;

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductShortdescription() {
        return productShortdescription;
    }

    public void setProductShortdescription(String productShortdescription) {
        this.productShortdescription = productShortdescription;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getSubcatid() {
        return subcatid;
    }

    public void setSubcatid(String subcatid) {
        this.subcatid = subcatid;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Producttag getProductTag() {
        return productTag;
    }

    public void setProductTag(Producttag productTag) {
        this.productTag = productTag;
    }

    public ProductAttribute getProductAttribute() {
        return productAttribute;
    }

    public void setProductAttribute(ProductAttribute productAttribute) {
        this.productAttribute = productAttribute;
    }

    public String getProductSaleprice() {
        return productSaleprice;
    }

    public void setProductSaleprice(String productSaleprice) {
        this.productSaleprice = productSaleprice;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public ProductMultiimg getProductMultiimg() {
        return productMultiimg;
    }

    public void setProductMultiimg(ProductMultiimg productMultiimg) {
        this.productMultiimg = productMultiimg;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public String getProductStock() {
        return productStock;
    }

    public void setProductStock(String productStock) {
        this.productStock = productStock;
    }

    public String getProRating() {
        return proRating;
    }

    public void setProRating(String proRating) {
        this.proRating = proRating;
    }


}
