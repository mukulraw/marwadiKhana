package ProdPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttributeData {

    @SerializedName("attribute_type")
    @Expose
    private String attributeType;
    @SerializedName("attribute_title")
    @Expose
    private String attributeTitle;
    @SerializedName("attribute_id")
    @Expose
    private String attributeId;

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public String getAttributeTitle() {
        return attributeTitle;
    }

    public void setAttributeTitle(String attributeTitle) {
        this.attributeTitle = attributeTitle;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

}
