package ProdPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class attribute {

    @SerializedName("attribute_value")
    @Expose
    private String attributeValue;
    @SerializedName("attribute_valueId")
    @Expose
    private String attributeValueId;

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getAttributeValueId() {
        return attributeValueId;
    }

    public void setAttributeValueId(String attributeValueId) {
        this.attributeValueId = attributeValueId;
    }

}
