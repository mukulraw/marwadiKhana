package countPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class countBean {

    @SerializedName("carttotal")
    @Expose
    private List<Carttotal> carttotal = null;

    public List<Carttotal> getCarttotal() {
        return carttotal;
    }

    public void setCarttotal(List<Carttotal> carttotal) {
        this.carttotal = carttotal;
    }

}
