package cartPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class cartBean {

    @SerializedName("cartheader")
    @Expose
    private List<Cartheader> cartheader = null;

    public List<Cartheader> getCartheader() {
        return cartheader;
    }

    public void setCartheader(List<Cartheader> cartheader) {
        this.cartheader = cartheader;
    }

}
