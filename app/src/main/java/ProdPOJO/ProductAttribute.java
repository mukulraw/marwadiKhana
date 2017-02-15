package ProdPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductAttribute {

    @SerializedName("attribute_data")
    @Expose
    private AttributeData attributeData;
    @SerializedName("value_data")
    @Expose
    private ValueData valueData;

    public AttributeData getAttributeData() {
        return attributeData;
    }

    public void setAttributeData(AttributeData attributeData) {
        this.attributeData = attributeData;
    }

    public ValueData getValueData() {
        return valueData;
    }

    public void setValueData(ValueData valueData) {
        this.valueData = valueData;
    }

}
