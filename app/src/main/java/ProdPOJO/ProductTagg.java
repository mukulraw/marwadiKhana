package ProdPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductTagg {

    @SerializedName("producttag")
    @Expose
    private List<Producttag> producttag = null;

    public List<Producttag> getProducttag() {
        return producttag;
    }

    public void setProducttag(List<Producttag> producttag) {
        this.producttag = producttag;
    }
}
