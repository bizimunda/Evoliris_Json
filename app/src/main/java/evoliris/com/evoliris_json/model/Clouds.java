
package evoliris.com.evoliris_json.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Clouds {

    @SerializedName("all")
    @Expose
    private long all;

    /**
     *
     * @return
     *     The all
     */
    public long getAll() {
        return all;
    }

    /**
     *
     * @param all
     *     The all
     */
    public void setAll(long all) {
        this.all = all;
    }

}
