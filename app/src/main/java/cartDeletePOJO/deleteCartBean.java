package cartDeletePOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class deleteCartBean {

    @SerializedName("cartproduct_delete")
    @Expose
    private List<CartproductDelete> cartproductDelete = null;

    public List<CartproductDelete> getCartproductDelete() {
        return cartproductDelete;
    }

    public void setCartproductDelete(List<CartproductDelete> cartproductDelete) {
        this.cartproductDelete = cartproductDelete;
    }

}
