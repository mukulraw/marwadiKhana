package wishlistPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemCount {

    @SerializedName("total_item")
    @Expose
    private Integer totalItem;

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

}
