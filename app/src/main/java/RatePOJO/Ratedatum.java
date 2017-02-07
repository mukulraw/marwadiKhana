package RatePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ratedatum {

    @SerializedName("ratename")
    @Expose
    private String ratename;
    @SerializedName("percant")
    @Expose
    private String percant;

    public String getRatename() {
        return ratename;
    }

    public void setRatename(String ratename) {
        this.ratename = ratename;
    }

    public String getPercant() {
        return percant;
    }

    public void setPercant(String percant) {
        this.percant = percant;
    }

}
