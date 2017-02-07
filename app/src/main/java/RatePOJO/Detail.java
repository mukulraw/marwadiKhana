package RatePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Detail {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Desc")
    @Expose
    private String desc;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("postdate")
    @Expose
    private String postdate;
    @SerializedName("ratedata")
    @Expose
    private List<Ratedatum> ratedata = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public List<Ratedatum> getRatedata() {
        return ratedata;
    }

    public void setRatedata(List<Ratedatum> ratedata) {
        this.ratedata = ratedata;
    }

}
