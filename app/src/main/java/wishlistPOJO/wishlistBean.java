package wishlistPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class wishlistBean {

    @SerializedName("item_count")
    @Expose
    private ItemCount itemCount;
    @SerializedName("item_detail")
    @Expose
    private ItemDetail itemDetail;

    public ItemCount getItemCount() {
        return itemCount;
    }

    public void setItemCount(ItemCount itemCount) {
        this.itemCount = itemCount;
    }

    public ItemDetail getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }

}
