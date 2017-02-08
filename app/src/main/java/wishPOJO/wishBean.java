package wishPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class wishBean {

    @SerializedName("product_wishlist")
    @Expose
    private List<ProductWishlist> productWishlist = null;

    public List<ProductWishlist> getProductWishlist() {
        return productWishlist;
    }

    public void setProductWishlist(List<ProductWishlist> productWishlist) {
        this.productWishlist = productWishlist;
    }

}
