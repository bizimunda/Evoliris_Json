
package evoliris.com.evoliris_json.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Wind {

    @SerializedName("speed")
    @Expose
    private double speed;
    @SerializedName("deg")
    @Expose
    private long deg;

    /**
     *
     * @return
     *     The speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     *     The speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     *
     * @return
     *     The deg
     */
    public long getDeg() {
        return deg;
    }

    /**
     *
     * @param deg
     *     The deg
     */
    public void setDeg(long deg) {
        this.deg = deg;
    }

}
