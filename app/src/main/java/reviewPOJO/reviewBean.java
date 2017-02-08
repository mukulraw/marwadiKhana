package reviewPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class reviewBean {

    @SerializedName("product_review")
    @Expose
    private List<ProductReview> productReview = null;

    public List<ProductReview> getProductReview() {
        return productReview;
    }

    public void setProductReview(List<ProductReview> productReview) {
        this.productReview = productReview;
    }

}
