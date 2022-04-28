package it.unimore.dipi.iot.http.api.client.location.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZoneList {
    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;

    @SerializedName("zone")
    @Expose
    private List<ZoneInfo> zone;

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public List<ZoneInfo> getZone() {
        return zone;
    }

    public void setZone(List<ZoneInfo> zone) {
        this.zone = zone;
    }
}

