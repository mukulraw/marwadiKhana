package orderPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class orderBean {

    @SerializedName("cartorder")
    @Expose
    private List<Cartorder> cartorder = null;

    public List<Cartorder> getCartorder() {
        return cartorder;
    }

    public void setCartorder(List<Cartorder> cartorder) {
        this.cartorder = cartorder;
    }

}
