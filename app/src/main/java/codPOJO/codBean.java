package codPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class codBean {

    @SerializedName("order_status")
    @Expose
    private List<OrderStatu> orderStatus = null;

    public List<OrderStatu> getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(List<OrderStatu> orderStatus) {
        this.orderStatus = orderStatus;
    }

}
