package getRatePOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rate {

    @SerializedName("packeg_id")
    @Expose
    private String packegId;
    @SerializedName("condition_value")
    @Expose
    private String conditionValue;
    @SerializedName("packeg_price")
    @Expose
    private String packegPrice;

    public String getPackegId() {
        return packegId;
    }

    public void setPackegId(String packegId) {
        this.packegId = packegId;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    public String getPackegPrice() {
        return packegPrice;
    }

    public void setPackegPrice(String packegPrice) {
        this.packegPrice = packegPrice;
    }

}
