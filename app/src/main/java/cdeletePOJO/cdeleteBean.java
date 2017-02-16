package cdeletePOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class cdeleteBean {

    @SerializedName("cart_delete")
    @Expose
    private List<CartDelete> cartDelete = null;

    public List<CartDelete> getCartDelete() {
        return cartDelete;
    }

    public void setCartDelete(List<CartDelete> cartDelete) {
        this.cartDelete = cartDelete;
    }

}
