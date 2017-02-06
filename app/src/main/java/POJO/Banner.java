package POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Banner {
    @SerializedName("img_url")
    @Expose
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


}
