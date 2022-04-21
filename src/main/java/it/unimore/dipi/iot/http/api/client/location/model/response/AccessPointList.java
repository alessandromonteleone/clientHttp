package it.unimore.dipi.iot.http.api.client.location.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccessPointList {
    @SerializedName("accessPoint")
    @Expose
    private List<AccessPointInfo>  accessPoint;

    @SerializedName("resourceURL")
    @Expose
    private String resourceURL;

    @SerializedName("zoneId")
    @Expose
    private String zoneId;

    public List<AccessPointInfo> getAccessPoint() {
        return accessPoint;
    }

    public void setAccessPoint(List<AccessPointInfo> accessPoint) {
        this.accessPoint = accessPoint;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }
}
