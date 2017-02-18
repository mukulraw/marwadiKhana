package wishQtyPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class wishQtyBean {

    @SerializedName("wishlist_qty")
    @Expose
    private List<WishlistQty> wishlistQty = null;

    public List<WishlistQty> getWishlistQty() {
        return wishlistQty;
    }

    public void setWishlistQty(List<WishlistQty> wishlistQty) {
        this.wishlistQty = wishlistQty;
    }

}
