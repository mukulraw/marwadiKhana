package addCartPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class addCartBean {

    @SerializedName("productcart")
    @Expose
    private List<Productcart> productcart = null;

    public List<Productcart> getProductcart() {
        return productcart;
    }

    public void setProductcart(List<Productcart> productcart) {
        this.productcart = productcart;
    }

}
