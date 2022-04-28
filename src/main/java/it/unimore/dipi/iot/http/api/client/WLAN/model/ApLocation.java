package it.unimore.dipi.iot.http.api.client.WLAN.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApLocation {
    @SerializedName("geolocation")
    @Expose
    private Geolocation geolocation;

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

}
