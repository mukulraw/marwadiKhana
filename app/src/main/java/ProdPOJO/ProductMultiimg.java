package ProdPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductMultiimg {

    @SerializedName("productimg")
    @Expose
    private List<Productimg> productimg = null;

    public List<Productimg> getProductimg() {
        return productimg;
    }

    public void setProductimg(List<Productimg> productimg) {
        this.productimg = productimg;
    }
}
