package ProdPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValueData {

    @SerializedName("productattribute")
    @Expose
    private List<attribute> productattribute = null;

    public List<attribute> getProductattribute() {
        return productattribute;
    }

    public void setProductattribute(List<attribute> productattribute) {
        this.productattribute = productattribute;
    }

}
