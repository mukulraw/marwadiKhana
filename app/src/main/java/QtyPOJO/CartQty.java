package QtyPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartQty {

    @SerializedName("success")
    @Expose
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
