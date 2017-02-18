package QtyPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class qtyBean {

    @SerializedName("cart_qty")
    @Expose
    private List<CartQty> cartQty = null;

    public List<CartQty> getCartQty() {
        return cartQty;
    }

    public void setCartQty(List<CartQty> cartQty) {
        this.cartQty = cartQty;
    }

}
