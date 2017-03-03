package cartPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class cartBean {

    @SerializedName("cartheader")
    @Expose
    private List<Cartheader> cartheader = null;
    @SerializedName("total")
    @Expose
    private String total;

    public List<Cartheader> getCartheader() {
        return cartheader;
    }

    public void setCartheader(List<Cartheader> cartheader) {
        this.cartheader = cartheader;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
