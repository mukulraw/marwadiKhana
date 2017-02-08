package wishlistPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mrtechs.apps.mk.Wishlist;

import java.util.List;

public class ItemDetail {


    @SerializedName("wishlist")
    @Expose
    private List<Wishlistt> wishlist = null;

    public List<Wishlistt> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<Wishlistt> wishlist) {
        this.wishlist = wishlist;
    }

}
