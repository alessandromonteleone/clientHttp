package it.unimore.dipi.iot.http.api.client.WLAN.model;


import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geolocation {

    @SerializedName("datum")
    @Expose
    private Integer datum;
    @SerializedName("lat")
    @Expose
    private Integer lat;
    @SerializedName("latUncertainty")
    @Expose
    private Integer latUncertainty;
    @SerializedName("long")
    @Expose
    private Integer _long;
    @SerializedName("longUncertainty")
    @Expose
    private Integer longUncertainty;

    public Integer getDatum() {
        return datum;
    }

    public void setDatum(Integer datum) {
        this.datum = datum;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getLatUncertainty() {
        return latUncertainty;
    }

    public void setLatUncertainty(Integer latUncertainty) {
        this.latUncertainty = latUncertainty;
    }

    public Integer getLong() {
        return _long;
    }

    public void setLong(Integer _long) {
        this._long = _long;
    }

    public Integer getLongUncertainty() {
        return longUncertainty;
    }

    public void setLongUncertainty(Integer longUncertainty) {
        this.longUncertainty = longUncertainty;
    }

}

