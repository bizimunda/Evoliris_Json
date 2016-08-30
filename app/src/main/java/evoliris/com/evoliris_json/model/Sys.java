
package evoliris.com.evoliris_json.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Sys {

    @SerializedName("type")
    @Expose
    private long type;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sunrise")
    @Expose
    private long sunrise;
    @SerializedName("sunset")
    @Expose
    private long sunset;

    /**
     *
     * @return
     *     The type
     */
    public long getType() {
        return type;
    }

    /**
     *
     * @param type
     *     The type
     */
    public void setType(long type) {
        this.type = type;
    }

    /**
     *
     * @return
     *     The id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The message
     */
    public double getMessage() {
        return message;
    }

    /**
     *
     * @param message
     *     The message
     */
    public void setMessage(double message) {
        this.message = message;
    }

    /**
     *
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     *     The sunrise
     */
    public long getSunrise() {
        return sunrise;
    }

    /**
     *
     * @param sunrise
     *     The sunrise
     */
    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    /**
     *
     * @return
     *     The sunset
     */
    public long getSunset() {
        return sunset;
    }

    /**
     *
     * @param sunset
     *     The sunset
     */
    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

}
