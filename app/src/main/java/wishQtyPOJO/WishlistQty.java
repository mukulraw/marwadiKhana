package wishQtyPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WishlistQty {

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
